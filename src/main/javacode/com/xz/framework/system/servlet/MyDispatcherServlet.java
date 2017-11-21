package com.xz.framework.system.servlet;

import com.xz.framework.system.preload.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;

/**
 * @author  wuhy on 2017/11/16.
 */
public class MyDispatcherServlet extends DispatcherServlet {
    private static Logger logger = LoggerFactory.getLogger(MyDispatcherServlet.class);

    @Override
    protected void onRefresh(ApplicationContext context) {
        super.onRefresh(context);
        logger.info("加载Controller结束...");

    }
    @Override
    protected void initFrameworkServlet() throws ServletException{
        //这里可以增加初化参数
        SysConfig.init();
        logger.info("系统启动完成...");
    }

}
