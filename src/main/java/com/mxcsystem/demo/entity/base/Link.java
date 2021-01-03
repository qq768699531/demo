package com.mxcsystem.demo.entity.base;

import com.mxcsystem.demo.mapper.ApplyMapper;
import com.mxcsystem.demo.mapper.LogMapper;
import com.mxcsystem.demo.util.SpringUtil;

public class Link {
    private ApplyMapper applyMapper =
            (ApplyMapper) SpringUtil.applicationContext.getBean("applyMapper");
    private LogMapper logMapper =
            (LogMapper) SpringUtil.applicationContext.getBean("logMapper");

    private int ID;
    private int WorkItemType;
    private int LinkID;
    private int LinkWorkItemType;
    private String Title;

    public Link (int ID, int workItemType, int linkID, int linkWorkItemType) {
        this.ID = ID;
        WorkItemType = workItemType;
        LinkID = linkID;
        LinkWorkItemType = linkWorkItemType;
    }

    public Link (String title, String linkID, String linkWorkItemType) {
        Title = title;
        LinkID = Integer.parseInt(linkID);
        LinkWorkItemType = Integer.parseInt(linkWorkItemType);
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

    public int getLinkID () {
        return LinkID;
    }

    public void setLinkID (int linkID) {
        LinkID = linkID;
    }

    public int getLinkWorkItemType () {
        return LinkWorkItemType;
    }

    public void setLinkWorkItemType (int linkWorkItemType) {
        LinkWorkItemType = linkWorkItemType;
    }

    public String getTitle () {
        String title = null;
        if(WorkItemType == 0){
            title = applyMapper.getApplyByID(ID).getTitle();
        }else if(WorkItemType == 1){
            title = logMapper.getLogByID(ID).getTitle();
        }
        return title==null?Title:title;
    }

    public void setTitle (String title) {
        Title = title;
    }

}
