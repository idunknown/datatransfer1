package com.xz.framework.system.sysbean.inte;

/**
 * @author  wuhy on 2017/11/16.
 */
public interface Dialect {
    boolean supportsLimit();

    String getLimitString(String oldSql);
     int firstParam(int pageSize,int page);
     int secondParam(int pageSize,int page);
}
