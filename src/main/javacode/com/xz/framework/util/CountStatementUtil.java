package com.xz.framework.util;

import com.xz.framework.system.sysbean.inte.Dialect;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author  wuhy on 2017/11/16.
 */

public class CountStatementUtil {

    private  SqlSessionTemplate sessionTemplate;

    private static SqlSessionTemplate sqlSessionTemplate;

    private Dialect dialect;
    private static Dialect mydialect;

    public void init() {
        sqlSessionTemplate = this.sessionTemplate;
        mydialect=this.dialect;
    }
    public CountStatementUtil() {
    }

    /**
     * 通过反射修改sql
     * 增加一个参数类型
     * @param sqlId
     * @param min
     * @param max
     * @return
     * @throws Exception
     */
    public static MappedStatement createListStatement(String sqlId, int min, int max) throws  Exception{
        Configuration configuration=sqlSessionTemplate.getSqlSessionFactory().getConfiguration();
        MappedStatement  statement=configuration.getMappedStatement(sqlId);
        /*SqlSource sqlSource= statement.getSqlSource();
        Class<?> clazz = sqlSource.getClass();
        Field field = clazz.getDeclaredField("sqlSource");
        field.setAccessible(true);
        SqlSource staticsqlSource=(SqlSource)field.get(sqlSource);
        // 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
        clazz=staticsqlSource.getClass();
        Field field2 = clazz.getDeclaredField("sql");
        field2.setAccessible(true);
        // 更改私有属性的值
        field2.set(staticsqlSource,"select *from user where loginid=? limit ? ,?");


        Field field3 = clazz.getDeclaredField("parameterMappings");
        field3.setAccessible(true);
        // 更改私有属性的值
        List<ParameterMapping> parameterMappings = (List<ParameterMapping>) field3.get(staticsqlSource);
        clazz=parameterMappings.getClass();
        Method method = clazz.getDeclaredMethod("add",Object.class);
        method.setAccessible(true);
        ParameterMapping parameterMapping = new ParameterMapping.Builder(configuration,"min",Integer.class).jdbcTypeName("INT").build();
        method.invoke(parameterMappings,parameterMapping);
        parameterMapping = new ParameterMapping.Builder(configuration,"max",Integer.class).jdbcTypeName("INT").build();
        method.invoke(parameterMappings,parameterMapping);
       // field3.set(staticsqlSource,getCountStatementId(sqlId));

        //修改参数类型
        statement.getBoundSql(null).getParameterMappings();*/

        return statement;
    }




    public static MappedStatement createCountStatement(String sqlId,int min,int max) {



        Configuration configuration=sqlSessionTemplate.getSqlSessionFactory().getConfiguration();

        MappedStatement  statement=configuration.getMappedStatement(sqlId);

        if( !statement.getSqlCommandType().name().equals("SELECT")) {
            throw new RuntimeException("系统异常，请联系管理员:selectStatement对象不是SelectStatement类型。");
        }
        try{
            MappedStatement  st=configuration.getMappedStatement(getCountStatementId(sqlId));
            return st;
        }catch (IllegalArgumentException e){
            List a=new ArrayList<ParameterMapping>();
            // ParameterMap p=  new ParameterMap.Builder(configuration,"loginid",User.class,a).build();
            String sql=mydialect.getLimitString(statement.getSqlSource().getBoundSql(null).getSql());
            StaticSqlSource sqlSource =new StaticSqlSource(configuration, sql,statement.getBoundSql(null).getParameterMappings());


            MappedStatement.Builder mb= new MappedStatement.Builder(configuration, getCountStatementId(sqlId),sqlSource , SqlCommandType.SELECT);
            //增加返回类型
            MapperBuilderAssistant mapperBuilderAssistant=new MapperBuilderAssistant(configuration,null);
            mb.resultMaps(new ArrayList<ResultMap>() {
                /*{
                    add(new ResultMap.Builder(configuration,
                            "defaultResultMap",
                            User.class,
                            new ArrayList<ResultMapping>(0)).build());
                }*/
            });
            List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();
            mb.parameterMap(
                    new ParameterMap.Builder(configuration,getCountStatementId(sqlId)+"-Inline",java.lang.String.class,parameterMappings).build()
            );

            //新的分页sql
            MappedStatement s=mb.build();
           // mb.
            //s.getParameterMap(
            System.out.print(s.getParameterMap().getType());;
            configuration.addMappedStatement(s);
            return s;
        }



    }

