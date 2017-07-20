package com.pursuit.noteblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.dao.NoteBookDao;
import com.pursuit.noteblog.entity.NoteBook;
import com.pursuit.noteblog.service.NoteBookService;

public class NotebookeServiceImpl implements NoteBookService {
	@Autowired
	NoteBookDao noteBookDao;

	@Override
	public NoteBook save(NoteBook notebook) {
		return noteBookDao.addNoteBook(notebook);
	}

	@Override
	public NoteBook renameNoteBook(NoteBook notebook) {
		return noteBookDao.updateNoteBook(notebook);
	}

	@Override
	public List<NoteBook> findByUserid(String userId) {
		return noteBookDao.getNoteBookByUid(userId);
	}
}
