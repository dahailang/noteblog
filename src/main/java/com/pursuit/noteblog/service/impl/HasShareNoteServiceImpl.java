package com.pursuit.noteblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.entity.HasShareNote;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.repository.HasShareNotesRepository;
import com.pursuit.noteblog.service.HasShareNoteService;

public class HasShareNoteServiceImpl implements HasShareNoteService {
	@Autowired
	HasShareNotesRepository hasShareNotesRepository;

	@Override
	public List<HasShareNote> findByUserId(String userId) {
		return hasShareNotesRepository.findByUserId(userId);
	}
	
	@Override
	public HasShareNote findById(String userId) {
		return null;
	}

	@Override
	public HasShareNote save(HasShareNote hasShareNote) {
		return hasShareNotesRepository.save(hasShareNote);
	}

	@Override
	public SuccessEnum update(HasShareNote e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessEnum deleteBy(String id) {
		hasShareNotesRepository.delete(id);
		return null;
	}

}
