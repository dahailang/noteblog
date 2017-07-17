package com.pursuit.noteblog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.pursuit.noteblog.dao.NoteContentDao;
import com.pursuit.noteblog.dao.NoteDao;
import com.pursuit.noteblog.entity.Note;
import com.pursuit.noteblog.entity.NoteBook;
import com.pursuit.noteblog.entity.NoteContent;
import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.service.NoteBookService;
import com.pursuit.noteblog.service.NoteService;
import com.pursuit.noteblog.web.WebResult;
public class NoteServiceImpl implements NoteService {
	@Autowired
	NoteDao noteDao;
	@Autowired
	NoteContentDao noteContentDao;
	@Autowired
	NoteBookService noteBookService;
	
	@Override
	public WebResult index(User user,String noteId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("", "");
		// 已登录了, 那么得到所有信息
		List<NoteBook> notebooks = noteBookService.findByUserid(user.getUid());
//		shareNotebooks, sharedUserInfos := shareService.GetShareNotebooks(userId)
		List<Note> notes;
		NoteContent noteContent = new  NoteContent();
		boolean hasRightNoteId = false;
		if(notebooks!=null&&notebooks.size()>0){
			if(null!=noteId){
				noteContent = getNoteContent(noteId);
			}else{
				notes = findByUserId(user.getUid());
				
			}
			
			// 没有传入笔记
			// 那么得到最新笔记
			if (!hasRightNoteId) {
				notes = findByUserId(user.getUid());
				if (null!=notes&&notes.size()>0) {
					noteContent =getNoteContent(noteId);
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
		return noteDao.getNoteByUid(userId);
	}
	@Override
	public NoteContent getNoteContent(String noteId) {
		return noteContentDao.getNoteContentByNoteiId(noteId);
	}

}
