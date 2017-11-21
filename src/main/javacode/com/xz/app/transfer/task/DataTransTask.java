package com.xz.app.transfer.task;

import com.xz.app.transfer.service.DataTransServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author  wuhy on 2017/11/18.
 */
@Component("dataTransTask")
public class DataTransTask  {
    private static Logger logger = LoggerFactory.getLogger(DataTransTask.class);
    @Resource(name="dataTransService")
    private DataTransServiceImpl dataTransService;
    @Scheduled(cron = "0 0 16 * * ?")
    public void doJob(){
        logger.info("开始。。。。");
        dataTransService.selectPrice();
    }
    public DataTransServiceImpl getDataTransService() {
        return dataTransService;
    }

    public void setDataTransService(DataTransServiceImpl dataTransService) {
        this.dataTransService = dataTransService;
    }
}
