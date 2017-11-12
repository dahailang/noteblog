package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.po.NoteContent;
import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.web.NBResult;

public interface NoteContentService{
	public NBResult index(NoteUser noteUser,String noteId);
	public List<NoteContent> findByUserId(String userId);
	public NoteContent getNoteContent(String noteId);
	public NBResult saveContent(String noteId,String noteBookId,String content);
}
