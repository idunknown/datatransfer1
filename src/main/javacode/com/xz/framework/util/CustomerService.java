package com.xz.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author  wuhy on 2017/11/15.
 */
//@Service
public class CustomerService implements ApplicationListener<ContextRefreshedEvent>{
    private static Logger logger = LoggerFactory.getLogger(CustomerService.class);


    public void onApplicationEvent(ContextRefreshedEvent event) {
       if("WebApplicationContext for namespace 'springMvc-servlet'".equals(event.getApplicationContext().getDisplayName())){
           logger.info("扫描Controller结束***********");
           logger.info("系统启动完成*************");
           //cichu
       };

    }

   /* public void afterPropertiesSet() throws Exception {
        System.out.println("Init method after properties are set 你好你好还年会: ");
    }

    public void destroy() throws Exception {
        System.out.println("Spring Container is destroy! Customer clean up");
    }*/

}