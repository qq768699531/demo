package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(User user){
        return userMapper.getUserInfo(user);
    }
}
