package com.mxcsystem.demo.util;

import com.mxcsystem.demo.constant.SystemConstant;
import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.User;
import com.mxcsystem.demo.entity.WX.WXMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WXUtil {
    public static String getAccessToken(){
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();

        wxStorage.setAppId(SystemConstant.APP_ID);
        wxStorage.setSecret(SystemConstant.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        try {
            String token = wxMpService.getAccessToken();
            System.out.println(token);
            return token;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getOpenID(WXMessage entity){
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();

        wxStorage.setAppId(SystemConstant.APP_ID);
        wxStorage.setSecret(SystemConstant.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        try {
            WxMpOAuth2AccessToken token = wxMpService.oauth2getAccessToken(entity.getCode());
            System.out.println(token.getOpenId());
            return token.getOpenId();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendApproveMsg(WXMessage wxMessage){
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();

        wxStorage.setAppId(SystemConstant.APP_ID);
        wxStorage.setSecret(SystemConstant.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        String color = "#000000";//黑色
        //color = "#001EFF";蓝色
        //color = "#FF0000";红色
        //color = "#FFFF00";黄色

        //2-推送消息
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(SystemConstant.MSG_TEMPLATE_ID);

        //点击模版消息要访问的网址，注释掉之后就不会有点击跳转
        //String alarmPath ="https://wx.navimentum.com/demos/qa/shangbaoDetail.html?openid="+entity.getOpenid()+"&&id="+entity.getId();
        //templateMessage.setUrl(alarmPath);

        List<WxMpTemplateData> wxMpTemplateData = new ArrayList<>();

        wxMpTemplateData.add(new WxMpTemplateData("thing1", wxMessage.getTitle(), color));
        wxMpTemplateData.add(new WxMpTemplateData("name2", wxMessage.getAssignTo(), color));
        wxMpTemplateData.add(new WxMpTemplateData("thing3", wxMessage.getThing(), color));
        wxMpTemplateData.add(new WxMpTemplateData("name4", wxMessage.getApplyer(), color));
        wxMpTemplateData.add(new WxMpTemplateData("date5", wxMessage.getDate(), color));
        // wxMpTemplateData.add(new WxMpTemplateData("remark", "领航磐石产品部", color));
        templateMessage.setData(wxMpTemplateData);
        try {
            //要推送的用户openid
            templateMessage.setToUser(wxMessage.getOpenid());
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            //System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
