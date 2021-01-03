package com.mxcsystem.demo.entity.base;

public class Mention {
    String PhoneNum;
    int ID;
    int WorkItemType;
    int Status;
    String Title;
    String AssignTo;

    public String getPhoneNum () {
        return PhoneNum;
    }

    public void setPhoneNum (String phoneNum) {
        PhoneNum = phoneNum;
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

    public int getStatus () {
        return Status;
    }

    public void setStatus (int status) {
        Status = status;
    }

    public String getTitle () {
        return Title;
    }

    public void setTitle (String title) {
        Title = title;
    }

    public String getAssignTo () {
        return AssignTo;
    }

    public void setAssignTo (String assignTo) {
        AssignTo = assignTo;
    }
}
