package com.beiyou.pojo;

import java.util.Date;
import java.util.List;

public class SystemModuletb {
    private Integer moduleid;

    private Integer parentid;

    private String modulename;

    private Integer moduleweight;

    private String moduleurl;

    private Date modulecreatetime;

    private Date modulelastupdatetime;
    private List<SystemModuletb> children;

    public List<SystemModuletb> getChildren() {
        return children;
    }

    public void setChildren(List<SystemModuletb> children) {
        this.children = children;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public Integer getModuleweight() {
        return moduleweight;
    }

    public void setModuleweight(Integer moduleweight) {
        this.moduleweight = moduleweight;
    }

    public String getModuleurl() {
        return moduleurl;
    }

    public void setModuleurl(String moduleurl) {
        this.moduleurl = moduleurl == null ? null : moduleurl.trim();
    }

    public Date getModulecreatetime() {
        return modulecreatetime;
    }

    public void setModulecreatetime(Date modulecreatetime) {
        this.modulecreatetime = modulecreatetime;
    }

    public Date getModulelastupdatetime() {
        return modulelastupdatetime;
    }

    public void setModulelastupdatetime(Date modulelastupdatetime) {
        this.modulelastupdatetime = modulelastupdatetime;
    }
}