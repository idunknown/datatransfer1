package brave.spring.webmvc;
import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.http.HttpServerHandler;
import brave.http.HttpTracing;
import brave.propagation.TraceContext;
import brave.servlet.HttpServletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class TracingHandlerInterceptor extends HandlerInterceptorAdapter {

    public static AsyncHandlerInterceptor create(Tracing tracing) {
        return new TracingHandlerInterceptor(HttpTracing.create(tracing));
    }

    public static AsyncHandlerInterceptor create(HttpTracing httpTracing) {
        return new TracingHandlerInterceptor(httpTracing);
    }

    final Tracer tracer;
    final HttpServerHandler<HttpServletRequest, HttpServletResponse> handler;
    final TraceContext.Extractor<HttpServletRequest> extractor;

    @Autowired
    TracingHandlerInterceptor(HttpTracing httpTracing) { // internal
        tracer = httpTracing.tracing().tracer();
        handler = HttpServerHandler.create(httpTracing, new HttpServletAdapter());
        extractor = httpTracing.tracing().propagation().extractor(HttpServletRequest::getHeader);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        if (request.getAttribute(Tracer.SpanInScope.class.getName()) != null) {
            return true; // already handled (possibly due to async request)
        }

        Span span = handler.handleReceive(extractor, request);
        request.setAttribute(Tracer.SpanInScope.class.getName(), tracer.withSpanInScope(span));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object o, Exception ex) {
        Span span = tracer.currentSpan();
        if (span == null) return;
        ((Tracer.SpanInScope) request.getAttribute(Tracer.SpanInScope.class.getName())).close();
        handler.handleSend(response, ex, span);
    }
}