    //处理sql方言
    //mysql 分页条数

    private static String getSelectCountSqlString( String sql) {
        String sqlString = "SELECT COUNT(1) AS count from(" + sql + ") a";
        return sqlString;
    }


    public static MappedStatement createPageListSql(String sqlId){
        //获取数据库整体配置
        Configuration configuration=sqlSessionTemplate.getSqlSessionFactory().getConfiguration();
        try{
            MappedStatement  st=configuration.getMappedStatement(getListStatementId(sqlId));
            return st;
        }catch (IllegalArgumentException e) {
            //获取原sql实体
            MappedStatement statement = configuration.getMappedStatement(sqlId);
            SqlSource sqlSource = statement.getSqlSource();
            //获取原有参数
            List<ParameterMapping> parameterMappings = new ArrayList();
            parameterMappings.addAll(sqlSource.getBoundSql(null).getParameterMappings());
            //增加min参数
            ParameterMapping parameterMapping = new ParameterMapping.Builder(configuration, "min", Integer.class).jdbcType(JdbcType.INTEGER).build();
            parameterMappings.add(parameterMapping);
            //增加max参数
            parameterMapping = new ParameterMapping.Builder(configuration, "max", Integer.class).jdbcType(JdbcType.INTEGER).build();
            parameterMappings.add(parameterMapping);
            //新的sql语句

            String sql =  mydialect.getLimitString(sqlSource.getBoundSql(null).getSql());
            //创建新的sql对象
            SqlSource sqlSourceNew = new StaticSqlSource(configuration, sql, parameterMappings);
            //需要创建 原始sql 也可以用获取到的带问号的sql,如果能获取到原始sql更好，暂时没找到方法
            StringBuilder sb;
            StringBuilder sqlb=new StringBuilder(sql);

            for(int i=parameterMappings.size()-1;i>=0;i--){
                ParameterMapping p=parameterMappings.get(i);
                sb=new StringBuilder();
                int index=sqlb.lastIndexOf("?");
                sb.append("#{").append(p.getProperty()).append(",jdbcType=").append(p.getJdbcType().toString()).append("}");
                sqlb= sqlb.replace(index,index+1,sb.toString());
            }
            RawSqlSource ada=new RawSqlSource(configuration,sqlb.toString(), HashMap.class);

            //创建内部内对象
            MappedStatement.Builder mb = new MappedStatement.Builder(configuration, getListStatementId(sqlId), ada, SqlCommandType.SELECT);

            //初始化参数对象
            mb.parameterMap(
                    new ParameterMap.Builder(configuration, getListStatementId(sqlId) + "-Inline", java.util.HashMap.class, parameterMappings).build()
            );
            //初始化返回参数
            mb.resultMaps(
                    statement.getResultMaps()
            );
            MappedStatement statementNew = mb.build();
            //添加到环境
            configuration.addMappedStatement(statementNew);
            return statementNew;
        }
    }
    public static MappedStatement createPageCountSql(String sqlId){
        //获取数据库整体配置
        Configuration configuration=sqlSessionTemplate.getSqlSessionFactory().getConfiguration();
        try{
            MappedStatement  st=configuration.getMappedStatement(getCountStatementId(sqlId));
            return st;
        }catch (IllegalArgumentException e) {
            //获取原sql实体
            MappedStatement statement = configuration.getMappedStatement(sqlId);
            SqlSource sqlSource = statement.getSqlSource();
            //获取原有参数
            List<ParameterMapping> parameterMappings = sqlSource.getBoundSql(null).getParameterMappings();
            //新的sql语句
            String sql =getSelectCountSqlString(sqlSource.getBoundSql(null).getSql());
            //创建新的sql对象
            SqlSource sqlSourceNew = new StaticSqlSource(configuration, sql, parameterMappings);
            StringBuilder sb;
            StringBuilder sqlb=new StringBuilder(sql);

            for(int i=parameterMappings.size()-1;i>=0;i--){
                ParameterMapping p=parameterMappings.get(i);
                sb=new StringBuilder();
                int index=sqlb.lastIndexOf("?");
                sb.append("#{").append(p.getProperty()).append(",jdbcType=").append(p.getJdbcType()).append("}");
                sqlb= sqlb.replace(index,index+1,sb.toString());
            }
            RawSqlSource ada=new RawSqlSource(configuration,sqlb.toString(), HashMap.class);

            //创建内部内对象
            MappedStatement.Builder mb = new MappedStatement.Builder(configuration, getCountStatementId(sqlId), ada, SqlCommandType.SELECT);
            //初始化参数对象
            mb.parameterMap(
                    new ParameterMap.Builder(configuration, getCountStatementId(sqlId) + "-Inline", java.util.HashMap.class, parameterMappings).build()
            );
            //初始化返回参数
            mb.resultMaps(
                    new ArrayList<ResultMap>() {
                {
                    add(new ResultMap.Builder(configuration,
                            "defaultResultMap",
                            Integer.class,
                            new ArrayList<ResultMapping>(0)).build());
                }
                    }
            );
            MappedStatement statementNew = mb.build();
            //添加到环境
            configuration.addMappedStatement(statementNew);
            return statementNew;
        }
    }

