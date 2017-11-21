package com.xz.framework.system.app.userregister.controller;

import com.xz.framework.system.app.login.domain.User;
import com.xz.framework.system.app.userregister.service.UserRegisterService;
import com.xz.framework.system.sysbean.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/16.
 */
@Controller
@RequestMapping("/user")
public class UserRegisterController {
    @Resource(name="userRegisterServiceImpl")
    UserRegisterService userRegisterService;

    @RequestMapping("/register")
    @ResponseBody
    public Map register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user=new User();
        user.setLoginid(request.getParameter("loginid"));
        user.setLoginidgo(request.getParameter("loginidgo"));
        Map resultInfo = userRegisterService.register(user);
        return resultInfo;
    }

    @RequestMapping("/getUsers")
    @ResponseBody
    public List getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User user=new User();
        user.setLoginid(request.getParameter("loginid"));
        user.setLoginidgo(request.getParameter("loginidgo"));
        return  userRegisterService.getUser(user);
    }

    @RequestMapping("/getUserPageBean")
    @ResponseBody
    public PageBean getUsersPageBean(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User user=new User();
        user.setLoginid(request.getParameter("loginid"));
     //   user.setLoginidgo(request.getParameter("loginidgo"));
        PageBean p=userRegisterService.getUserPageBean(user);
        return p ;
    }
}
