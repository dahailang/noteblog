package com.pursuit.noteblog.repository;

import java.util.List;

import com.pursuit.noteblog.entity.HasShareNote;

public interface HasShareNotesRepository extends BaseRepository<HasShareNote> {
	
	public List<HasShareNote> findByUserId(String userId);
	public List<HasShareNote> findByToUserId(String toUserId);
}
