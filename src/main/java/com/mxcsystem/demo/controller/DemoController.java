package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.WX.WXMessage;
import com.mxcsystem.demo.service.UserService;
import com.mxcsystem.demo.util.WXUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DemoController {
    private final UserService userService;

    public DemoController (UserService userService) {
        this.userService = userService;
    }

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
    public String getOpenID(String code,String phoneNum){
        //System.out.println(code);
        WXMessage wxMessage = new WXMessage();
        wxMessage.setCode(code);
        WXUtil wxUtil = new WXUtil();
        String openid = wxUtil.getOpenID(wxMessage);
        userService.insertIntoOpenID(openid,phoneNum);
        return openid;
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(WXMessage wxMessage){
        WXUtil wxUtil = new WXUtil();
        wxUtil.setMsgService();
        try {
            wxUtil.sendSubscribeMsg(wxMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }
}
