package com.ssc.beans;

import java.util.Date;

public class ProjectUser   {
    private String pass;

    private String role;

    private Date addTime;

    private String otherConfig;

    private String longName;

    private String shortName;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getOtherConfig() {
        return otherConfig;
    }

    public void setOtherConfig(String otherConfig) {
        this.otherConfig = otherConfig == null ? null : otherConfig.trim();
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName == null ? null : longName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }
}