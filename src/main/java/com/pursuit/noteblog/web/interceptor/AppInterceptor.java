package com.pursuit.noteblog.web.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pursuit.noteblog.util.ConstUtils;

public class AppInterceptor extends HandlerInterceptorAdapter{


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		setLocale(request);
	}
	
	 //设置本地化信息
    public void setLocale(HttpServletRequest request){
    	Locale locale = request.getLocale(); // zh_CN
    	String lang = locale.getLanguage()+"_"+locale.getCountry();

        request.setAttribute("locale", lang);
        request.setAttribute("uri", request.getRequestURI());
        request.setAttribute("siteUrl", ConstUtils.SITEURL);
        request.setAttribute("blogUrl", ConstUtils.BLOGURL);
        request.setAttribute("leaUrl", ConstUtils.LEAURL);
        request.setAttribute("noteUrl", ConstUtils.NOTEURL);
        request.setAttribute("openRegister", ConstUtils.OPEN_REGISTER);
    }
    
}
