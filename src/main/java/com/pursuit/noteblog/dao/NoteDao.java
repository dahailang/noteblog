package com.pursuit.noteblog.dao;

import java.util.List;

import com.pursuit.noteblog.entity.Note;

/**
 * 用户表dao
 */
public interface NoteDao {
	
	public Note addNote(Note note);
    public Note updateNote(Note note);
    public void deleteNote(String noteid);
    public List<Note> getNoteByUid(String uid);
    public List<Note> getNoteByNoteBookId(String noteBookId);

}
