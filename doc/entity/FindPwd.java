package com.pursuit.noteblog.entity;

import java.util.Date;
public class FindPwd extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public String email;
	public String token;
	public Date createdTime;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
