package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.base.*;
import com.mxcsystem.demo.mapper.*;
import com.mxcsystem.demo.util.MyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private MentionMapper mentionMapper;
    @Autowired
    private LinkMapper linkMapper;
    @Autowired
    private DiscussionMapper discussionMapper;

    public int createNewLog(Log log){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if(log.getStatus() >= 1) log.setCreatedDate(df.format(new Date()));
        if(log.getStatus() >= 2) log.setActivatedDate(df.format(new Date()));
        if(log.getStatus() >= 3) log.setResolvedDate(df.format(new Date()));
        if(log.getStatus() >= 4) log.setClosedDate(df.format(new Date()));
        return logMapper.createNewLog(log);
    }

    public int updateLogWhileNotSubmit(Log log){
        return logMapper.updateLogWhileNotSubmit(log);
    }

    public int deleteLogWhileNotSubmit(Log log){
        return logMapper.deleteLogWhileNotSubmit(log);
    }

    public int submitLog(Log log){
        return logMapper.submitLog(log.getID());
    }

    public Log getLogByID(Log log){
        return logMapper.getLogByID(log.getID());
    }

    public List<Log> getLogListCreatedByMe (User user){
        return logMapper.getLogListCreatedByMe(user.getPhoneNum());
    }

    public int updateLogStatusByLogID(Log log){
        return logMapper.updateLogStatusByLogID(log);
    }

    public void insertMentionsFromLog (Log log) {
        Set<User> userSet = MyStringUtil.getMentionUsers(log.getRecord());
        Mention mention = new Mention();
        mention.setID(log.getID());
        mention.setStatus(1);
        for (User user:userSet) {
            mention.setPhoneNum(user.getPhoneNum());
            mention.setTitle(user.getUsername());
            mention.setAssignTo(user.getPhoneNum());
            if(mentionMapper.getMentionListByMention(mention).size() == 0){
                mentionMapper.insertMention(mention);
            }
        }
    }

    public void insertLinksFromLog (Log log) {
        Set<Link> linkSet = MyStringUtil.getLinkItems(log.getRecord());
        for(Link link:linkSet){
            link.setID(log.getID());
            link.setWorkItemType(1);
            if(linkMapper.getLinkListByLink(link).size() == 0){
                linkMapper.insertLink(link);
            }
        }
    }

    public int insertFollowFromLog (Log log, User user) {
        Follow follow = new Follow();
        follow.setID(log.getID());
        follow.setPhoneNum(user.getPhoneNum());
        follow.setWorkItemType(0);
        return followMapper.insertFollow(follow);
    }

    public int deleteFollowFromLog (Log log, User user) {
        Follow follow = new Follow();
        follow.setID(log.getID());
        follow.setPhoneNum(user.getPhoneNum());
        follow.setWorkItemType(0);
        return followMapper.deleteFollow(follow);
    }

    public int deleteMentionsByLogID (Log log) {
        return mentionMapper.deleteMentionsByLogID(log);
    }

    public int deleteLinksByLogID (Log log) {
        return linkMapper.deleteLogLinksByLogID(log);
    }

    public int deleteDiscussionsByLogID (Log log) {
        return discussionMapper.deleteDiscussionsByLogID(log);
    }

    public int deleteAllFollowFromLog (Log log) {
        return followMapper.deleteAllFollowFromLog(log);
    }

    public List<Log> getNewest5Log(){
        return logMapper.getNewest5Log();
    }
}
