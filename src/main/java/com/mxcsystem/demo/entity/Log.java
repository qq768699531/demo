package com.mxcsystem.demo.entity;

public class Log {
    private String Attachment;
    private int ID;
    private String Department;
    private String Title;
    private String CreatedBy;
    private String LogDate;
    private String Record;
    private String Defect;
    private String ActivatedBy;
    private String ActivatedDate;
    private String CloseDate;
    private String CloseBy;
    private String ResolvedBy;
    private String ResolvedDate;
    private String AssignedTo;
    private String Status;
    private String History;

    public String getAttachment () {
        return Attachment;
    }

    public void setAttachment (String attachment) {
        Attachment = attachment;
    }

    public int getID () {
        return ID;
    }

    public void setID (int ID) {
        this.ID = ID;
    }

    public String getDepartment () {
        return Department;
    }

    public void setDepartment (String department) {
        Department = department;
    }

    public String getTitle () {
        return Title;
    }

    public void setTitle (String title) {
        Title = title;
    }

    public String getCreatedBy () {
        return CreatedBy;
    }

    public void setCreatedBy (String createdBy) {
        CreatedBy = createdBy;
    }

    public String getLogDate () {
        return LogDate;
    }

    public void setLogDate (String logDate) {
        LogDate = logDate;
    }

    public String getRecord () {
        return Record;
    }

    public void setRecord (String record) {
        Record = record;
    }

    public String getDefect () {
        return Defect;
    }

    public void setDefect (String defect) {
        Defect = defect;
    }

    public String getActivatedBy () {
        return ActivatedBy;
    }

    public void setActivatedBy (String activatedBy) {
        ActivatedBy = activatedBy;
    }

    public String getActivatedDate () {
        return ActivatedDate;
    }

    public void setActivatedDate (String activatedDate) {
        ActivatedDate = activatedDate;
    }

    public String getCloseDate () {
        return CloseDate;
    }

    public void setCloseDate (String closeDate) {
        CloseDate = closeDate;
    }

    public String getCloseBy () {
        return CloseBy;
    }

    public void setCloseBy (String closeBy) {
        CloseBy = closeBy;
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

    public String getAssignedTo () {
        return AssignedTo;
    }

    public void setAssignedTo (String assignedTo) {
        AssignedTo = assignedTo;
    }

    public String getStatus () {
        return Status;
    }

    public void setStatus (String status) {
        Status = status;
    }

    public String getHistory () {
        return History;
    }

    public void setHistory (String history) {
        History = history;
    }
}
