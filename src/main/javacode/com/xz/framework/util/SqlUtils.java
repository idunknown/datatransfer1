package com.xz.framework.util;

/**
 * Created by lenovo on 2017/4/2.
 */
public class SqlUtils
{
    public SqlUtils() {
    }
/*
    public static MappedStatement createCountStatement(MappedStatement selectStatement) {
        if(selectStatement instanceof Object) {
          //  return new CountStatement((SelectStatement)selectStatement);
        } else {
           // throw new SysLevelException("系统异常，请联系管理员:selectStatement对象不是SelectStatement类型。");
        }
    }

    public static String getCountStatementId(String selectStatementId) {
        return "YinHai_" + selectStatementId + "_YinHai_Count";
    }

    public static long autoGetTotalCount(String selectQuery, Object parameterObject, AbstractDao dao) {
        long total = 0L;

        try {
            total = getTotal4Select(selectQuery, parameterObject, dao);
            return total;
        } catch (SQLException var6) {
            var6.printStackTrace();
          //  throw new AppException("获取总的记录数错误:" + var6.getMessage());
        }
    }

    private static long getTotal4Select(String selectQuery, Object parameterObject, AbstractDao dao) throws SQLException {
        putCountMappedStatement(selectQuery, dao);
        String countSQL = getCountStatementId(selectQuery);
        Long result = (Long)dao.queryForObject(countSQL, parameterObject);
        result = result != null?result:Long.valueOf(0L);
        return result.longValue();
    }

    private static void putCountMappedStatement(String selectQuery, AbstractDao dao) {
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
}
