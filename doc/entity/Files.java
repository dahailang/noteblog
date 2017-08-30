package com.pursuit.noteblog.entity;

import java.util.Date;

public class Files {
	public String fileId;
	public String userId;
	public String albumId;
	public String name;
	public String title;// file name or user defined for search
	public String size;// file size (byte)
	public String type; // file type, "" = image, "doc" = word
	public String path;// the file path
	public boolean isDefaultAlbum;
	private Date  createdTime;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isDefaultAlbum() {
		return isDefaultAlbum;
	}
	public void setDefaultAlbum(boolean isDefaultAlbum) {
		this.isDefaultAlbum = isDefaultAlbum;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
