package com.xz.app.transfer.service;

import com.xz.app.transfer.dao.PriceMapper;
import com.xz.app.transfer.daoread.DataTransMapper;
import com.xz.app.transfer.domain.DataTransEntity;
import com.xz.framework.util.DateUtils;
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
public class DataTransServiceImpl {
    @Resource
    private DataTransMapper dataTransMapper;
    @Resource
    private PriceMapper priceMapper;
    public List selectPrice(){
        Map m= new HashMap();
        m.put("ab13004", DateUtils.getNow(DateUtils.FORMAT_INTEGER));
        List<DataTransEntity> list=dataTransMapper.selectPrice(m);
        if(ObjectUtils.isEmpty(list)){
            return null;
        }
        priceMapper.insertBatch(list);
        return list;
    }
}
