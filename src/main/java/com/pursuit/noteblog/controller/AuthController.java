package com.pursuit.noteblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.service.NoteUserService;
import com.pursuit.noteblog.web.NBResult;
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{

    @Autowired
    private NoteUserService noteUserService;

    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    public NBResult doRegister(HttpServletRequest request,HttpServletResponse response,@RequestParam("email") String email,@RequestParam("pwd") String pwd,@RequestParam("iu")String fromUserId) {
    	NoteUser noteUser =noteUserService.doRegister(email,pwd, fromUserId);
    	userLoginStatusService.addLoginStatus(request, response, noteUser.getId());
        //注册成功即登录
        return NBResult.ok();
    }
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public NBResult doLogin(HttpServletRequest request,HttpServletResponse response,String email,String pwd,String captcha) {
    	NBResult result = noteUserService.doLogin(email, pwd);
    	if(result.isOk()){
    		// 登录成功写入登录状态 msg为用户id
    		userLoginStatusService.addLoginStatus(request, response, result.getMsg());
    	}
    	
    	return result;
    }
}