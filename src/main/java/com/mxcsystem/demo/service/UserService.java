package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.base.Follow;
import com.mxcsystem.demo.entity.base.User;
import com.mxcsystem.demo.mapper.FollowMapper;
import com.mxcsystem.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final FollowMapper followMapper;

    public UserService (UserMapper userMapper, FollowMapper followMapper) {
        this.userMapper = userMapper;
        this.followMapper = followMapper;
    }

    public User getUserInfo(User user){
        return userMapper.getUserInfoByPhoneNum(user.getPhoneNum());
    }

    public List<User> getGroupMember (User user) {
        return userMapper.getGroupMember(user);
    }

    public List<String> getUsername(){
        return userMapper.getUsername();
    }

    public List<User> getGroupManager (User user) {
        return userMapper.getGroupManager(user);
    }

    public String getUserOpenID(User user){
        return userMapper.getUserOpenID(user);
    }

    public int insertIntoOpenID (String openid, String phoneNum) {
        User user = new User();
        user.setPhoneNum(phoneNum);
        if(userMapper.getUserOpenID(user) == null){
            return userMapper.insertOpenID(openid,phoneNum);
        }else{
            return userMapper.updateOpenID(openid,phoneNum);
        }
    }

    public void insertUser (User user) {
        if(userMapper.getUserInfoByPhoneNum(user.getPhoneNum()) == null){
            userMapper.insertUser(user);
        }
    }

    public List<Follow> getMyFollow (User user) {
        return followMapper.getFollowListByPhoneNumber(user);
    }
}
