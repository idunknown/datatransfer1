package com.xz.framework.system.security.domain;

import java.util.Set;

/**
 * @author wuhy on 2017/11/22.
 */
public class Role {
    private Integer urid;
    private Integer userid;
    private Integer roleid;
    private String effective;

    private Set resources;

    public Set getResources() {
        return resources;
    }

    public void setResources(Set resources) {
        this.resources = resources;
    }

    public Integer getUrid() {
        return urid;
    }

    public void setUrid(Integer urid) {
        this.urid = urid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }
}
