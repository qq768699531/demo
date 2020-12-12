package com.mxcsystem.demo.entity;

public class User {
    private String PhoneNum;
    private String Username;
    private int GroupID;
    private int isManager;
    private String QQ;
    private String Weixin;
    private String Email;
    private String hobby;

    public String getPhoneNum () {
        return PhoneNum;
    }

    public void setPhoneNum (String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getUsername () {
        return Username;
    }

    public void setUsername (String username) {
        Username = username;
    }

    public int getGroupID () {
        return GroupID;
    }

    public void setGroupID (int groupID) {
        GroupID = groupID;
    }

    public int getIsManager () {
        return isManager;
    }

    public void setIsManager (int isManager) {
        this.isManager = isManager;
    }

    public String getQQ () {
        return QQ;
    }

    public void setQQ (String QQ) {
        this.QQ = QQ;
    }

    public String getWeixin () {
        return Weixin;
    }

    public void setWeixin (String weixin) {
        Weixin = weixin;
    }

    public String getEmail () {
        return Email;
    }

    public void setEmail (String email) {
        Email = email;
    }

    public String getHobby () {
        return hobby;
    }

    public void setHobby (String hobby) {
        this.hobby = hobby;
    }

    public  User(String Username,String PhoneNum){
        this.Username = Username;
        this.PhoneNum = PhoneNum;
    }
}
