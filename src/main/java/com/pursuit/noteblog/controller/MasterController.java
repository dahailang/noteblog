package com.pursuit.noteblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.web.NBResult;
@RestController
@RequestMapping("/master")
public class MasterController extends BaseController{
	
	
	@RequestMapping("/allinfo")
	public NBResult getAllInfo(HttpServletRequest request){
		NoteUser user = getUserInfo(request);
		NBResult result =  NBResult.ok();
		result.addAttribute("userinfo", user);
		return result;
	}
	@RequestMapping("/layout")
	public NBResult layout(HttpServletRequest request,HttpServletResponse response){
		userLoginStatusService.removeLoginStatus(request,response);
		return NBResult.ok();
	}
}
