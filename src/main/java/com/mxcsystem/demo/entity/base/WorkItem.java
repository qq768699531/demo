package com.mxcsystem.demo.entity.base;

public class WorkItem {
    private int ID;
    private int Status;
    private String Title;
    private String Departments;
    private String CreatedBy;
    private String CreatedDate;
    private String ActivatedBy;
    private String ActivatedDate;
    private String ResolvedBy;
    private String ResolvedDate;
    private String ClosedBy;
    private String ClosedDate;
    private String History;
    private String AssignedTo;
    private String FinishDate;

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
