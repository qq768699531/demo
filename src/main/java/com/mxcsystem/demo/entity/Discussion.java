package com.mxcsystem.demo.entity;

public class Discussion {
    private String PhoneNum;
    private String CommentContent;
    private int ID;
    private String WorkItemType;
    private String CommentDate;
    private String Username;

    public String getPhoneNum () {
        return PhoneNum;
    }

    public void setPhoneNum (String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getCommentContent () {
        return CommentContent;
    }

    public void setCommentContent (String commentContent) {
        CommentContent = commentContent;
    }

    public int getID () {
        return ID;
    }

    public void setID (int ID) {
        this.ID = ID;
    }

    public String getWorkItemType () {
        return WorkItemType;
    }

    public void setWorkItemType (String workItemType) {
        WorkItemType = workItemType;
    }

    public String getCommentDate () {
        return CommentDate;
    }

    public void setCommentDate (String commentDate) {
        CommentDate = commentDate;
    }

    public String getUsername () {
        return Username;
    }

    public void setUsername (String username) {
        Username = username;
    }
}
