package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.Log;
import com.mxcsystem.demo.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public int createNewLog(Log log){
        return logMapper.createNewLog(log);
    }
}
