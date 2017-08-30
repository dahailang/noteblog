package com.pursuit.noteblog.entity;

import java.util.Date;

public class GroupUsers extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public String groupId;
	public String userId;
	public Date createdTime;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
