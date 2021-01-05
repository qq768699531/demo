package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.base.Worker;
import com.mxcsystem.demo.mapper.LoginMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginMapper loginMapper;

    public LoginService (LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public boolean checkLogin(String username,String password){
        return loginMapper.checkLogin(username,password);
    }

    public boolean checkLogin(Worker worker){
        return loginMapper.checkLogin(worker.getName(),worker.getPassword());
    }
}
