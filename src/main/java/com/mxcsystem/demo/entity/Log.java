package com.mxcsystem.demo.entity;

import com.mxcsystem.demo.mapper.LogMapper;
import com.mxcsystem.demo.util.SpringUtil;

public class Log extends WorkItem{
    private LogMapper logMapper =
            (LogMapper) SpringUtil.applicationContext.getBean("logMapper");
    private String Attachment;
    private String LogDate;
    private String Record;
    private String Defect;
    private String CorrectiveActionPlan;

    public String getCorrectiveActionPlan () {
        return CorrectiveActionPlan;
    }

    public void setCorrectiveActionPlan (String correctiveActionPlan) {
        CorrectiveActionPlan = correctiveActionPlan;
    }

    public String getAttachment () {
        return Attachment;
    }

    public void setAttachment (String attachment) {
        Attachment = attachment;
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
