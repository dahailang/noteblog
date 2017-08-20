package com.pursuit.noteblog.web.conversation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录状态信息服务
 */
public interface UserLoginStatusService {
    
	public final static String LOGIN_USER_STATUS_NAME = "NOTE_BOLG_USER_STATUS";

    /**
     * 获取用户登录状态信息
     * 
     * @param request
     * @return
     */
    String getUserIdFromLoginStatus(HttpServletRequest request);

    /**
     * 添加用户登录状态信息
     * 
     * @param request
     * @param response
     * @param userId
     */
    void addLoginStatus(HttpServletRequest request, HttpServletResponse response, String userId);

    /**
     * 移除用户登录状态信息
     * 
     * @param request
     * @param response
     */
    void removeLoginStatus(HttpServletRequest request, HttpServletResponse response);
}