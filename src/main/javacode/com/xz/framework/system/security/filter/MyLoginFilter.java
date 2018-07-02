package com.xz.framework.system.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuhy on 2017/11/21.
 */
public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //增加验证码验证
        //如果验证码正确则进入其他验证，不正确则直接返回异常

        return super.attemptAuthentication(request, response);
    }
}

