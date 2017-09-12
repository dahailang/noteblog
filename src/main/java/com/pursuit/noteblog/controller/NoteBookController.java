package com.pursuit.noteblog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pursuit.noteblog.service.NoteBookService;
import com.pursuit.noteblog.web.NBResult;

@RestController
@RequestMapping("/tree")
public class NoteBookController extends BaseController{
	@Autowired
	NoteBookService noteBookService;
	@RequestMapping(value="/lefttree",produces={"application/json;charset=UTF-8"})
	public NBResult getLeftTree(HttpServletRequest request){
		String uid = getUid(request);
		return noteBookService.getLeftTree(uid);
	}
}
