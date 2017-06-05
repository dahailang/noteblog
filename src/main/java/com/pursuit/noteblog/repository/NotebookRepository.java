package com.pursuit.noteblog.repository;
import java.util.List;

import com.pursuit.noteblog.entity.Notebook;

public interface NotebookRepository extends BaseRepository<Notebook> {
    
	public List<Notebook> findByUserId(String userId);
}