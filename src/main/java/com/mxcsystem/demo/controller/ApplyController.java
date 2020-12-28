package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Follow;
import com.mxcsystem.demo.entity.Mention;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.entity.WX.WXMessage;
import com.mxcsystem.demo.service.ApplyService;
import com.mxcsystem.demo.service.UserService;
import com.mxcsystem.demo.util.MyStringUtil;
import com.mxcsystem.demo.util.WXUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UserService userService;



    //用户创建新审批,返回ApplyID为成功,0为失败
    @RequestMapping(value = "/createNewApply",method = RequestMethod.POST)
    public int createNewApply(Apply apply){
        return applyService.createNewApply(apply);
    }

    //用户修改未提交的审批,1为成功,0为失败
    @RequestMapping(value = "/updateApplyWhileNotSubmit",method = RequestMethod.POST)
    public int updateApplyWhileNotSubmit(Apply apply){
        int result = applyService.updateApplyWhileNotSubmit(apply);
        applyService.deleteMentionsByApplyID(apply);
        applyService.deleteLinksByApplyID(apply);
        applyService.insertMentionFromApply(apply);
        return result;
    }

    //用户删除未提交的审批,1为成功,0为失败
    @RequestMapping(value = "/deleteApplyWhileNotSubmit",method = RequestMethod.GET)
    public int deleteApplyWhileNotSubmit(Apply apply){
        applyService.deleteMentionsByApplyID(apply);
        applyService.deleteLinksByApplyID(apply);
        applyService.deleteDiscussionsByApplyID(apply);
        return applyService.deleteApplyWhileNotSubmit(apply);
    }

    //用户提交审批,0为提交失败
    @RequestMapping(value = "/applyerSubmitApply",method = RequestMethod.POST)
    public int applyerSubmitApply(Apply apply){
        applyService.sendMentionMsgToUserByApply(apply);
        return applyService.submitApplyByApplyerOwner(apply.getID());
    }

    //用户主管修改审批状况
    @RequestMapping(value = "/applyerOwnerUpdate",method = RequestMethod.POST)
    public int applyerOwnerUpdate(Apply apply){
        return applyService.updateApplyByApplyerOwner(apply);
    }

    //查询分配给我的
    @RequestMapping(value = "/getApplyListAssignToMe",method = RequestMethod.GET)
    public List<Apply> getApplyListAssignToMe(User user){
        return applyService.getApplyListAssignToMe(user);
    }

    //查询我的活动
    @RequestMapping(value = "/getApplyListCreateByMe",method = RequestMethod.GET)
    public List<Apply> getApplyListCreateByMe(User user){
        return applyService.getApplyListCreateByMe(user);
    }

    //查询我关注的
    @RequestMapping(value = "/getFollowList",method = RequestMethod.GET)
    public List<Follow> getFollowList(User user){
        return applyService.getFollowList(user);
    }

    //查询我提及的
    @RequestMapping(value = "/getMentionList",method = RequestMethod.GET)
    public List<Mention> getMentionList(User user){
        return applyService.getMentionList(user);
    }
}
