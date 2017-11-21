package com.xz.framework.system.sysservice;

import com.xz.framework.system.sysbean.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/16.
 */
@Service("baseService")
public abstract class BaseService  {

    @Resource(name="baseDao")
    private  BaseDao baseDao;

    private String mapper;


    /**
     * 用map参数查询List
     * @param sqlId
     * @param param
     * @param pageSize
     * @param page
     * @return
     * @throws Exception
     */
    public List selectListForPage(String sqlId, Map param, int pageSize, int page) throws Exception{

       return baseDao.selectListForPage( sqlId,  param,  pageSize,  page);
    };

    /**
     * Object查询List
     * @param sqlId
     * @param param
     * @param pageSize
     * @param page
     * @return
     * @throws Exception
     */
    public List selectListForPageByObject(String sqlId, Object param,  int pageSize, int page) throws Exception{
        return baseDao.selectListForPageByObject(  sqlId,  param,  pageSize,  page);
    };

    /**
     * map 查询PageBean
     * @param sqlId
     * @param param
     * @param pageSize
     * @param page
     * @return
     * @throws Exception
     */
    public PageBean selectPageBeanForPage(String sqlId, Map param, int pageSize, int page) throws Exception{
        return baseDao.selectPageBeanForPage( sqlId,  param,  pageSize,  page);
    };

    /**
     * Object查询PageBean
     * @param sqlId
     * @param param
     * @param pageSize
     * @param page
     * @return
     * @throws Exception
     */
    public PageBean selectPageBeabForPageByObject(String sqlId, Object param, int pageSize, int page) throws Exception{

        return baseDao.selectPageBeanForPageByObject( sqlId,  param,  pageSize,  page);
    };
    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }



   // protected abstract  String getMapper();
  /*  private String getSqlId(String sqlId) throws Exception{
        StringBuilder stringBuilder=new StringBuilder();
        String mapper=getMapper();
        if (ObjectUtils.isEmpty(mapper)) {
            throw new SysException("没有mapper");
        }
        stringBuilder.append(mapper).append(".").append(sqlId);
        return stringBuilder.toString();
    }*/
}
