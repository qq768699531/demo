package com.mxcsystem.demo.entity;

public class Mention {
    int phoneNum;
    int ID;
    int WorkItemType;
    String status;
    String title;
    String assignTo;

    public int getPhoneNum () {
        return phoneNum;
    }

    public void setPhoneNum (int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getID () {
        return ID;
    }

    public void setID (int ID) {
        this.ID = ID;
    }

    public int getWorkItemType () {
        return WorkItemType;
    }

    public void setWorkItemType (int workItemType) {
        WorkItemType = workItemType;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

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
}
