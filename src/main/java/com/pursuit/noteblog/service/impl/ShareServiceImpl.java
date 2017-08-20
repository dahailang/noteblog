package com.pursuit.noteblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pursuit.noteblog.entity.HasShareNote;
import com.pursuit.noteblog.entity.ShareNotebook;
import com.pursuit.noteblog.entity.ShareNotes;
import com.pursuit.noteblog.service.HasShareNoteService;
import com.pursuit.noteblog.service.ShareService;

public class ShareServiceImpl implements ShareService {
	@Autowired
	HasShareNoteService hasShareNoteService;
	
	// 第三方注册时没有email
	@Override
	public void  addShareNotebookToUserId(String notebookId,int perm,String userId,String toUserId ){
		// 添加一条记录说明两者存在关系
		HasShareNote hasShareNote = new HasShareNote();
		//hasShareNote.setId(new ObjectId().toHexString());
		hasShareNote.setUserId(userId);
		hasShareNote.setToUserId(toUserId);
		//hasShareNoteService.save(hasShareNote);
		
		// 先删除之
		//mongoTemplate.remove(new Query(Criteria.where("userId").is(userId).and("ToUserId").is(toUserId)),ShareNotebook.class,"share_notebooks");  
		//插入信息
		ShareNotebook shareNotebook = new ShareNotebook();
		//shareNotebook.setId(new ObjectId().toHexString());
		shareNotebook.setUserId(userId);
		shareNotebook.setToUserId(toUserId);
		shareNotebook.setPerm(perm);
		shareNotebook.setCreatedTime(new Date());
		//mongoTemplate.insert(shareNotebook);
		
	}

	@Override
	public List<ShareNotes> findShareNotesByUserId(String userId) {
		return null;
	}
	@Override
	public List<ShareNotebook> findShareNoteBookByUserId(String userId) {
		return null;
	}
	
}
