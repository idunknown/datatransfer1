package com.xz.app.transfer.task;

import com.xz.app.transfer.service.DataTransServiceImpl;
import com.xz.framework.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/18.
 */
/*@Component("dataTransTask")*/
public class DataTransTask1 {
    private static Logger logger = LoggerFactory.getLogger(DataTransTask1.class);
    @Resource(name="dataTransService")
    private DataTransServiceImpl dataTransService;
    //@Scheduled(cron = "0 0 13 * * ?")
 /*   public void doPriceJob(){
        logger.info("开始。。。。");
        dataTransService.selectPrice();
    }
*/
    @Scheduled(cron = "0 43 12 * * ?")
    public void doYbjJob(){
        logger.info("开始。。。。");
        Map m =new HashMap();
        Date date=DateUtils.parse("2017-07-11 00:00:00");
        while (true){
            date=DateUtils.addDay(date,1);
            m.put("date", DateUtils.getDateStr(date,DateUtils.FORMAT_INTEGER));

            dataTransService.selectYbjData(m);
            if(DateUtils.getDateStr(date,DateUtils.FORMAT_INTEGER)=="20170713"){
                break;
            }
        }

    }
    public DataTransServiceImpl getDataTransService() {
        return dataTransService;
    }

    public void setDataTransService(DataTransServiceImpl dataTransService) {
        this.dataTransService = dataTransService;
    }
}
