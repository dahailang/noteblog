package com.pursuit.noteblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pursuit.noteblog.dao.NoteBookMapper;
import com.pursuit.noteblog.po.NoteBook;
import com.pursuit.noteblog.service.NoteBookService;

public class NotebookeServiceImpl implements NoteBookService {
	
	@Autowired
	NoteBookMapper noteBookMapper;

	@Override
	public NoteBook addNoteBook(NoteBook notebook) {
		noteBookMapper.insert(notebook);
		return notebook;
	}

	@Override
	public NoteBook renameNoteBook(NoteBook notebook) {
		noteBookMapper.updateByPrimaryKey(notebook);
		return notebook;
	}

	@Override
	public List<NoteBook> findByUserid(String userId) {
		return noteBookMapper.selectByUid(userId);
	}
}
