package com.pursuit.noteblog.repository;
import java.util.List;

import com.pursuit.noteblog.entity.Notebook;

public interface NoteRepository extends BaseRepository<Notebook> {
    
	public List<Notebook> findByUserId(String userId);
}