package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.WX.WXMessage;
import com.mxcsystem.demo.entity.base.*;
import com.mxcsystem.demo.mapper.*;
import com.mxcsystem.demo.util.MyStringUtil;
import com.mxcsystem.demo.util.WXUtil;
import me.chanjar.weixin.common.error.WxErrorException;
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

    public int submitApplyByApplyer(Apply apply){
        return applyMapper.submitApply(apply.getID());
    }

    public int updateApplyByApplyerOwner(Apply apply){
        return applyMapper.updateApplyByApplyerOwner(apply);
    }

    public int updateApplyByApplyerManager(Apply apply){
        return applyMapper.updateApplyByApplyerManager(apply);
    }

    public List<Apply> getApplyList(User user){
        if(user.getIsManager() == 1){
            return applyMapper.getApplyListLargerThanStatus(1);
        }else{
            return applyMapper.getApplyListCreatedByMe(user.getPhoneNum());
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

    public int deleteMentionsByApplyID(Apply apply){
        return mentionMapper.deleteMentionsByApplyID(apply);
    }

    public int deleteLinksByApplyID (Apply apply) {
        return linkMapper.deleteApplyLinksByApplyID(apply);
    }

    public int deleteDiscussionsByApplyID(Apply apply){
        return discussionMapper.deleteDiscussionsByApplyID(apply);
    }

    public int deleteApplyWhileNotSubmit (Apply apply) {
        return applyMapper.deleteApplyWhileNotSubmit(apply);
    }

    public void insertMentionsFromApply (Apply apply){
        Set<User> userSet = MyStringUtil.getMentionUsers(apply.getMissionStatement());
        Mention mention = new Mention();
        mention.setID(apply.getID());
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

    public void insertLinksFromApply (Apply apply){
        Set<Link> linkSet = MyStringUtil.getLinkItems(apply.getMissionStatement());
        for(Link link:linkSet){
            link.setID(apply.getID());
            link.setWorkItemType(0);
            if(linkMapper.getLinkListByLink(link).size() == 0){
                linkMapper.insertLink(link);
            }
        }
    }

    public int insertFollowFromApply (Apply apply,User user){
        Follow follow = new Follow();
        follow.setID(apply.getID());
        follow.setWorkItemType(0);
        follow.setPhoneNum(user.getPhoneNum());
        return followMapper.insertFollow(follow);
    }

    public int deleteFollowFromApply (Apply apply, User user) {
        Follow follow = new Follow();
        follow.setID(apply.getID());
        follow.setWorkItemType(0);
        follow.setPhoneNum(user.getPhoneNum());
        return followMapper.deleteFollow(follow);
    }

    private List<Link> getLinkListByApplyID (Apply apply) {
        return linkMapper.getApplyLinkListByApplyID(apply);
    }

    public void sendMentionMsgToUserByApply (Apply apply){
        Set<User> userSet = MyStringUtil.getMentionUsers(apply.getMissionStatement());
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


    public int deleteAllFollowFromApply (Apply apply) {
        return followMapper.deleteAllFollowFromApply(apply);
    }

    public List<Apply> getNewest5Apply(){
        return applyMapper.getNewest5Apply();
    }
}
