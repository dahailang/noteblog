package com.pursuit.noteblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.service.NoteContentService;
import com.pursuit.noteblog.web.NBResult;
@Controller
public class NoteController extends BaseController{
	
	@Autowired
	private NoteContentService noteContentService;
	
	@RequestMapping("")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Model model,String noteId){
    	
		model.addAttribute("title", "Leanote, "+getMessage("moto"));
		NoteUser user = (NoteUser) request.getAttribute("userInfo");
		NBResult noteResut = noteContentService.index(user,noteId);
		model.addAllAttributes(noteResut.getAttributes());
		return new ModelAndView("/note/note-new");
	}
	
	//进行分支合并操作
	@RequestMapping("/note/{noteId}")
	public ModelAndView note(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable("noteId")String noteId){
		model.addAttribute("title", "Leanote, "+getMessage("moto"));
		NoteUser user = (NoteUser) request.getAttribute("userInfo");
		NBResult noteResut = noteContentService.index(user,noteId);
		model.addAllAttributes(noteResut.getAttributes());
		return new ModelAndView("/note/note-new");
	}
}
