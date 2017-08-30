package com.pursuit.noteblog.entity;

public class NoteImage {
	private String noteId;// 笔记
	private String imageId;// 图片fileId
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
}
