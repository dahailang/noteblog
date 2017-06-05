package com.pursuit.noteblog.service;

import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.enums.SuccessEnum;

public interface UserService {
	 /**
     * 通过id获取用户
     */
    User get(String userId);

    /**
     * 通过邮箱查询
     */
    public User getByEmail(String email);

    /**
     * 通过用户名查询
     */
    public User getByUsername(String username);
    
    /**
     * 通过用户名邮箱查询
     */
    public User findByUsernameOrEmail(String username,String email);

    /**
     * 保存用户
     */
    public User save(User user);

    /**
     * 更新用户
     */
    public SuccessEnum update(User user);

    /**
     * 删除用户
     */
    public SuccessEnum delete(String userId);
}
