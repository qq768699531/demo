package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Follow;
import com.mxcsystem.demo.entity.Mention;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.entity.WX.WXMessage;
import com.mxcsystem.demo.mapper.*;
import com.mxcsystem.demo.util.MyStringUtil;
import com.mxcsystem.demo.util.WXUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    @Autowired
    private UserMapper userMapper;

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
            return applyMapper.getApplyListByPhoneNum(user.getPhoneNum());
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

    public void insertMentionFromApply (Apply apply){
        Set<User> userSet = MyStringUtil.matchAt(apply.getMissionStatement());
        Mention mention = new Mention();
        mention.setID(apply.getID());
        mention.setStatus(1);
        for (User user:userSet) {
            mention.setPhoneNum(user.getPhoneNum());
            mention.setTitle(user.getUsername());
            mention.setAssignTo(user.getPhoneNum());
            if(getMentionListByMention(mention).size() == 0){
                insertMention(mention);
            }
        }
    }

    public void insertLinkFromApply(Apply apply){

    }

    public void insertFollowFromApplyAndUser(Apply apply,User user){
        Follow follow = new Follow();
        follow.setID(apply.getID());
        follow.setAssignTo(apply.getAssignedTo());
        follow.setPhoneNum(user.getPhoneNum());
        follow.setStatus(apply.getStatus());
        follow.setTitle(apply.getTitle());
        follow.setWorkItemType(0);
        insertFollow(follow);
    }

    public void insertFollow (Follow follow) {
        followMapper.insertFollow(follow);
    }

    public void sendMentionMsgToUserByApply (Apply apply){
        Set<User> userSet = MyStringUtil.matchAt(apply.getMissionStatement());
        for (User user:userSet) {
            String openid = userMapper.getUserOpenID(user);
            if(openid != null){
                WXMessage wxMessage = new WXMessage();
                wxMessage.setOpenid(openid);
                wxMessage.setTitle(apply.getTitle());
                wxMessage.setApplyer(apply.getApplyer());
                wxMessage.setAssignTo(apply.getAssignedTo());
                wxMessage.setThing(apply.getMissionStatement());
                wxMessage.setDate(apply.getActivatedDate());
                WXUtil wxUtil = new WXUtil();
                try {
                    wxUtil.sendSubscribeMsg(wxMessage);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("用户" + user.getPhoneNum() + "的openid为空");
            }
        }
    }
}
