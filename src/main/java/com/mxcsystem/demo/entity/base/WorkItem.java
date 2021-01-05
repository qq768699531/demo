package com.mxcsystem.demo.entity.base;

import com.mxcsystem.demo.mapper.UserMapper;
import com.mxcsystem.demo.util.SpringUtil;

public class WorkItem {
    private UserMapper userMapper =
            (UserMapper) SpringUtil.applicationContext.getBean("userMapper");
    private int ID;
    private int Status;
    private String Title;
    private String Departments;

    private String CreatedBy;
    private String CreatedDate;
    private String CreatedByName;
    private String ActivatedBy;
    private String ActivatedDate;
    private String ActivatedByName;
    private String ResolvedBy;
    private String ResolvedDate;
    private String ResolvedByName;
    private String ClosedBy;
    private String ClosedDate;
    private String ClosedByName;

    private String AssignedTo;
    private String AssignedToName;

    private String History;
    private String FinishDate;

    public String getAssignedToName () {
        User user = userMapper.getUserInfoByPhoneNum(AssignedTo);
        return user==null?AssignedToName:user.getUsername();
    }

    public void setAssignedToName (String assignedToName) {
        AssignedToName = assignedToName;
    }

    public String getCreatedByName () {
        User user = userMapper.getUserInfoByPhoneNum(CreatedBy);
        return user==null?CreatedByName:user.getUsername();
    }

    public void setCreatedByName (String createdByName) {
        CreatedByName = createdByName;
    }

    public String getActivatedByName () {
        User user = userMapper.getUserInfoByPhoneNum(ActivatedBy);
        return user==null?ActivatedByName:user.getUsername();
    }

    public void setActivatedByName (String activatedByName) {
        ActivatedByName = activatedByName;
    }

    public String getResolvedByName () {
        User user = userMapper.getUserInfoByPhoneNum(ResolvedBy);
        return user==null?ResolvedByName:user.getUsername();
    }

    public void setResolvedByName (String resolvedByName) {
        ResolvedByName = resolvedByName;
    }

    public String getClosedByName () {
        User user = userMapper.getUserInfoByPhoneNum(ClosedBy);
        return user==null?ClosedByName:user.getUsername();
    }

    public void setClosedByName (String closedByName) {
        ClosedByName = closedByName;
    }

    public String getFinishDate () {
        return FinishDate;
    }

    public void setFinishDate (String finishDate) {
        FinishDate = finishDate;
    }

    public String getResolvedBy () {
        return ResolvedBy;
    }

    public void setResolvedBy (String resolvedBy) {
        ResolvedBy = resolvedBy;
    }

    public String getResolvedDate () {
        return ResolvedDate;
    }

    public void setResolvedDate (String resolvedDate) {
        ResolvedDate = resolvedDate;
    }

    public String getCreatedDate () {
        return CreatedDate;
    }

    public void setCreatedDate (String createdDate) {
        CreatedDate = createdDate;
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

    public String getDepartments () {
        return Departments;
    }

    public void setDepartments (String departments) {
        Departments = departments;
    }

    public String getCreatedBy () {
        return CreatedBy;
    }

    public void setCreatedBy (String createdBy) {
        CreatedBy = createdBy;
    }

    public String getActivatedDate () {
        return ActivatedDate;
    }

    public void setActivatedDate (String activatedDate) {
        ActivatedDate = activatedDate;
    }

    public String getActivatedBy () {
        return ActivatedBy;
    }

    public void setActivatedBy (String activatedBy) {
        ActivatedBy = activatedBy;
    }

    public String getClosedDate () {
        return ClosedDate;
    }

    public void setClosedDate (String closedDate) {
        ClosedDate = closedDate;
    }

    public String getClosedBy () {
        return ClosedBy;
    }

    public void setClosedBy (String closedBy) {
        ClosedBy = closedBy;
    }

    public String getHistory () {
        return History;
    }

    public void setHistory (String history) {
        History = history;
    }

    public String getAssignedTo () {
        return AssignedTo;
    }

    public void setAssignedTo (String assignedTo) {
        AssignedTo = assignedTo;
    }

    public int getStatus () {
        return Status;
    }

    public void setStatus (int status) {
        Status = status;
    }
}
