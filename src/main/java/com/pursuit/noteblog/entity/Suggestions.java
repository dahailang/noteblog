package com.pursuit.noteblog.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="suggestions")
public class Suggestions extends BaseEntity {
	public String userId;
	public String addr;
	public String suggestion;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
}
