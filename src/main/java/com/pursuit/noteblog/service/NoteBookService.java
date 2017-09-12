package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.po.NoteBook;
import com.pursuit.noteblog.web.NBResult;

public interface NoteBookService {
	public void addUserDefaultBook(String uid);
	public NBResult getLeftTree(String userId);
	public NoteBook addNoteBook(NoteBook notebook);
	public NoteBook renameNoteBook(NoteBook notebook);
	public List<NoteBook> findByUserid(String userId);
}
