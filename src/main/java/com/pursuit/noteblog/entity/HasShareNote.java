package com.pursuit.noteblog.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="has_share_notes")
public class HasShareNote extends BaseEntity {
	private String userId;
	private String toUserId;
	private int seq; // 以后还可以用户排序
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
}
