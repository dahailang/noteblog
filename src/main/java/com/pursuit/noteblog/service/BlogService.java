package com.pursuit.noteblog.service;
import java.util.List;

import com.pursuit.noteblog.entity.UserBlog;

public interface BlogService extends BaseService<UserBlog>{
	public UserBlog updateUserBlog(UserBlog UserBlog);
	public List<UserBlog>  findByUserId(String userId);
	
	
}
