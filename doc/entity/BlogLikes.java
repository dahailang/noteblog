package com.pursuit.noteblog.entity;

import java.util.Date;

public class BlogLikes extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String noteId;
	private String userId;// UserId回复ToUserId
	private Date createdTime;
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
