package com.pursuit.noteblog.entity;

import java.util.Date;

public class NoteContent extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String userId;
	private boolean isBlog;// 为了搜索博客
	private String content;
	private String abstracts;// 摘要, 有html标签, 比content短, 在博客展示需要, 不放在notes表中
	private Date createdTime;
	private Date updatedTime;
	private String updatedUserId;// 如果共享了, 并可写, 那么可能是其它他修改了

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isBlog() {
		return isBlog;
	}

	public void setBlog(boolean isBlog) {
		this.isBlog = isBlog;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

}
