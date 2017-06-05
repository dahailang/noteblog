package com.pursuit.noteblog.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User extends BaseEntity{
	
	public Date createdTime;
	public String email;
	public boolean leftIsMin;
	public int noteListWidth;
	public int notebookWidth;
	public String pwd;
	public String theme;
	public int thirdType;
	public String thirdUserId;
	public String thirdUsername;
	public String username;
	public String usernameRaw;
	public boolean verified;
	public int mdEditorWidth;
	public int usn;
	public String logo;
	public int type;//-1 下线用户 0 管理员 1 普通用户
	public String blogUrl;

	public String getBlogUrl() {
		return blogUrl;
	}
	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isLeftIsMin() {
		return leftIsMin;
	}
	public void setLeftIsMin(boolean leftIsMin) {
		this.leftIsMin = leftIsMin;
	}
	public int getNoteListWidth() {
		return noteListWidth;
	}
	public void setNoteListWidth(int noteListWidth) {
		this.noteListWidth = noteListWidth;
	}
	public int getNotebookWidth() {
		return notebookWidth;
	}
	public void setNotebookWidth(int notebookWidth) {
		this.notebookWidth = notebookWidth;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getThirdType() {
		return thirdType;
	}
	public void setThirdType(int thirdType) {
		this.thirdType = thirdType;
	}
	public String getThirdUserId() {
		return thirdUserId;
	}
	public void setThirdUserId(String thirdUserId) {
		this.thirdUserId = thirdUserId;
	}
	public String getThirdUsername() {
		return thirdUsername;
	}
	public void setThirdUsername(String thirdUsername) {
		this.thirdUsername = thirdUsername;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernameRaw() {
		return usernameRaw;
	}
	public void setUsernameRaw(String usernameRaw) {
		this.usernameRaw = usernameRaw;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public int getMdEditorWidth() {
		return mdEditorWidth;
	}
	public void setMdEditorWidth(int mdEditorWidth) {
		this.mdEditorWidth = mdEditorWidth;
	}

	public String getLogo() {
		return logo;
	}
	public int getUsn() {
		return usn;
	}
	public void setUsn(int usn) {
		this.usn = usn;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
