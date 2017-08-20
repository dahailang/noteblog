package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.entity.NoteBook;

public interface NoteBookService {
	
	public NoteBook save(NoteBook notebook);
	public NoteBook renameNoteBook(NoteBook notebook);
	public List<NoteBook> findByUserid(String userId);
}
