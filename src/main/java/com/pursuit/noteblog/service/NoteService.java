package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.entity.Note;
import com.pursuit.noteblog.entity.NoteContent;
import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.web.WebResult;

public interface NoteService{
	public WebResult index(User user,String noteId);
	public List<Note> findByUserId(String userId);
	public NoteContent getNoteContent(String noteId);
}
