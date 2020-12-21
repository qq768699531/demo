package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Follow;
import com.mxcsystem.demo.entity.Mention;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.mapper.ApplyMapper;
import com.mxcsystem.demo.mapper.FollowMapper;
import com.mxcsystem.demo.mapper.MentionMapper;
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

    /**
     *
     * @param apply 表单
     * @return 返回审批id
     */
    public int createNewApply(Apply apply){
        applyMapper.createNewApply(apply);
        return apply.getID();
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
}
