package com.pursuit.noteblog.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pursuit.noteblog.web.interceptor.AppInterceptor;
import com.pursuit.noteblog.web.interceptor.FrontUserLoginInterceptor;
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/html/index.html");
	}
	@Bean
	public FrontUserLoginInterceptor frontUserLoginInterceptor(){
		return new FrontUserLoginInterceptor();
	}
	@Bean
	public AppInterceptor appInterceptor(){
		return new AppInterceptor();
	}
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		HandlerInterceptor interceptor = frontUserLoginInterceptor();
//		registry.addInterceptor(interceptor).
//			addPathPatterns("/**").
//			excludePathPatterns("/").
//			excludePathPatterns("/index").
//			excludePathPatterns("/index.html").
//			excludePathPatterns("/login").
//			excludePathPatterns("/auth/doLogin").
//			excludePathPatterns("/auth/doRegister").
//			excludePathPatterns("/register");
//		//公共过滤器
//		registry.addInterceptor(appInterceptor());
//	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
//		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//		internalResourceViewResolver.setOrder(0);
//		internalResourceViewResolver.setPrefix("/");
//		internalResourceViewResolver.setSuffix(".html");
//		registry.viewResolver(internalResourceViewResolver);
	}
	
}
