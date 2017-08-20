package com.pursuit.noteblog.dao;

import com.pursuit.noteblog.entity.Note;
import com.pursuit.noteblog.entity.NoteContent;

/**
 * 用户表dao
 */
public interface NoteContentDao {
	
	public Note addNoteContent(NoteContent noteContent);
    public Note updateNoteContent(NoteContent noteContent);
    public void deleteNoteContent(String noteid);
    public NoteContent getNoteContentByNoteiId(String noteid);

}
