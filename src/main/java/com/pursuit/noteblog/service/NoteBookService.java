package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.po.NoteBook;
import com.pursuit.noteblog.vo.TreeNode;
import com.pursuit.noteblog.web.NBResult;

public interface NoteBookService {
	public void addUserDefaultBook(String uid);
	public NBResult addNoteBook(String uid,TreeNode node);
	public NBResult deleteNoteBook(String nid);
	public NoteBook renameNoteBook(NoteBook notebook);
  	
	
	public NBResult getLeftTree(String userId);
	public NoteBook addNoteBook(NoteBook notebook);
	public List<NoteBook> findByUserid(String userId);
}
