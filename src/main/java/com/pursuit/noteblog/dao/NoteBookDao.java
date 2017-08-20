package com.pursuit.noteblog.dao;

import java.util.List;

import com.pursuit.noteblog.entity.NoteBook;

/**
 * 用户表dao
 */
public interface NoteBookDao {

	public NoteBook addNoteBook(NoteBook notebook);
    public NoteBook updateNoteBook(NoteBook notebook);
    public void deleteNoteBook(String notebookid);
    public List<NoteBook> getNoteBookByUid(String uid);

}
