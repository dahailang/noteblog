package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.entity.Notebook;

public interface NotebookService {
	
	public Notebook save(Notebook notebook);
	public List<Notebook> findByUserid(String userId);
}
