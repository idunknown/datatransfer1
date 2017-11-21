package com.xz.framework.system.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * @author  wuhy on 2017/11/9.
 */
public class StartupListener extends ContextLoaderListener {
    private static Logger logger = LoggerFactory.getLogger(StartupListener.class);

    public StartupListener() {
    }
    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("开始启动系统...");
        logger.info("开始加载环境配置...");
        super.contextInitialized(event);

        logger.info("环境配置加载完成...");
        logger.info("开始加载Controller...");
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("系统开始关闭...");
        super.contextDestroyed(event);
        logger.info("系统成功关闭...");
    }
}
