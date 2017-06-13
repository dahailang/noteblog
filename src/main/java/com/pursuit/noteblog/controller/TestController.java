package com.pursuit.noteblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pursuit.noteblog.service.SystemConfigService;

@Controller
public class TestController {
	@Autowired
	SystemConfigService configService;
	@RequestMapping("/config/reload")
	public void test(){
		configService.reloadSystemConfig();
	}
}
