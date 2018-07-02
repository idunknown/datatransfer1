package com.xz.app.transfer.dao;

import com.xz.app.transfer.domain.DataTransEntity;
import com.xz.app.transfer.domain.Price;

import java.util.List;
import java.util.Map;

public interface PriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
    int insertBatch(List<DataTransEntity> list);

    int insertYbj(Map map);

    int insertYbjBatch( List list);


}