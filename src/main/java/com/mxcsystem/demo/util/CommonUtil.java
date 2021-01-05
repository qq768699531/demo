package com.mxcsystem.demo.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zhenzi.sms.ZhenziSmsClient;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    /**
     * 生成验证码图片
     * @param request 设置session
     * @param response 转成图片
     * @param captchaProducer 生成图片方法类
     * @param validateSessionKey session名称
     * @throws Exception 错误
     */
    public static void validateCode(HttpServletRequest request,
                                    HttpServletResponse response,
                                    DefaultKaptcha captchaProducer,
                                    String validateSessionKey) throws Exception{
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        response.setHeader("sessionId",request.getSession().getId());

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        // store the text in the session
        request.getSession().setAttribute(validateSessionKey, capText);
        //System.out.println(request.getSession().getAttribute(validateSessionKey));

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    public static void phoneValidateCode(HttpServletRequest request,
                                         HttpServletResponse response,
                                         DefaultKaptcha captchaProducer,
                                         String validateSessionKey,
                                         String phoneNumber){
        String verifyCode = captchaProducer.createText();
        ZhenziSmsClient client = new ZhenziSmsClient(
                "https://sms_developer.zhenzikj.com/",
                "107367",
                "32bc3e2b-c902-4d8d-ae07-3dca14b46875");
        Map<String,Object> map = new HashMap<>();

        map.put("number",phoneNumber);
        map.put("templateId","2587");
        map.put("templateParams", new String[]{verifyCode, "5分钟"});
        String result = "";
        String balance = "";
        try {
            result = client.send(map);
            balance = client.balance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("result = " + result);
        System.out.println("balance = " + balance);

        String[] key = {phoneNumber,verifyCode};
        request.getSession().setAttribute(validateSessionKey,key);
        response.setHeader("sessionId",request.getSession().getId());

        System.out.println(verifyCode);
    }

}
