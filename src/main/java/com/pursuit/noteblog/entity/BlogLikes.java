package com.pursuit.noteblog.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blog_likes")
public class BlogLikes extends BaseEntity {

	private String noteId;
	private String userId;// UserId回复ToUserId
	private Date createdTime;
}
