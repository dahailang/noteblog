package com.pursuit.noteblog.service.impl;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.entity.HasShareNote;
import com.pursuit.noteblog.entity.ShareNotebook;
import com.pursuit.noteblog.entity.ShareNotes;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.service.HasShareNoteService;
import com.pursuit.noteblog.service.ShareService;
public class ShareServiceImpl implements ShareService {
	@Autowired
	HasShareNoteService hasShareNoteService;
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	
	// 第三方注册时没有email
	@Override
	public void  addShareNotebookToUserId(String notebookId,int perm,String userId,String toUserId ){
		// 添加一条记录说明两者存在关系
		HasShareNote hasShareNote = new HasShareNote();
		hasShareNote.setId(new ObjectId().toHexString());
		hasShareNote.setUserId(userId);
		hasShareNote.setToUserId(toUserId);
		hasShareNoteService.save(hasShareNote);
		
		// 先删除之
		mongoTemplate.remove(new Query(Criteria.where("userId").is(userId).and("ToUserId").is(toUserId)),ShareNotebook.class,"share_notebooks");  
		//插入信息
		ShareNotebook shareNotebook = new ShareNotebook();
		shareNotebook.setId(new ObjectId().toHexString());
		shareNotebook.setUserId(userId);
		shareNotebook.setToUserId(toUserId);
		shareNotebook.setPerm(perm);
		shareNotebook.setCreatedTime(new Date());
		mongoTemplate.insert(shareNotebook);
		
	}


	@Override
	public List<ShareNotes> findShareNotesByUserId(String userId) {
		return mongoTemplate.find(new Query(Criteria.where("userId").is(userId)), ShareNotes.class,"share_notes");
	}
	@Override
	public List<ShareNotebook> findShareNoteBookByUserId(String userId) {
		return mongoTemplate.find(new Query(Criteria.where("userId").is(userId)), ShareNotebook.class,"share_notebooks");
	}
	
	@Override
	public ShareNotes findById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ShareNotes save(ShareNotes e) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SuccessEnum update(ShareNotes e) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SuccessEnum deleteBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
