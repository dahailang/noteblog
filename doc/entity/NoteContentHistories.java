package com.pursuit.noteblog.entity;

import java.util.Date;


/**
 * @author wangjn_bj
 * @desc:id 和note id 共享
 */
public class NoteContentHistories{
	private String noteId;
	private String userId;
	private EachHistory[] histories;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public EachHistory[] getHistories() {
		return histories;
	}
	public void setHistories(EachHistory[] histories) {
		this.histories = histories;
	}
}
// 历史记录
//每一个历史记录对象
class EachHistory{
	private String updatedUserId;
	private Date updatedTime;
	private String content;
	public String getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
