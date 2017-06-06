package com.pursuit.noteblog.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author wangjn_bj
 * @desc:博客评论
 */
@Document(collection="blog_comments")
public class BlogComments extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String noteId;
	private String userId;// UserId回复ToUserId
	private String content;// 评论内容
	private String toCommendId;// 对某条评论进行回复
	private String toUserId;// 为空表示直接评论, 不回空表示回复某人
	private int likeNum;// 点赞次数, 评论也可以点赞
	private String[] likeUserIds;// 点赞的用户ids
	private Date createdTime;
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getToCommendId() {
		return toCommendId;
	}
	public void setToCommendId(String toCommendId) {
		this.toCommendId = toCommendId;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public String[] getLikeUserIds() {
		return likeUserIds;
	}
	public void setLikeUserIds(String[] likeUserIds) {
		this.likeUserIds = likeUserIds;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
