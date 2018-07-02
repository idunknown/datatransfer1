package com.xz.app.transfer.task;

import com.xz.app.transfer.service.DataTransServiceImpl;
import com.xz.framework.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/18.
 */
@Component("dataTransTask")
public class DataTransTask  {
    private static Logger logger = LoggerFactory.getLogger(DataTransTask.class);
    @Resource(name="dataTransService")
    private DataTransServiceImpl dataTransService;
    //@Scheduled(cron = "0 0 13 * * ?")
 /*   public void doPriceJob(){
        logger.info("开始。。。。");
        dataTransService.selectPrice();
    }
*/
    @Scheduled(cron = "0 25 15 * * ?")
    public void doYbjJob(){
        logger.info("开始。。。。");
        Map m =new HashMap();
        Date date=DateUtils.parse("2018-01-22 00:00:00");
        m.put("date", DateUtils.getDateStr(date,DateUtils.FORMAT_INTEGER));
        dataTransService.selectYbjData(m);
       /* while (true){
            date=DateUtils.addDay(date,1);
            m.put("date", DateUtils.getDateStr(date,DateUtils.FORMAT_INTEGER));

            dataTransService.selectYbjData(m);
            if(DateUtils.getDateStr(date,DateUtils.FORMAT_INTEGER)=="20170713"){
                break;
            }
        }*/

    }
    public DataTransServiceImpl getDataTransService() {
        return dataTransService;
    }

    public void setDataTransService(DataTransServiceImpl dataTransService) {
        this.dataTransService = dataTransService;
    }
}
