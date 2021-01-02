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

    /**
     *
     * @param apply 需要全面的信息
     * @param user 如果设置关注，则需要user的PhoneNum
     * @param isFollow 设置是否关注
     * @return 返回apply的id
     */
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

    /**
     *
     * @param apply 可以修改的内容包括
     *              Departments Title Reason MissionStatement Analysis
     *              Attachments CorrectiveActionPlan
     *              需要的条件是ID
     * @return 0为失败，1为成功
     */
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

    /**
     *
     * @param apply 只需要ID
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/deleteApplyWhileNotSubmit",method = RequestMethod.GET)
    public int deleteApplyWhileNotSubmit(Apply apply){
        int result = applyService.deleteApplyWhileNotSubmit(apply);
        if(result == 1){
            applyService.deleteMentionsByApplyID(apply);
            applyService.deleteLinksByApplyID(apply);
            applyService.deleteDiscussionsByApplyID(apply);
            applyService.deleteAllFollowFromApply(apply);
        }
        return result;
    }

    /**
     *
     * @param apply 只需要ID
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/applyerSubmitApply",method = RequestMethod.POST)
    public int applyerSubmitApply(Apply apply){
        //涉及真机调试不能使用本地地址，所以无法使用推送通知
        //applyService.sendMentionMsgToUserByApply(apply);
        return applyService.submitApplyByApplyer(apply);
    }

    /**
     *
     * @param apply 需要
     *              ApplyerOwner ApplyerOwnerNote ResolvedBy ID
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/applyerOwnerUpdate",method = RequestMethod.POST)
    public int applyerOwnerUpdate(Apply apply){
        return applyService.updateApplyByApplyerOwner(apply);
    }

    /**
     *
     * @param apply 只需要ID
     * @param user 只需要PhoneNum
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/setFollow")
    public int insertFollowFromApply(Apply apply,User user){
        return applyService.insertFollowFromApply(apply,user);
    }

    /**
     *
     * @param apply 只需要ID
     * @param user 只需要PhoneNum
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/cancelFollow")
    public int deleteFollowFromApply(Apply apply,User user){
        return applyService.deleteFollowFromApply(apply,user);
    }

    /**
     *
     * @param user 只需要PhoneNum
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/getApplyListAssignToMe",method = RequestMethod.GET)
    public List<Apply> getApplyListAssignToMe(User user){
        return applyService.getApplyListAssignToMe(user);
    }

    /**
     *
     * @param user 只需要PhoneNum
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/getApplyListCreateByMe",method = RequestMethod.GET)
    public List<Apply> getApplyListCreateByMe(User user){
        return applyService.getApplyListCreateByMe(user);
    }

    /**
     *
     * @param user 只需要PhoneNum
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/getFollowList",method = RequestMethod.GET)
    public List<Follow> getFollowList(User user){
        return applyService.getFollowList(user);
    }

    /**
     *
     * @param user 只需要PhoneNum
     * @return 0为失败，1为成功
     */
    @RequestMapping(value = "/getMentionList",method = RequestMethod.GET)
    public List<Mention> getMentionList(User user){
        return applyService.getMentionList(user);
    }

    @RequestMapping(value = "/getNewest5Apply",method = RequestMethod.GET)
    public List<Apply> getNewest5Apply(){
        return applyService.getNewest5Apply();
    }

}
