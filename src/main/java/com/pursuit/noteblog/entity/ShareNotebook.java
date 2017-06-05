package com.pursuit.noteblog.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="share_notebooks")
public class ShareNotebook extends BaseEntity {
	private String userId;
	private String toUserId;
	//private String toGroupId       bson.ObjectId `bson:"ToGroupId,omitempty"` // 分享给的用户组
	//private String toGroup         Group         `ToGroup,omitempty`          // 仅仅为了显示, 不存储, 分组信息
	private String notebookId;
	private int seq;// 排序
	private int perm;// 权限, 其下所有notes 0只读, 1可修改
	private Date createdTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getNotebookId() {
		return notebookId;
	}
	public void setNotebookId(String notebookId) {
		this.notebookId = notebookId;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getPerm() {
		return perm;
	}
	public void setPerm(int perm) {
		this.perm = perm;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
