package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.Log;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    //用户创建新日志,返回日志ID为成功,0为失败
    @RequestMapping(value = "/createNewLog",method = RequestMethod.POST)
    public int createNewApply(Log log,int isFollow,User user){
        int result = logService.createNewLog(log);
        if(result == 1){
            logService.insertMentionsFromLog(log);
            logService.insertLinksFromLog(log);
            if(isFollow == 1){
                logService.insertFollowFromLog(log,user);
            }
        }
        return result;
    }

    //修改草稿,返回1为成功,0为失败
    @RequestMapping(value = "/updateLogWhileNotSubmit",method = RequestMethod.POST)
    public int updateLogWhileNotSubmit(Log log){
        int result = logService.updateLogWhileNotSubmit(log);
        if(result == 1){
            //删除后重新插入，因为可能会有修改，前端没有保存修改前数据
            logService.deleteMentionsByLogID(log);
            logService.insertMentionsFromLog(log);
            logService.deleteLinksByLogID(log);
            logService.insertLinksFromLog(log);
        }
        return result;
    }

    //通过日志ID获取日志
    @RequestMapping(value = "/getLogByID",method = RequestMethod.GET)
    public Log getLogByID(Log log){
        return logService.getLogByID(log);
    }

    //删除草稿,返回1为成功,0为失败
    @RequestMapping(value = "/deleteLogWhileNotSubmit",method = RequestMethod.POST)
    public int deleteLogWhileNotSubmit(Log log){
        return logService.deleteLogWhileNotSubmit(log);
    }

    //提交草稿,返回1为成功,0为失败,只需要填写ID,当然填满也没事
    @RequestMapping(value = "/submitLog",method = RequestMethod.POST)
    public int submitLog(Log log){
        return logService.deleteLogWhileNotSubmit(log);
    }

    //根据用户手机号码获取日志记录
    @RequestMapping(value = "/getLogListByPhoneNum",method = RequestMethod.POST)
    public List<Log> getLogListByPhoneNum(User user){
        return logService.getLogListByPhoneNum(user);
    }

    @RequestMapping(value = "/updateLogStatusByLogID",method = RequestMethod.POST)
    public int updateLogStatusByLogID(Log log){
        return logService.updateLogStatusByLogID(log);
    }
}
