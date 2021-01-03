package com.mxcsystem.demo.entity.base;

import com.mxcsystem.demo.mapper.DiscussionMapper;
import com.mxcsystem.demo.mapper.LinkMapper;
import com.mxcsystem.demo.mapper.MentionMapper;
import com.mxcsystem.demo.util.SpringUtil;

import java.util.List;

public class Log extends WorkItem {
    private MentionMapper mentionMapper =
            (MentionMapper) SpringUtil.applicationContext.getBean("mentionMapper");
    private DiscussionMapper discussionMapper =
            (DiscussionMapper) SpringUtil.applicationContext.getBean("discussionMapper");
    private LinkMapper linkMapper =
            (LinkMapper) SpringUtil.applicationContext.getBean("linkMapper");
    private String Attachments;
    private String LogDate;
    private String Record;
    private String Defect;
    private String CorrectiveActionPlan;

    private List<Mention> mentionList;
    private List<Discussion> discussionList;
    private List<Link> linkList;

    public List<Mention> getMentionList () {
        mentionList = mentionMapper.getMentionListByLogID(this);
        return mentionList;
    }

    public void setMentionList (List<Mention> mentionList) {
        this.mentionList = mentionList;
    }

    public List<Discussion> getDiscussionList () {
        discussionList = discussionMapper.getDiscussionListByLogID(this);
        return discussionList;
    }

    public void setDiscussionList (List<Discussion> discussionList) {
        this.discussionList = discussionList;
    }

    public List<Link> getLinkList () {
        linkList = linkMapper.getLogLinkListByLogID(this);
        return linkList;
    }

    public void setLinkList (List<Link> linkList) {
        this.linkList = linkList;
    }

    public String getCorrectiveActionPlan () {
        return CorrectiveActionPlan;
    }

    public void setCorrectiveActionPlan (String correctiveActionPlan) {
        CorrectiveActionPlan = correctiveActionPlan;
    }

    public String getAttachments () {
        return Attachments;
    }

    public void setAttachments (String attachments) {
        Attachments = attachments;
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
}
