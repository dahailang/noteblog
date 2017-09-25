package com.pursuit.noteblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pursuit.noteblog.service.NoteBookService;
import com.pursuit.noteblog.web.NBResult;

@RestController
@RequestMapping("/note")
public class NoteResultController extends BaseController{
	@Autowired
	NoteBookService noteBookService;
	@RequestMapping("/treeall")
	public NBResult init(HttpServletRequest request, HttpServletResponse response,Model model,String noteId){
		String uid = getUid(request);
		NBResult leftTree = noteBookService.getLeftTree(uid);
		return leftTree;
	}
}
