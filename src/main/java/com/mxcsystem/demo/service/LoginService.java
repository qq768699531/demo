package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.Worker;
import com.mxcsystem.demo.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public boolean checkLogin(String username,String password){
        return loginMapper.checkLogin(username,password);
    }

    public boolean checkLogin(Worker worker){
        return loginMapper.checkLogin(worker.getName(),worker.getPassword());
    }
}
