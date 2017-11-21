package com.xz.framework.system.app.login.controller;

import com.xz.framework.system.preload.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/31.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    static final Logger log = LoggerFactory.getLogger(IndexController.class);
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("msg","login！");
        return "/index";
    }
    @RequestMapping("/testjson")
    @ResponseBody
    public Map testJson(){
        SysConfig.getSysConfig("test");
        log.error("错误错误");
        log.info(SysConfig.getSysConfig("test"));
        log.info(SysConfig.getSysConfig("systemtest"));
        log.debug("调试调试");
        Map m=new HashMap();
        m.put("aa","aa");
        m.put("bb","bb");
        return m;
    }
}
