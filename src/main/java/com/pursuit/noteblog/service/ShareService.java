package com.pursuit.noteblog.service;

import java.util.List;

import com.pursuit.noteblog.entity.ShareNotebook;
import com.pursuit.noteblog.entity.ShareNotes;

public interface ShareService extends BaseService<ShareNotes>{
	
	public void  addShareNotebookToUserId(String notebookId,int perm,String userId,String toUserId );
	public List<ShareNotes> findShareNotesByUserId(String userId);
	public List<ShareNotebook> findShareNoteBookByUserId(String userId);
}
