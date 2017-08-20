package com.pursuit.noteblog.web.conversation.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

import com.pursuit.noteblog.web.conversation.UserLoginStatusService;



/**
 * session保护登录状态
 * @author leifu
 * @Date 2016年6月15日
 * @Time 下午1:31:05
 */
public class UserLoginStatusSessionServiceImpl implements UserLoginStatusService {

    @Override
    public String getUserIdFromLoginStatus(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Object object = session.getAttribute(LOGIN_USER_STATUS_NAME);
        return object == null ? "-1" : StringUtils.trim(object.toString());
    }

    @Override
    public void addLoginStatus(HttpServletRequest request, HttpServletResponse response, String userId) {
        request.getSession().setAttribute(LOGIN_USER_STATUS_NAME, userId);
    }

    @Override
    public void removeLoginStatus(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(LOGIN_USER_STATUS_NAME);
    }

    
    
}
