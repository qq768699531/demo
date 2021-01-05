package com.mxcsystem.demo.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.mxcsystem.demo.entity.base.User;
import com.mxcsystem.demo.service.LoginService;
import com.mxcsystem.demo.service.UserService;
import com.mxcsystem.demo.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;
    @Resource
    private DefaultKaptcha captchaProducer;
    /**
     * 登录验证码SessionKey
     */
    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";

    public LoginController (LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    /**
     * 登录验证码图片
     */
    @RequestMapping("/loginValidateCode")
    public void loginValidateCode(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception{
        CommonUtil.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE);
        //System.out.println(request.getSession().getId());
        //return request.getSession().getId();
    }

    /**
     *
     * @param phone 手机号码
     */
    @RequestMapping("/sendSMS")
    public void sendSMS(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam("phone")String phone){
        CommonUtil.phoneValidateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE,phone);
    }

    @RequestMapping("/checkSession")
    public boolean checkSession(HttpServletRequest request) {
        return !request.getSession().isNew();
    }

    /**
     * 检查验证码是否正确
     */
    @RequestMapping("/checkLoginValidateCode")
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

    @SuppressWarnings("unchecked")
    @RequestMapping("/checkLogin")
    public HashMap<String,Object> checkLogin(HttpServletRequest request,
                                             @RequestParam("validateCode")String validateCode,
                                             @RequestParam("username")String username,
                                             @RequestParam("password")String password){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession().getAttribute("identity") != null){
            map = (HashMap<String,Object>) request.getSession().getAttribute("identity");
            return map;
        }
        String loginValidateCode = "";
        //System.out.println(request.getSession().getId());
        try{
            loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        }catch (NullPointerException e){
            map.put("validateStatus",null);//验证码过期
        }
        if(loginValidateCode.equals(validateCode)){
            map.put("validateStatus",true);//验证码正确
            if(loginService.checkLogin(username, password)){
                map.put("loginStatus",true);
                //保存登陆凭证map
                request.getSession().setAttribute("identity",map);
            }else{
                map.put("loginStatus",false);
            }
        }else {
            map.put("validateStatus",false);//验证码不正确
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/checkPhoneLogin")
    public HashMap<String,Object> checkPhoneLogin(HttpServletRequest request,
                                             @RequestParam("validateCode")String validateCode,
                                             @RequestParam("phone")String phone){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession().getAttribute("identity") != null){
            map = (HashMap<String,Object>) request.getSession().getAttribute("identity");
            return map;
        }
        String loginPhoneNum = "";
        String loginValidateCode = "";
        //System.out.println(request.getSession().getId());
        try{
            String[] key = (String[]) request.getSession().getAttribute(LOGIN_VALIDATE_CODE);
            loginPhoneNum = key[0];
            loginValidateCode = key[1];
        }catch (NullPointerException e){
            map.put("validateStatus",null);//验证码过期
        }
        if(loginValidateCode.equals(validateCode) && loginPhoneNum.equals(phone)){
            map.put("validateStatus",true);//验证码正确
            map.put("loginStatus",true);
            User user = new User();
            user.setPhoneNum(phone);
            if((user = userService.getUserInfo(user))== null){
                User new_user = new User();
                new_user.setPhoneNum(phone);
                new_user.setUsername("未知用户");
                new_user.setIsManager(0);
                new_user.setGroupID(0);
                userService.insertUser(new_user);
                map.put("user",new_user);
            }else{
                map.put("user",user);
            }

            //保存登陆凭证map
            request.getSession().setAttribute("identity",map);
        }else {
            map.put("validateStatus",false);//验证码不正确
        }
        return map;
    }
}
