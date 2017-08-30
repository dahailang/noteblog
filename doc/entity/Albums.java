package com.pursuit.noteblog.entity;

import java.util.Date;


/**
 * @author wangjn_bj
 * @desc:专辑
 */
public class Albums extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String name;
	private int type;
	private Date createdTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
