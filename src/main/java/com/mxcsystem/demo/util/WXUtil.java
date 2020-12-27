package com.mxcsystem.demo.util;

import com.mxcsystem.demo.constant.SystemConstant;
import com.mxcsystem.demo.entity.WX.WXMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
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

    public static void sendApproveMsg(WXMessage entity){
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

        wxMpTemplateData.add(new WxMpTemplateData("通知", "你被@了", color));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(new Date());
        //下面的keyword1对应模板上的位置
        wxMpTemplateData.add(new WxMpTemplateData("keyword1", startTime, color));
        wxMpTemplateData.add(new WxMpTemplateData("keyword2", entity.getQuestion(), color));
        wxMpTemplateData.add(new WxMpTemplateData("keyword3", entity.getAnswer(), color));
        // wxMpTemplateData.add(new WxMpTemplateData("remark", "领航磐石产品部", color));
        templateMessage.setData(wxMpTemplateData);
        try {
            //要推送的用户openid
            templateMessage.setToUser(entity.getOpenid());
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            //System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
