package com.xz.framework.system.app.login.controller;

import com.xz.framework.system.aop.annotation.VisitLog;
import com.xz.framework.system.app.login.domain.User;
import com.xz.framework.system.app.login.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author  wuhy on 2017/11/9.
 */
@org.springframework.stereotype.Controller
@RequestMapping("/user")
public class LoginController {
   @Resource(name="loginService")
    private LoginService loginService;
@VisitLog(method = "lgoin")
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes model){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        HttpSession session=request.getSession(true);
        session.setAttribute("_token_",uuid);
        Cookie[] c=request.getCookies();
        Cookie cookie = new Cookie("_token_",uuid);
        cookie.setPath("/");
        response.addCookie(cookie);
        String loginid=request.getParameter("loginid");
        session.setAttribute("loginid",loginid);
        String loginid2=request.getParameter("loginid2");
        User user=new User();
        user.setLoginid(loginid);
        user.setLoginidgo(loginid2);
        boolean bool= loginService.selectByLoginidPass(user);
        if (bool) {
            System.out.print("登陆成功");
           return "redirect:/index/";
        }else{
            session.setAttribute("user", loginid);
            session.setAttribute("pass", loginid2);
            return "redirect:/";
        }
    }
}
