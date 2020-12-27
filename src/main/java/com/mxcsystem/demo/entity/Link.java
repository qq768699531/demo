package com.mxcsystem.demo.entity;

public class Link {
    private String WorkItemType;
    private int ID;
    private String Title;
    private String note;

    public String getWorkItemType () {
        return WorkItemType;
    }

    public void setWorkItemType (String workItemType) {
        WorkItemType = workItemType;
    }

    public int getID () {
        return ID;
    }

    public void setID (int ID) {
        this.ID = ID;
    }

    public String getTitle () {
        return Title;
    }

    public void setTitle (String title) {
        Title = title;
    }

    public String getNote () {
        return note;
    }

    public void setNote (String note) {
        this.note = note;
    }
}
