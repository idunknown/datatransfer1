package brave.spring.webmvc;

import com.sun.deploy.net.HttpResponse;
import org.omg.PortableInterceptor.ServerRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuhy on 2018/7/2.
 */
public class CustomServletHandlerInterceptor extends HandlerInterceptorAdapter {
    static final String HTTP_SERVER_SPAN_ATTRIBUTE = ServletHandlerInterceptor.class.getName() + ".customserver-span";

    /** Creates a tracing interceptor with custom */
    public static CustomServletHandlerInterceptor create(Brave brave) {
        return new Builder(brave).build();
    }

    public static Builder builder(Brave brave) {
        return new Builder(brave);
    }

    public static final class Builder {
        final Brave brave;
        SpanNameProvider spanNameProvider = new DefaultSpanNameProvider();

        Builder(Brave brave) { // intentionally hidden
            this.brave = checkNotNull(brave, "brave");
        }

        public Builder spanNameProvider(SpanNameProvider spanNameProvider) {
            this.spanNameProvider = checkNotNull(spanNameProvider, "spanNameProvider");
            return this;
        }

        public CustomServletHandlerInterceptor build() {
            return new CustomServletHandlerInterceptor(this);
        }
    }

    private final ServerRequestInterceptor requestInterceptor;
    private final ServerResponseInterceptor responseInterceptor;
    private final ServerSpanThreadBinder serverThreadBinder;
    private final SpanNameProvider spanNameProvider;

    @Autowired
        // internal
    CustomServletHandlerInterceptor(SpanNameProvider spanNameProvider, Brave brave) {
        this(builder(brave).spanNameProvider(spanNameProvider));
    }

    CustomServletHandlerInterceptor(Builder b) { // intentionally hidden
        this.requestInterceptor = b.brave.serverRequestInterceptor();
        this.responseInterceptor = b.brave.serverResponseInterceptor();
        this.serverThreadBinder = b.brave.serverSpanThreadBinder();
        this.spanNameProvider = b.spanNameProvider;
    }

    /**
     *
     */
    @Deprecated
    public CustomServletHandlerInterceptor(ServerRequestInterceptor requestInterceptor, ServerResponseInterceptor responseInterceptor, SpanNameProvider spanNameProvider, final ServerSpanThreadBinder serverThreadBinder) {
        this.requestInterceptor = requestInterceptor;
        this.spanNameProvider = spanNameProvider;
        this.responseInterceptor = responseInterceptor;
        this.serverThreadBinder = serverThreadBinder;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        requestInterceptor.handle(new CustomServerRequestAdapter(request, spanNameProvider));

        return true;
    }

    @Override
    public void afterConcurrentHandlingStarted(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        request.setAttribute(HTTP_SERVER_SPAN_ATTRIBUTE, serverThreadBinder.getCurrentServerSpan());
        serverThreadBinder.setCurrentSpan(null);
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {

        final ServerSpan span = (ServerSpan) request.getAttribute(HTTP_SERVER_SPAN_ATTRIBUTE);

        if (span != null) {
            serverThreadBinder.setCurrentSpan(span);
        }

        responseInterceptor.handle(new HttpServerResponseAdapter(new HttpResponse() {
            @Override
            public int getHttpStatusCode() {
                return response.getStatus();
            }
        }));
    }

}
