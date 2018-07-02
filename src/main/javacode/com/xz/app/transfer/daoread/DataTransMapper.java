package com.xz.app.transfer.daoread;

import com.xz.app.transfer.domain.DataTransEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/18.
 */
public interface DataTransMapper {
    List<DataTransEntity> selectPrice(Map map);
    List<HashMap> selectYbj(Map map);
}
