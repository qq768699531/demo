package com.mxcsystem.demo.entity.WX;

public class WXMessage {
    private String code;
    private String openid;

    private String title;
    private String assignTo;
    private String thing;
    private String applyer;
    private String date;

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getAssignTo () {
        return assignTo;
    }

    public void setAssignTo (String assignTo) {
        this.assignTo = assignTo;
    }

    public String getThing () {
        return thing;
    }

    public void setThing (String thing) {
        this.thing = thing;
    }

    public String getApplyer () {
        return applyer;
    }

    public void setApplyer (String applyer) {
        this.applyer = applyer;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getOpenid () {
        return openid;
    }

    public void setOpenid (String openid) {
        this.openid = openid;
    }
}
