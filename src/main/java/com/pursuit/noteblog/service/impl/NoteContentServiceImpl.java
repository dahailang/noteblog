package com.pursuit.noteblog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.pursuit.noteblog.dao.NoteContentMapper;
import com.pursuit.noteblog.po.NoteBook;
import com.pursuit.noteblog.po.NoteContent;
import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.service.NoteBookService;
import com.pursuit.noteblog.service.NoteContentService;
import com.pursuit.noteblog.web.NBResult;

public class NoteContentServiceImpl implements NoteContentService {
	
	@Autowired
	private NoteContentMapper noteContentMapper;
	
	@Autowired
	private NoteBookService noteBookService;
	
	
	@Override
	public NBResult index(NoteUser noteUser,String noteId) {
		Map<String,Object> map = new HashMap<String,Object>();
		// 已登录了, 那么得到所有信息
		List<NoteBook> notebooks = noteBookService.findByUserid(noteUser.getId());
		List<NoteContent> notes;
		NoteContent noteContent = new  NoteContent();
		boolean hasRightNoteId = false;
		if(notebooks!=null&&notebooks.size()>0){
			if(null!=noteId){
				noteContent = getNoteContent(noteId);
			}else{
				notes = findByUserId(noteUser.getId());
				
			}
			// 没有传入笔记
			// 那么得到最新笔记
			if (!hasRightNoteId) {
				notes = findByUserId(noteUser.getId());
				if (null!=notes&&notes.size()>0) {
					noteContent =getNoteContent(noteId);
					map.put("curNoteId", notes.get(0).getId());
				}
			}
			
		}
		map.put("noteContent", noteContent);
		map.put("notebooks", notebooks);
		return NBResult.ok(map);
	}

	@Override
	public List<NoteContent> findByUserId(String userId) {
		return noteContentMapper.selectByUid(userId);
	}

	@Override
	public NoteContent getNoteContent(String noteId) {
		return noteContentMapper.selectByPrimaryKey(noteId);
	}

	@Override
	public NBResult saveContent(String noteId, String noteBookId, String content) {
		// TODO Auto-generated method stub
		return null;
	}

}
