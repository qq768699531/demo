package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("user/set")
    public User getUserInfo(HttpServletRequest request,String PhoneNum){
        return userService.getUserInfo(PhoneNum);
    }
}
