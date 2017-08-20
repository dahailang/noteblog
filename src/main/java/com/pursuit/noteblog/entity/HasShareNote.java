package com.pursuit.noteblog.entity;

public class HasShareNote extends BaseEntity {
	private static final long serialVersionUID = 1L;
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
