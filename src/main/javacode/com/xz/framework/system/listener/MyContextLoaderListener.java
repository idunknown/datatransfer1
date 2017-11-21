package com.xz.framework.system.listener;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * @author  wuhy on 2017/11/16.
 */
@Deprecated
public class MyContextLoaderListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.print("diyidiydiydiyidyidyidyidyidyid");
        initWebApplicationContext(event.getServletContext());
        System.out.print("ererererererererere");
    }

}

