package com.pursuit.noteblog.entity;

import java.util.Date;

public class Groups extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public String groupId;// 谁的
	public String userId;// 所有者Id
	public String title;// 标题
	public String userCount;// 用户数
	public Date createdTime;

	public User[] users;// 分组下的用户, 不保存, 仅查看
}
