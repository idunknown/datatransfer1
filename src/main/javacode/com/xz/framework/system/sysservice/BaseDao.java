package com.xz.framework.system.sysservice;

import com.xz.framework.system.sysbean.PageBean;
import com.xz.framework.util.CountStatementUtil;
import org.apache.ibatis.mapping.MappedStatement;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/16.
 */
public class BaseDao extends SqlSessionDaoSupport {
    private static Logger logger = LoggerFactory.getLogger(BaseDao.class);
    public PageBean selectPageBeanForPage(String sqlId, Map param, int pageSize, int page) {
        int min = (page-1)*pageSize+1;
        int max = page*pageSize;
        param.put("min",CountStatementUtil.firstParam(pageSize,page));
        param.put("max",CountStatementUtil.secondParam(pageSize,page));
        //查询list
        MappedStatement statement = CountStatementUtil.createPageListSql(sqlId);
        List l=super.getSqlSession().selectList(statement.getId(),param);
        //查询总数
        MappedStatement statement1 = CountStatementUtil.createPageCountSql(sqlId);
        Integer count=super.getSqlSession().selectOne(statement1.getId(),param);
        PageBean pageBean=new PageBean();
        pageBean.setPage(page);
        pageBean.setPageSize(pageSize);
        pageBean.setPageList(l);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    public List selectListForPage(String sqlId, Map param, int pageSize, int page){
        PageBean pageBean=selectPageBeanForPage(sqlId,param,pageSize,page);
        return pageBean.getPageList();
    }

    public PageBean selectPageBeanForPageByObject(String sqlId, Object object, int pageSize, int page){
        Map param=new HashMap();
        Class clazz=object.getClass();
        Field[] fields= clazz.getDeclaredFields();

            for(Field f:fields){
                try {
                    f.setAccessible(true);
                    Object o=f.get(object);
                    if(ObjectUtils.isEmpty(o)){
                        continue;
                    }
                    param.put(f.getName(),o);
                }catch (IllegalAccessException e){
                   logger.info(f.getName()+" is null ");
                }

            }

        PageBean pageBean=selectPageBeanForPage(sqlId,param,pageSize,page);
        return pageBean;
    }
    public List selectListForPageByObject(String sqlId, Object object, int pageSize, int page){
        PageBean pageBean=selectPageBeanForPageByObject(sqlId,object,pageSize,page);
        return pageBean.getPageList();
    }

    }