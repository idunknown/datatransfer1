package com.xz.framework.system.app.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/31.
 */
@Controller
@RequestMapping("/index1")
public class IndexController1 {
    static final Logger log = LoggerFactory.getLogger(IndexController1.class);
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("msg","login！");
        return "/index";
    }
    @RequestMapping("/test1")
    public Map testJson(){
        Map m=new HashMap();
        m.put("wuhy","555");
        return m;
    }
}
