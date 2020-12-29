package com.mxcsystem.demo.entity;

import com.mxcsystem.demo.mapper.ApplyMapper;
import com.mxcsystem.demo.mapper.LogMapper;
import com.mxcsystem.demo.util.SpringUtil;

public class Follow {
    private ApplyMapper applyMapper =
            (ApplyMapper) SpringUtil.applicationContext.getBean("applyMapper");
    private LogMapper logMapper =
            (LogMapper) SpringUtil.applicationContext.getBean("logMapper");

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
        this.PhoneNum = phoneNum;
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
        int status = 0;
        if(WorkItemType == 0){
            status = applyMapper.getApplyByID(ID).getStatus();
        }else if (WorkItemType == 1){
            status = logMapper.getLogByID(ID).getStatus();
        }
        return status == 0?Status:status;
    }

    public void setStatus (int status) {
        this.Status = status;
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
        this.Title = title;
    }

    public String getAssignTo () {
        if(WorkItemType == 0){
            AssignTo = applyMapper.getApplyByID(ID).getAssignedTo();
        }else if (WorkItemType == 1){
            AssignTo = logMapper.getLogByID(ID).getAssignedTo();
        }
        return AssignTo;
    }

    public void setAssignTo (String assignTo) {
        this.AssignTo = assignTo;
    }
}
