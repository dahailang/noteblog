package com.pursuit.noteblog.entity;

import org.springframework.data.annotation.Id;

public class BaseEntity {
	@Id
	public String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
