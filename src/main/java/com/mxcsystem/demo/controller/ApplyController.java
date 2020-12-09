package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public int createNewApply(Apply apply){
        return applyService.createNewApply(apply);
    }

    @RequestMapping(value = "/applyerOwnerUpdate",method = RequestMethod.POST)
    public int applyerOwnerUpdate(Apply apply){
        return applyService.updateApplyByApplyerOwner(apply);
    }
}
