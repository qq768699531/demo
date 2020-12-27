package com.mxcsystem.demo.util;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.alibaba.fastjson.JSONObject;
import com.mxcsystem.demo.constant.SystemConstant;
import com.mxcsystem.demo.entity.WX.WXMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WXUtil {
    private WxMaMsgService msgService;

    public String getAccessToken () throws WxErrorException {
        WxMaDefaultConfigImpl wxStorage = new WxMaDefaultConfigImpl();

        wxStorage.setAppid(SystemConstant.APP_ID);
        wxStorage.setSecret(SystemConstant.APP_SECRET);

        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxStorage);

        msgService = wxMaService.getMsgService();

        String token = wxMaService.getAccessToken();
        System.out.println(token);
        return token;
    }

    public String getOpenID (WXMessage wxMessage) {
        CloseableHttpClient client = HttpClients.createDefault();
        String url =
                String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                        SystemConstant.APP_ID, SystemConstant.APP_SECRET, wxMessage.getCode());
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //读取服务器返回过来的json字符串数据
                String strResult = EntityUtils.toString(response.getEntity());
                //把json字符串转换成json对象
                JSONObject jsonResult = (JSONObject) JSONObject.parse(strResult);
                System.out.println(jsonResult.getString("openid"));
                return jsonResult.getString("openid");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * description: 发送订阅消息
     * param: openId 用户的openid
     * param: templateId 模板id
     * param: dataParam 参数内容
     */
    public void sendSubscribeMsg(String openId, String templateId, List<WxMaSubscribeMessage.Data> dataParam) throws WxErrorException {

        // 3.8.0版本使用的使用WxMaSubscribeMessage
        WxMaSubscribeMessage.WxMaSubscribeMessageBuilder builder = WxMaSubscribeMessage.builder();

        builder.toUser(openId);//推送消息的目标对象openId
        builder.templateId(templateId); //这里填写的就是在后台申请添加的模板ID
        builder.data(dataParam);//添加请求参数
        WxMaSubscribeMessage msg = builder.build();
        msgService.sendSubscribeMsg(msg);
    }

    public void sendSubscribeMsg (WXMessage wxMessage) throws WxErrorException {
        List<WxMaSubscribeMessage.Data> dataParam = new ArrayList<>();
        dataParam.add(new WxMaSubscribeMessage.Data("thing1",wxMessage.getTitle()));
        dataParam.add(new WxMaSubscribeMessage.Data("name2", wxMessage.getAssignTo()));
        dataParam.add(new WxMaSubscribeMessage.Data("thing3", wxMessage.getThing()));
        dataParam.add(new WxMaSubscribeMessage.Data("name4", wxMessage.getApplyer()));
        dataParam.add(new WxMaSubscribeMessage.Data("date5", wxMessage.getDate()));
        WxMaSubscribeMessage.WxMaSubscribeMessageBuilder builder = WxMaSubscribeMessage.builder();

        builder.toUser(wxMessage.getOpenid());//推送消息的目标对象openId
        builder.templateId(SystemConstant.MSG_TEMPLATE_ID); //这里填写的就是在后台申请添加的模板ID
        builder.data(dataParam);//添加请求参数
        WxMaSubscribeMessage msg = builder.build();
        msgService.sendSubscribeMsg(msg);
    }
}
