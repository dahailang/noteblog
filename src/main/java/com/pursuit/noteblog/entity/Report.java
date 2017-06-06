package com.pursuit.noteblog.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="reports")
public class Report extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String noteId;
	private String userId;
	private String reason;
	private String commendId;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCommendId() {
		return commendId;
	}
	public void setCommendId(String commendId) {
		this.commendId = commendId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
