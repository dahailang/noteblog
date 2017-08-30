package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.po.NoteContent;
import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.web.WebResult;

public interface NoteContentService{
	public WebResult index(NoteUser noteUser,String noteId);
	public List<NoteContent> findByUserId(String userId);
	public NoteContent getNoteContent(String noteId);
}
