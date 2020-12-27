package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.WX.WXMessage;
import com.mxcsystem.demo.mapper.UserMapper;
import com.mxcsystem.demo.util.WXUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DemoController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("test")
    @ResponseBody
    public String Test(){
        return "test";
    }

    @RequestMapping("login")
    @ResponseBody
    public String Login(){
        return "test";
    }

    @RequestMapping("/getAccessToken")
    @ResponseBody
    public String getAccessToken(){
        WXUtil wxUtil = new WXUtil();
        try {
            return wxUtil.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "false";
    }

    @RequestMapping("/saveOpenID")
    @ResponseBody
    public String getOpenID(String code){
        //System.out.println(code);
        WXMessage wxMessage = new WXMessage();
        wxMessage.setCode(code);
        WXUtil wxUtil = new WXUtil();
        return wxUtil.getOpenID(wxMessage);
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(){
        return "false";
    }
}
