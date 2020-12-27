package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Follow;
import com.mxcsystem.demo.entity.Mention;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyService {
    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private MentionMapper mentionMapper;
    @Autowired
    private LinkMapper linkMapper;
    @Autowired
    private DiscussionMapper discussionMapper;

    /**
     *
     * @param apply 表单
     * @return 返回审批id
     */
    public int createNewApply(Apply apply){
        applyMapper.createNewApply(apply);
        return apply.getID();
    }

    public int updateApplyWhileNotSubmit(Apply apply){
        return applyMapper.updateApplyWhileNotSubmit(apply);
    }

    public int updateApplyByApplyerOwner(Apply apply){
        return applyMapper.updateApplyByApplyerOwner(apply);
    }

    public int updateApplyByApplyerManager(Apply apply){
        return applyMapper.updateApplyByApplyerManager(apply);
    }

    public int submitApplyByApplyerOwner(int applyID){
        return applyMapper.submitApply(applyID);
    }

    public List<Apply> getApplyList(User user){
        if(user.getIsManager() == 1){
            return applyMapper.getApplyListLargerThanStatus(1);
        }else{
            return applyMapper.getApplyListByUserID(user.getPhoneNum());
        }
    }

    public List<Follow> getFollowList(User user){
        return followMapper.getFollowListByPhoneNumber(user);
    }

    public List<Mention> getMentionList (User user) {
        return mentionMapper.getMentionListByPhoneNumber(user);
    }

    public List<Apply> getApplyListAssignToMe (User user) {
        return applyMapper.getApplyListAssignToMe(user);
    }

    public List<Apply> getApplyListCreateByMe (User user) {
        return applyMapper.getApplyListCreateByMe(user);
    }

    public int insertMention(Mention mention){
        return mentionMapper.insertMention(mention);
    }

    public int deleteMentionsByApplyID(Apply apply){
        return mentionMapper.deleteMentionsByApplyID(apply);
    }

    public int deleteLinksByApplyID (Apply apply) {
        return linkMapper.deleteLinksByApplyID(apply);
    }

    public int deleteDiscussionsByApplyID(Apply apply){
        return discussionMapper.deleteDiscussionsByApplyID(apply);
    }

    public List<Mention> getMentionListByMention(Mention mention){
        return mentionMapper.getMentionListByMention(mention);
    }

    public int deleteApplyWhileNotSubmit (Apply apply) {
        return applyMapper.deleteApplyWhileNotSubmit(apply);
    }


}
