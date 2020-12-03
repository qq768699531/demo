package com.mxcsystem.demo.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.mxcsystem.demo.service.LoginService;
import com.mxcsystem.demo.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Resource
    private DefaultKaptcha captchaProducer;
    /**
     * 登录验证码SessionKey
     */
    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";
    /**
     * 登录验证码图片
     */
    @RequestMapping("/loginValidateCode")
    public void loginValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        CommonUtil.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE);
    }

    /**
     * 检查验证码是否正确
     */
    @RequestMapping("/checkLoginValidateCode")
    @ResponseBody
    public HashMap<String,Object> checkLoginValidateCode(HttpServletRequest request,
                                          @RequestParam("validateCode")String validateCode) {
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        HashMap<String,Object> map = new HashMap<>();
        if(loginValidateCode == null){
            map.put("status",null);//验证码过期
        }else if(loginValidateCode.equals(validateCode)){
            map.put("status",true);//验证码正确
        }else {
            map.put("status",false);//验证码不正确
        }
        map.put("code",200);
        return map;
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public HashMap<String,Object> checkLogin(HttpServletRequest request,
                              @RequestParam("validateCode")String validateCode,
                              @RequestParam("username")String username,
                              @RequestParam("password")String password){
        HashMap<String,Object> map = new HashMap<>();
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        if(loginValidateCode == null){
            map.put("validateStatus",null);//验证码过期
        }else if(loginValidateCode.equals(validateCode)){
            map.put("validateStatus",true);//验证码正确
            if(loginService.checkLogin(username, password)){
                map.put("loginStatus","true");
                //保存登陆凭证map
                request.getSession().setAttribute("identity",map);
            }else{
                map.put("loginStatus","false");
            }
        }else {
            map.put("validateStatus",false);//验证码不正确
        }
        return map;
    }
}
