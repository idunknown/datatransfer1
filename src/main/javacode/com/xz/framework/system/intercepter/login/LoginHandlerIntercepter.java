package com.xz.framework.system.intercepter.login;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author  wuhy on 2017/11/9.
 * @author wuhy
 *
 * 用于拦截登录
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {
   @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        Cookie[] cokies=httpServletRequest.getCookies();
        Object token=session.getAttribute("_token_");
        if(ObjectUtils.isEmpty(token)){
            //转发
           // httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest, httpServletResponse);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.jsp");
            return false;
        }
        //cookie  session 同时验证

        for (Cookie c:cokies) {
            if(token.toString().equals(c.getValue())){
                return true;
            }
        }
        return false;
    }
@Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
@Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
