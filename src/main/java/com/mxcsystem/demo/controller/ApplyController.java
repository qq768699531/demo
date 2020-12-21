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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    //用户创建新审批
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public int createNewApply(Apply apply){
        return applyService.createNewApply(apply);
    }

    //用户修改未提交的审批
    @RequestMapping(value = "/applyerOwnerUpdate",method = RequestMethod.POST)
    public int applyerOwnerUpdate(Apply apply){
        return applyService.updateApplyByApplyerOwner(apply);
    }

    //用户提交审批,0为提交失败
    @RequestMapping(value = "/applyerSubmitApply",method = RequestMethod.POST)
    public int applyerSubmitApply(Apply apply){
        return applyService.submitApplyByApplyerOwner(apply.getID());
    }

    @RequestMapping(value = "/getApplyListAssignToMe",method = RequestMethod.GET)
    public List<Apply> getApplyListAssignToMe(User user){
        return applyService.getApplyListAssignToMe(user);
    }

    @RequestMapping(value = "/getApplyListCreateByMe",method = RequestMethod.GET)
    public List<Apply> getApplyListCreateByMe(User user){
        return applyService.getApplyListCreateByMe(user);
    }

    @RequestMapping(value = "/getFollowList",method = RequestMethod.GET)
    public List<Follow> getFollowList(User user){
        return applyService.getFollowList(user);
    }

    @RequestMapping(value = "/getMentionList",method = RequestMethod.GET)
    public List<Mention> getMentionList(User user){
        return applyService.getMentionList(user);
    }
}
