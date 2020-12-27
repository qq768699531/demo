package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(User user){
        return userMapper.getUserInfo(user);
    }

    public List<User> getGroupMember (User user) {
        return userMapper.getGroupMember(user);
    }

    public List<User> getGroupManager (User user) {
        return userMapper.getGroupManager(user);
    }

    public String getUserOpenID(User user){
        return userMapper.getUserOpenID(user);
    }
}
