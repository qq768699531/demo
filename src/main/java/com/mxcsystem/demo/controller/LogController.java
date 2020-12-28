package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.Log;
import com.mxcsystem.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    //用户创建新日志,返回日志ID为成功,0为失败
    @RequestMapping(value = "/createNewLog",method = RequestMethod.POST)
    public int createNewApply(Log log){
        return logService.createNewLog(log);
    }

    @RequestMapping(value = "/getLogByID",method = RequestMethod.GET)
    public Log getLogByID(Log log){
        return logService.getLogByID(log);
    }
}
