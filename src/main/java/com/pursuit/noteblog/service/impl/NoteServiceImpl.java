package com.pursuit.noteblog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.entity.Note;
import com.pursuit.noteblog.entity.NoteContent;
import com.pursuit.noteblog.entity.Notebook;
import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.service.NoteService;
import com.pursuit.noteblog.service.NotebookService;
import com.pursuit.noteblog.web.WebResult;
@Service
public class NoteServiceImpl implements NoteService {
	@Autowired
	NotebookService notebookService;
	@Autowired
	NoteService noteService;
//	@Autowired
//	ShareService shareService;
	@Autowired
	protected MongoTemplate mongoTemplate;
	@Override
	public WebResult index(User user,String noteId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("", "");
		
		// 已登录了, 那么得到所有信息
		List<Notebook> notebooks = notebookService.findByUserid(user.getId());
//		shareNotebooks, sharedUserInfos := shareService.GetShareNotebooks(userId)
		List<Note> notes;
		NoteContent noteContent = new NoteContent();
		boolean hasRightNoteId = false;
		if(notebooks!=null&&notebooks.size()>0){
			if(null!=noteId&&ObjectId.isValid(noteId)){
				noteContent = getNoteContent(user.getId(), noteId);
			}else{
				notes = findByUserId(user.getId());
				
			}
			
			// 没有传入笔记
			// 那么得到最新笔记
			if (!hasRightNoteId) {
				notes = findByUserId(user.getId());
				if (null!=notes&&notes.size()>0) {
					noteContent =getNoteContent(user.getId(), noteId);
					map.put("curNoteId", notes.get(0).getId());
				}
			}
			
		}
//		if len(notebooks) > 0 {
//			// noteId是否存在
//			// 是否传入了正确的noteId
//			hasRightNoteId := false
//			if IsObjectId(noteId) {
//				note := noteService.GetNoteById(noteId)
//
//				if note.NoteId != "" {
//					var noteOwner = note.UserId.Hex()
//					noteContent = noteService.GetNoteContent(noteId, noteOwner)
//
//					hasRightNoteId = true
//					c.ViewArgs["curNoteId"] = noteId
//					c.ViewArgs["curNotebookId"] = note.NotebookId.Hex()
//
//					// 打开的是共享的笔记, 那么判断是否是共享给我的默认笔记
//					if noteOwner != c.GetUserId() {
//						if shareService.HasReadPerm(noteOwner, c.GetUserId(), noteId) {
//							// 不要获取notebook下的笔记
//							// 在前端下发请求
//							c.ViewArgs["curSharedNoteNotebookId"] = note.NotebookId.Hex()
//							c.ViewArgs["curSharedUserId"] = noteOwner
//							// 没有读写权限
//						} else {
//							hasRightNoteId = false
//						}
//					} else {
//						_, notes = noteService.ListNotes(c.GetUserId(), note.NotebookId.Hex(), false, c.GetPage(), 50, defaultSortField, false, false)
//
//						// 如果指定了某笔记, 则该笔记放在首位
//						lenNotes := len(notes)
//						if lenNotes > 1 {
//							notes2 := make([]info.Note, len(notes))
//							notes2[0] = note
//							i := 1
//							for _, note := range notes {
//								if note.NoteId.Hex() != noteId {
//									if i == lenNotes { // 防止越界
//										break
//									}
//									notes2[i] = note
//									i++
//								}
//							}
//							notes = notes2
//						}
//					}
//				}
//
//				// 得到最近的笔记
//				_, latestNotes := noteService.ListNotes(c.GetUserId(), "", false, c.GetPage(), 50, defaultSortField, false, false)
//				c.ViewArgs["latestNotes"] = latestNotes
//			}
//
//			// 没有传入笔记
//			// 那么得到最新笔记
//			if !hasRightNoteId {
//				_, notes = noteService.ListNotes(c.GetUserId(), "", false, c.GetPage(), 50, defaultSortField, false, false)
//				if len(notes) > 0 {
//					noteContent = noteService.GetNoteContent(notes[0].NoteId.Hex(), userId)
//					c.ViewArgs["curNoteId"] = notes[0].NoteId.Hex()
//				}
//			}
//		}
//
//		// 当然, 还需要得到第一个notes的content
//		//...
//
//		c.ViewArgs["notebooks"] = notebooks
//		c.ViewArgs["shareNotebooks"] = shareNotebooks // note信息在notes列表中
//		c.ViewArgs["sharedUserInfos"] = sharedUserInfos
//
//		c.ViewArgs["notes"] = notes
//		c.ViewArgs["noteContentJson"] = noteContent
//		c.ViewArgs["noteContent"] = noteContent.Content
//
//		c.ViewArgs["tags"] = tagService.GetTags(c.GetUserId())
//
//		c.ViewArgs["globalConfigs"] = configService.GetGlobalConfigForUser()
//
//		// return c.RenderTemplate("note/note.html")
//
//		if isDev, _ := revel.Config.Bool("mode.dev"); isDev && online == "" {
//			return c.RenderTemplate("note/note-dev.html")
//		} else {
//			return c.RenderTemplate("note/note.html")
//		}
		map.put("noteContent", noteContent);
		map.put("notebooks", notebooks);
		return WebResult.ok(map);
	}
	
	
	@Override
	public List<Note> findByUserId(String userId) {
		return mongoTemplate.find(new Query(Criteria.where("userId").is(userId)), Note.class,"notes");  
	}
	@Override
	public NoteContent getNoteContent(String userId,String noteId) {
		return mongoTemplate.findOne(new Query(Criteria.where("userId").is(userId)
				.and("noteId").is(noteId)), NoteContent.class,"notes");  
	}
	@Override
	public Note findById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Note save(Note e) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SuccessEnum update(Note e) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SuccessEnum deleteBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
