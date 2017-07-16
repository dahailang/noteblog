package com.pursuit.noteblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.entity.Notebook;
import com.pursuit.noteblog.repository.NotebookRepository;
import com.pursuit.noteblog.service.NotebookService;
public class NotebookeServiceImpl implements NotebookService {
	@Autowired
	NotebookRepository notebookRepository;
	@Override
	public Notebook save(Notebook notebook){
		return notebookRepository.save(notebook);
	}
	@Override
	public List<Notebook> findByUserid(String userId){
		return notebookRepository.findByUserId(userId);
	}
}
