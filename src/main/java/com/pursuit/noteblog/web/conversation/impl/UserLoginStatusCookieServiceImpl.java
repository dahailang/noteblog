package com.pursuit.noteblog.web.conversation.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.util.ConstUtils;
import com.pursuit.noteblog.web.conversation.UserLoginStatusService;


/**
 * cookie保护登录状态
 */
@Service
public class UserLoginStatusCookieServiceImpl implements UserLoginStatusService {

    @Override
    public String getUserIdFromLoginStatus(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookiesId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LOGIN_USER_STATUS_NAME.equals(cookie.getName())) {
                    cookiesId = cookie.getValue();
                }
            }
        }
        return StringUtils.defaultString(cookiesId, "-1");
    }

    @Override
    public void addLoginStatus(HttpServletRequest request, HttpServletResponse response, String userId) {
        Cookie cookie = new Cookie(LOGIN_USER_STATUS_NAME, userId);
        cookie.setDomain(ConstUtils.COOKIE_DOMAIN);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    @Override
    public void removeLoginStatus(HttpServletRequest request, HttpServletResponse response) {
        addLoginStatus(request, response, "");
    }

    
    
}
