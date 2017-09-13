package com.pursuit.noteblog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pursuit.noteblog.service.NoteBookService;
import com.pursuit.noteblog.vo.TreeNode;
import com.pursuit.noteblog.web.NBResult;

@RestController
@RequestMapping("/tree")
public class NoteBookController extends BaseController{
	@Autowired
	NoteBookService noteBookService;
	@RequestMapping(value="/lefttree",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object getLeftTree(HttpServletRequest request){
		String uid = getUid(request);
		NBResult leftTree = noteBookService.getLeftTree(uid);
		return leftTree.getInfo();
	}
	@RequestMapping(value="/addnotebook",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object addNoteBook(HttpServletRequest request,TreeNode node){
		String uid = getUid(request);
		NBResult result = noteBookService.addNoteBook(uid, node);
		return result.getInfo();
	}
	@RequestMapping(value="/renameNoteBook",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object renameNoteBook(HttpServletRequest request,TreeNode node){
		String uid = getUid(request);
		NBResult result = noteBookService.addNoteBook(uid, node);
		return result.getInfo();
	}
	@RequestMapping(value="/deleteNoteBook",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object deleteNoteBook(HttpServletRequest request,TreeNode node){
		String uid = getUid(request);
		NBResult result = noteBookService.addNoteBook(uid, node);
		return result.getInfo();
	}
}
