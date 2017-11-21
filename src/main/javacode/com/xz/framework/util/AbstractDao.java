package com.xz.framework.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/4/2.
 */
public class AbstractDao extends SqlSessionDaoSupport {
    @Override
    @Autowired(required = false)
    @Qualifier("sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    public  <E> List<E> queryForListWithCount(String statement, Object parameter, Map map){
        map.put("total",getTotal(statement));
        return getSqlSession().selectList( statement,  parameter);

    }


    public Object queryForObject(String statement,Object parameter){
        return getSqlSession().selectOne(statement,parameter);
    }

    /**
     * 获取总数
     * @param statement
     * @return
     */
    public int getTotal(String statement){
      //  Person p2=new Person(9,"11","waw");
      //  String aa=getSqlSession().getConfiguration().getMappedStatement("com.hello.dao.PersonMapper.insert").getBoundSql(p2).getSql();
        Object o=getSqlSession().selectOne("select *from person");
    //    System.out.print(aa);
        return 1;
    }
}