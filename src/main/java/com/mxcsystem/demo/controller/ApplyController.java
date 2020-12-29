package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Follow;
import com.mxcsystem.demo.entity.Mention;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    //用户创建新审批,返回ApplyID为成功,0为失败,如果有关注还需要phoneNum
    @RequestMapping(value = "/createNewApply",method = RequestMethod.POST)
    public int createNewApply(Apply apply,User user,int isFollow){
        int result = applyService.createNewApply(apply);
        if(result == 1){
            applyService.insertMentionsFromApply(apply);
            applyService.insertLinksFromApply(apply);
            if(isFollow == 1){
                applyService.insertFollowFromApply(apply,user);
            }
        }
        return result;
    }

    //用户修改未提交的审批,1为成功,0为失败
    @RequestMapping(value = "/updateApplyWhileNotSubmit",method = RequestMethod.POST)
    public int updateApplyWhileNotSubmit(Apply apply){
        int result = applyService.updateApplyWhileNotSubmit(apply);
        if(result == 1){
            //删除后重新插入，因为可能会有修改，前端没有保存修改前数据
            applyService.deleteMentionsByApplyID(apply);
            applyService.insertMentionsFromApply(apply);
            applyService.deleteLinksByApplyID(apply);
            applyService.insertLinksFromApply(apply);
        }
        return result;
    }

    //用户删除未提交的审批,1为成功,0为失败
    @RequestMapping(value = "/deleteApplyWhileNotSubmit",method = RequestMethod.GET)
    public int deleteApplyWhileNotSubmit(Apply apply){
        int result = applyService.deleteApplyWhileNotSubmit(apply);
        if(result == 1){
            applyService.deleteMentionsByApplyID(apply);
            applyService.deleteLinksByApplyID(apply);
            applyService.deleteDiscussionsByApplyID(apply);
        }
        return result;
    }

    //用户提交审批,0为提交失败
    @RequestMapping(value = "/applyerSubmitApply",method = RequestMethod.POST)
    public int applyerSubmitApply(Apply apply){
        //涉及真机调试不能使用本地地址，所以无法使用推送通知
        //applyService.sendMentionMsgToUserByApply(apply);
        return applyService.submitApplyByApplyer(apply);
    }

    //用户主管修改审批状况
    @RequestMapping(value = "/applyerOwnerUpdate",method = RequestMethod.POST)
    public int applyerOwnerUpdate(Apply apply){
        return applyService.updateApplyByApplyerOwner(apply);
    }

    @RequestMapping(value = "/updateFollowStatus")
    public int updateFollowStatus(Follow follow,boolean isFollow){
        return !isFollow?applyService.deleteFollow(follow):applyService.updateFollowStatus(follow);
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
