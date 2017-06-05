package com.pursuit.noteblog.repository;
import java.util.List;

import com.pursuit.noteblog.entity.Note;

public interface NoteRepository extends BaseRepository<Note> {
	public List<Note> findByUserId(String userId);
}