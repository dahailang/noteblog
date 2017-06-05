package com.pursuit.noteblog.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.service.UserService;
import com.pursuit.noteblog.util.ConstUtils;
import com.pursuit.noteblog.web.conversation.UserLoginStatusService;

/**
 * 前台登陆验证
 */
public class FrontUserLoginInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(FrontUserLoginInterceptor.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginStatusService userLoginStatusService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String userId = userLoginStatusService.getUserIdFromLoginStatus(request);
        User user = userService.get(userId);
        
        if (user == null) {
        	logger.info("用户未登录，重定向到登录页面");
            String redirectUrl = LoginInterceptorUtil.getLoginRedirectUrl(request);
            response.sendRedirect(redirectUrl);
            return false;
        }
        request.setAttribute("userInfo", user);
        request.setAttribute("isAdmin", user.getUsername()==ConstUtils.ADMIN_USERNAME);
        return true;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserLoginStatusService(UserLoginStatusService userLoginStatusService) {
        this.userLoginStatusService = userLoginStatusService;
    }

}