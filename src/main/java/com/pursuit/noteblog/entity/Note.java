package com.pursuit.noteblog.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="notes")
public class Note extends BaseEntity {
	private String userId;
	private String notebookId;
	private String title;			// 标题
	private String desc;			// 描述, 非html
	private String imgSrc;			// 图片, 第一张缩略图地址
	private String tags;
	private boolean isTrash;		// 是否是trash, 默认是false
	private String urlTitle;		// 博客的url标题, 为了更友好的url, 在UserId, UrlName下唯一
	private boolean hasSelfDefined;	// 是否已经自定义博客图片, desc, abstract
	
	private int readNum;			// 阅读次数 2014/9/28
	private int likeNum;			// 点赞次数 2014/9/28
	private int commentNum;			// 评论次数 2014/9/28
	private boolean IsMarkdown;		// 是否是markdown笔记, 默认是false
	private int attachNum;
	private Date createdTime;
	private Date updatedTime;
	private Date recommendTime;		// 推荐时间
	private Date publicTime;		// 发表时间, 公开为博客则设置
	private String updatedUserId;	// 如果共享了, 并可写, 那么可能是其它他修改了
	private int usn;				// 更新序号
	private boolean isDeleted;		// 删除位
	private boolean isBlog;			// 是否设置成了blog 2013/12/29 新加
	
	private boolean isRecommend;	// 是否为推荐博客 2014/9/24新加
	private boolean isTop;			// blog是否置顶
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNotebookId() {
		return notebookId;
	}
	public void setNotebookId(String notebookId) {
		this.notebookId = notebookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public boolean isTrash() {
		return isTrash;
	}
	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
	public String getUrlTitle() {
		return urlTitle;
	}
	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}
	public boolean isHasSelfDefined() {
		return hasSelfDefined;
	}
	public void setHasSelfDefined(boolean hasSelfDefined) {
		this.hasSelfDefined = hasSelfDefined;
	}
	public int getReadNum() {
		return readNum;
	}
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public boolean isIsMarkdown() {
		return IsMarkdown;
	}
	public void setIsMarkdown(boolean isMarkdown) {
		IsMarkdown = isMarkdown;
	}
	public int getAttachNum() {
		return attachNum;
	}
	public void setAttachNum(int attachNum) {
		this.attachNum = attachNum;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Date getRecommendTime() {
		return recommendTime;
	}
	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}
	public Date getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}
	public String getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	public int getUsn() {
		return usn;
	}
	public void setUsn(int usn) {
		this.usn = usn;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean isBlog() {
		return isBlog;
	}
	public void setBlog(boolean isBlog) {
		this.isBlog = isBlog;
	}
	public boolean isRecommend() {
		return isRecommend;
	}
	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	public boolean isTop() {
		return isTop;
	}
	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}


}
