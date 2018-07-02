package com.xz.framework.system.security.domain;

/**
 * @author wuhy on 2017/11/22.
 */
public class Resources {
    private Integer resourcesid;
    private String url;
    private String presourcesid;
    private String name;
    private String effective;
    private Integer roleid;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getResourcesid() {
        return resourcesid;
    }

    public void setResourcesid(Integer resourcesid) {
        this.resourcesid = resourcesid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPresourcesid() {
        return presourcesid;
    }

    public void setPresourcesid(String presourcesid) {
        this.presourcesid = presourcesid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }
}
