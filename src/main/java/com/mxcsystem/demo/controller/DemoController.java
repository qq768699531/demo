package com.mxcsystem.demo.controller;

import com.mxcsystem.demo.entity.WX.WXMessage;
import com.mxcsystem.demo.util.WXUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DemoController {
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

    @RequestMapping("getAccessToken")
    @ResponseBody
    public String getAccessToken(){
        return WXUtil.getAccessToken();
    }

    @RequestMapping("saveOpenID")
    @ResponseBody
    public String getOpenID(String code){
        //System.out.println(code);
        WXMessage wxMessage = new WXMessage();
        wxMessage.setCode(code);
        return WXUtil.getOpenID(wxMessage);
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(){

        return "false";
    }
}
