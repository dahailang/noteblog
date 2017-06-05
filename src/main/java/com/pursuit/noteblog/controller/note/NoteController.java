package com.pursuit.noteblog.controller.note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pursuit.noteblog.controller.BaseController;
import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.service.NoteService;
import com.pursuit.noteblog.web.WebResult;
@Controller
@RequestMapping("/note")
public class NoteController extends BaseController{
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping("")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Model model,String noteId){
    	
		model.addAttribute("title", "Leanote, "+getMessage("moto"));
		User user = (User) request.getAttribute("userInfo");
		WebResult noteResut = noteService.index(user,noteId);
		model.addAllAttributes(noteResut.getAttributes());
		return new ModelAndView("/note/note");
	}
	@RequestMapping("/{noteId}")
	public ModelAndView note(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable("noteId")String noteId){
		System.out.println(noteId);
		model.addAttribute("title", "Leanote, "+getMessage("moto"));
		User user = (User) request.getAttribute("userInfo");
		WebResult noteResut = noteService.index(user,noteId);
		model.addAllAttributes(noteResut.getAttributes());
		return new ModelAndView("/note/note");
	}
}