    public static int firstParam(int pageSize,int page){
        return mydialect.firstParam(pageSize,page);
    }
    public static int secondParam(int pageSize,int page){
        return mydialect.secondParam(pageSize,page);
    }
    public static String getCountStatementId(String selectStatementId) {
        return "Hello" + selectStatementId + "WorldCount";
    }
    public static String getListStatementId(String selectStatementId) {
        return "Hello" + selectStatementId + "WorldList";
    }

  /*  public static long autoGetTotalCount(String selectQuery, Object parameterObject, IDao dao) {
        long total = 0L;

        try {
            total = getTotal4Select(selectQuery, parameterObject, dao);
            return total;
        } catch (SQLException var6) {
            var6.printStackTrace();
            throw new RuntimeException("获取总的记录数错误:" + var6.getMessage());
        }
    }*/

   /* private static long getTotal4Select(String selectQuery, Object parameterObject, IDao dao) throws SQLException {
        putCountMappedStatement(selectQuery, dao);
        String countSQL = getCountStatementId(selectQuery);
        Long result = (Long)dao.queryForObject(countSQL, parameterObject);
        result = result != null?result:Long.valueOf(0L);
        return result.longValue();
    }

    private static void putCountMappedStatement(String selectQuery, IDao dao) {
        String countQuery = getCountStatementId(selectQuery);
        SqlMapClient sqlMapClient = dao.getSqlMapClient();
        if(sqlMapClient instanceof ExtendedSqlMapClient) {
            SqlMapExecutorDelegate delegate = ((ExtendedSqlMapClient)sqlMapClient).getDelegate();

            try {
                delegate.getMappedStatement(countQuery);
            } catch (SqlMapException var8) {
                MappedStatement selectMap = delegate.getMappedStatement(selectQuery);
                if(selectMap == null) {
                    throw new AppException("XFACE分页自动获取select count时错误，需要查询的sqlmap id:" + selectQuery + ", 没有在xml文件中配置!");
                }

                MappedStatement ms = createCountStatement(selectMap);
                delegate.addMappedStatement(ms);
            }
        }

    }*/
    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        CountStatementUtil.sqlSessionTemplate = sqlSessionTemplate;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public SqlSessionTemplate getSessionTemplate() {
        return sessionTemplate;
    }

    public static SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public Dialect getDialect() {
        return dialect;
    }


    public static void main(String[] a){
        String sql="select \n *from user where aa=? limit ?,? haohao ";
        String regex="?";
        StringBuilder s=new StringBuilder(sql);
        int idex=s.lastIndexOf("?");

        s=s.replace(idex,idex+1,"nihao");
        System.out.print(s.toString());
    }
}
