package com.mxcsystem.demo.entity.base;

import com.mxcsystem.demo.mapper.DiscussionMapper;
import com.mxcsystem.demo.mapper.LinkMapper;
import com.mxcsystem.demo.mapper.MentionMapper;
import com.mxcsystem.demo.util.SpringUtil;

import java.util.List;

public class Apply extends WorkItem {
    private MentionMapper mentionMapper =
            (MentionMapper) SpringUtil.applicationContext.getBean("mentionMapper");
    private DiscussionMapper discussionMapper =
            (DiscussionMapper) SpringUtil.applicationContext.getBean("discussionMapper");
    private LinkMapper linkMapper =
            (LinkMapper) SpringUtil.applicationContext.getBean("linkMapper");

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

    private List<Mention> mentionList;
    private List<Discussion> discussionList;
    private List<Link> linkList;

    public List<Mention> getMentionList () {
        List<Mention> mentions = mentionMapper.getMentionListByApplyID(this);
        return mentions.size()==0?mentionList:mentions;
    }

    public void setMentionList (List<Mention> mentionList) {
        this.mentionList = mentionList;
    }

    public List<Discussion> getDiscussionList () {
        List<Discussion> discussions = discussionMapper.getDiscussionListByApplyID(this);
        return discussions.size()==0?discussionList:discussions;
    }

    public void setDiscussionList (List<Discussion> discussionList) {
        this.discussionList = discussionList;
    }

    public List<Link> getLinkList () {
        List<Link> links = linkMapper.getApplyLinkListByApplyID(this);
        return links.size()==0?linkList:links;
    }

    public void setLinkList (List<Link> linkList) {
        this.linkList = linkList;
    }

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
