package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.entity.HasShareNote;

public interface HasShareNoteService extends BaseService<HasShareNote>{

	List<HasShareNote> findByUserId(String userId);
	
}
