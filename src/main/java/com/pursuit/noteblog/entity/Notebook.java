package com.pursuit.noteblog.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="notebooks")
public class Notebook extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String title;
	private String urlTitle;
	private int numberNotes;//0
	private int seq;// -1
	private boolean isDeleted;// -1
	private int usn;// 3
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrlTitle() {
		return urlTitle;
	}
	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}
	public int getNumberNotes() {
		return numberNotes;
	}
	public void setNumberNotes(int numberNotes) {
		this.numberNotes = numberNotes;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getUsn() {
		return usn;
	}
	public void setUsn(int usn) {
		this.usn = usn;
	}
	
}
