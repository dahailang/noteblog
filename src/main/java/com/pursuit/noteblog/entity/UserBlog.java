package com.pursuit.noteblog.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user_bolgs")
public class UserBlog extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String title;
	private String subTitle;
	private String aboutMe;
	private boolean canComment;
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
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public boolean isCanComment() {
		return canComment;
	}
	public void setCanComment(boolean canComment) {
		this.canComment = canComment;
	}
	
	
}
