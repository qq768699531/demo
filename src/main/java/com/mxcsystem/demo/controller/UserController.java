package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.base.Follow;
import com.mxcsystem.demo.entity.base.User;
import com.mxcsystem.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

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
