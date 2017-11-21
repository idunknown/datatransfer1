package com.xz.framework.system.sysbean.impl;

import java.util.HashMap;

/**
 * @author  wuhy on 2017/11/16.
 */
public class ResultInfo extends HashMap {
    private String msg;
    private boolean success;


    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        this.put("msg",msg);
    }

    public void setSuccess(boolean success) {
        this.success = success;
        this.put("success",success);
    }
}
