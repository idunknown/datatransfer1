package com.xz.framework.system.sysbean.impl;

import com.xz.framework.system.sysbean.inte.Dialect;
import org.apache.commons.lang3.StringUtils;

/**
 * @author  wuhy on 2017/11/16.
 */
public class MySQLDialect implements Dialect{
    @Override
    public boolean supportsLimit() {
        return false;
    }

    /**
     * 第一个问号对应第一个参数
     * 第二个问号对应第二个参数
     * @param oldSql
     * @return
     */
    @Override
    public String getLimitString(String oldSql) {
        String tmpSql = StringUtils.lowerCase(StringUtils.deleteWhitespace(oldSql));
        boolean sqlFlag = StringUtils.contains(tmpSql, "orderby") || StringUtils.contains(tmpSql, "groupby") || StringUtils.contains(tmpSql, "having") || StringUtils.contains(tmpSql, "union") || StringUtils.contains(tmpSql, "distinct") || StringUtils.countMatches(tmpSql, "select") > 1;
        if(!sqlFlag) {
            return "( " + oldSql + " ) LIMIT " + "?" + "," + "?";
        } else {
            return "( " + oldSql + " ) LIMIT " + "?" + "," + "?";
        }
    }
   @Override
    public int firstParam(int pageSize,int page){
        return (page-1)*pageSize;
    }
    @Override
    public int secondParam(int pageSize,int page){
        return pageSize;
    }
}
