package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.Follow;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    public User getUserInfo(User user){
        return userService.getUserInfo(user);
    }

    @RequestMapping("/getGroupMember")
    public List<User> getGroupMember(User user){
        return userService.getGroupMember(user);
    }

    @RequestMapping("/getUsername")
    public List<String> getUsername(){
        return userService.getUsername();
    }

    @RequestMapping("/getGroupManager")
    public List<User> getGroupManager(User user){
        return userService.getGroupManager(user);
    }

    @RequestMapping("/getMyFollow")
    public List<Follow> getMyFollow(User user){
        return userService.getMyFollow(user);
    }
}
