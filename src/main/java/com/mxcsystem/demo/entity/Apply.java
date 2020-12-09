package com.mxcsystem.demo.entity;

public class Apply extends WorkItem{
    private String Reason;
    private String MissionStatement;
    private String Analysis;
    private String Attachments;
    private String CorrectiveActionPlan;
    private String Applyer;
    private String ApplyerOwner;
    private String ApplyerOwnerNote;
    private String ApplyerManager;
    private String ApplyerManagerNote;
    private String ApplicationType;
    private String ApplicationAmount;

    public String getReason () {
        return Reason;
    }

    public void setReason (String reason) {
        Reason = reason;
    }

    public String getMissionStatement () {
        return MissionStatement;
    }

    public void setMissionStatement (String missionStatement) {
        MissionStatement = missionStatement;
    }

    public String getAnalysis () {
        return Analysis;
    }

    public void setAnalysis (String analysis) {
        Analysis = analysis;
    }

    public String getAttachments () {
        return Attachments;
    }

    public void setAttachments (String attachments) {
        Attachments = attachments;
    }

    public String getCorrectiveActionPlan () {
        return CorrectiveActionPlan;
    }

    public void setCorrectiveActionPlan (String correctiveActionPlan) {
        CorrectiveActionPlan = correctiveActionPlan;
    }

    public String getApplyer () {
        return Applyer;
    }

    public void setApplyer (String applyer) {
        Applyer = applyer;
    }

    public String getApplyerOwner () {
        return ApplyerOwner;
    }

    public void setApplyerOwner (String applyerOwner) {
        ApplyerOwner = applyerOwner;
    }

    public String getApplyerOwnerNote () {
        return ApplyerOwnerNote;
    }

    public void setApplyerOwnerNote (String applyerOwnerNote) {
        ApplyerOwnerNote = applyerOwnerNote;
    }

    public String getApplyerManager () {
        return ApplyerManager;
    }

    public void setApplyerManager (String applyerManager) {
        ApplyerManager = applyerManager;
    }

    public String getApplyerManagerNote () {
        return ApplyerManagerNote;
    }

    public void setApplyerManagerNote (String applyerManagerNote) {
        ApplyerManagerNote = applyerManagerNote;
    }

    public String getApplicationType () {
        return ApplicationType;
    }

    public void setApplicationType (String applicationType) {
        ApplicationType = applicationType;
    }

    public String getApplicationAmount () {
        return ApplicationAmount;
    }

    public void setApplicationAmount (String applicationAmount) {
        ApplicationAmount = applicationAmount;
    }
}
