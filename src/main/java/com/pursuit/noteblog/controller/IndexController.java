package com.pursuit.noteblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pursuit.noteblog.util.ConstUtils;

@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController{
	
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model){
    	//主页是否是管理员的博客页
    	if(ConstUtils.HOME_PAGE==1){
    		return new ModelAndView("/bolg/index?admin=id");
    	}
    	model.addAttribute("title", getMessage("login"));
    	model.addAttribute("subTitle", getMessage("login"));
    	return new ModelAndView("/home/index");
    }
    @RequestMapping(value = "/register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response, Model model){
    	model.addAttribute("title", getMessage("register"));
    	model.addAttribute("subTitle", getMessage("register"));
    	return new ModelAndView("/home/register");
    }
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Model model){
    	model.addAttribute("title", getMessage("login"));
    	model.addAttribute("subTitle", getMessage("login"));
    	return new ModelAndView("/home/login");
    }
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Model model){
    	request.getSession().invalidate();
    	return new ModelAndView("/home/login");
    }
    @RequestMapping(value = "/findPassword")
    public ModelAndView findPassword(HttpServletRequest request, HttpServletResponse response, Model model){
    	model.addAttribute("title", getMessage("findPassword"));
    	model.addAttribute("subTitle", getMessage("findPassword"));
    	return new ModelAndView("/home/find_password");
    }
    @RequestMapping(value = "/test/{str}")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable String str){
    	//TODO  主页是否是管理员的博客页
    	model.addAttribute("title", "noteblog");
    	model.addAttribute("user", getUserInfo(request));
    	model.addAttribute("openRegister", "1");//TODO 取配置表
    	//model.addAttribute("locale", getLocale(request));
    	return new ModelAndView("/note/fragment/"+str);
    }
}
