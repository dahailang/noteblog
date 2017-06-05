package com.pursuit.noteblog.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	public String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
