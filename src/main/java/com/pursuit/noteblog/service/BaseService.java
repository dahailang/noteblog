package com.pursuit.noteblog.service;

import com.pursuit.noteblog.enums.SuccessEnum;

public interface BaseService<E> {
	 /**
     * 通过id获取用户
     */
    E findById(String userId);

    /**
     * 保存用户
     */
    public E save(E e);

    /**
     * 更新用户
     */
    public SuccessEnum update(E e);

    /**
     * 删除用户
     */
    public SuccessEnum deleteBy(String id);
}
