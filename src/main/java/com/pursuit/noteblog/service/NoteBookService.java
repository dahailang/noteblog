package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.po.NoteBook;

public interface NoteBookService {
	
	public NoteBook addNoteBook(NoteBook notebook);
	public NoteBook renameNoteBook(NoteBook notebook);
	public List<NoteBook> findByUserid(String userId);
}
