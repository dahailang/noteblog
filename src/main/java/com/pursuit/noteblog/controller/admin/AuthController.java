package com.pursuit.noteblog.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pursuit.noteblog.controller.BaseController;
import com.pursuit.noteblog.enums.LoginEnum;
import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.service.NoteUserService;
import com.pursuit.noteblog.web.WebResult;
import com.pursuit.noteblog.web.conversation.UserLoginStatusService;


@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{

    @Autowired
    private NoteUserService noteUserService;
    @Autowired
    private UserLoginStatusService userLoginStatusService;

    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    public WebResult doRegister(HttpServletRequest request,HttpServletResponse response,@RequestParam("email") String email,@RequestParam("pwd") String pwd,@RequestParam("iu")String fromUserId) {
    	NoteUser user = new NoteUser();
    	
    	user.setEmail(email.toLowerCase());//转为小写存储
    	user.setPassword(pwd);
    	String registerResult = noteUserService.register(email,pwd, fromUserId);
    	userLoginStatusService.addLoginStatus(request, response, "");
        //注册成功即登录
        return WebResult.ok();
    }
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public WebResult doLogin(HttpServletRequest request,HttpServletResponse response,String email,String pwd,String captcha) {
    	String userid = noteUserService.doLogin(email, pwd);
    	if(!LoginEnum.LOGIN_WRONG_USER_OR_PASSWORD.value().equals(userid)){
    		// 登录成功写入登录状态
    		userLoginStatusService.addLoginStatus(request, response, userid);
    		return WebResult.ok();
    	}
    	return WebResult.fail("用户名密码不正确");
    }
}