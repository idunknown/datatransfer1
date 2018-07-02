package com.xz.framework.system.security.domain;

import java.util.Set;

/**
 * @author wuhy on 2017/11/22.
 */
public class MyUser  {
    private Integer userid;

    private String loginid;

    private String loginidgo;

    private String effective;
    private Set role;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Set getRole() {
        return role;
    }

    public void setRole(Set role) {
        this.role = role;
    }



    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid == null ? null : loginid.trim();
    }

    public String getLoginidgo() {
        return loginidgo;
    }

    public void setLoginidgo(String loginidgo) {
        this.loginidgo = loginidgo == null ? null : loginidgo.trim();
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective == null ? null : effective.trim();
    }






}
