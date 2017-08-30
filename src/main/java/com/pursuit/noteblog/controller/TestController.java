package com.pursuit.noteblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pursuit.noteblog.service.NoteConfigService;

@Controller
public class TestController {
	@Autowired
	NoteConfigService noteConfigService;
	@RequestMapping("/config/reload")
	public void test(){
		noteConfigService.reloadSystemConfig();
	}
}
