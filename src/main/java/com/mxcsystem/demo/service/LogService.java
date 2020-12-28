package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.Log;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public int createNewLog(Log log){
        return logMapper.createNewLog(log);
    }

    public int updateLogWhileNotSubmit(Log log){
        return logMapper.updateLogWhileNotSubmit(log);
    }

    public int deleteLogWhileNotSubmit(Log log){
        return logMapper.deleteLogWhileNotSubmit(log);
    }

    public int submitLog(Log log){
        return logMapper.submitLog(log.getID());
    }

    public Log getLogByID(Log log){
        return logMapper.getLogByID(log.getID());
    }

    public List<Log> getLogListByPhoneNum(User user){
        return logMapper.getLogListByPhoneNum(user.getPhoneNum());
    }

}
