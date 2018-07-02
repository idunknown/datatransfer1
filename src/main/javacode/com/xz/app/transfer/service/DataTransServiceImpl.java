package com.xz.app.transfer.service;

import com.xz.app.transfer.dao.PriceMapper;
import com.xz.app.transfer.daoread.DataTransMapper;
import com.xz.app.transfer.domain.DataTransEntity;
import com.xz.framework.system.sysservice.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/18.
 */
@Transactional
@Service("dataTransService")
public class DataTransServiceImpl extends BaseService {
    @Resource
    private DataTransMapper dataTransMapper;
    @Resource
    private PriceMapper priceMapper;
    public List selectPrice(){
        Map m= new HashMap();
        m.put("ab13004", "2017-11-15"/*DateUtils.getNow(DateUtils.FORMAT_INTEGER)*/);
        List<DataTransEntity> list=dataTransMapper.selectPrice(m);
        if(ObjectUtils.isEmpty(list)){
            return null;
        }
        priceMapper.insertBatch(list);
        return list;
    }

    public List selectYbjData(Map m){
        /**
         * 每天同步前一天的数据
         */
             List list=dataTransMapper.selectYbj(m);
        insertBatchWithSimpleSql("com.xz.app.transfer.dao.PriceMapper.insertYbj",list);
        return list;
    }
}
