package com.pursuit.noteblog.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.pursuit.noteblog.util.ConstUtils;

public class LoginInterceptorUtil {

    /**
     * 获取登录跳转地址
     */
    public static String getLoginRedirectUrl(HttpServletRequest request) throws Exception {
        StringBuffer redirectUrl = new StringBuffer();
        redirectUrl.append(request.getSession(true).getServletContext().getContextPath());
        redirectUrl.append("/login?");
        // 跳转地址
        redirectUrl.append(ConstUtils.RREDIRECT_URL_PARAM);
        redirectUrl.append("=");
        redirectUrl.append(request.getRequestURI());
        // 跳转参数
        String query = request.getQueryString();
        if (StringUtils.isNotBlank(query)) {
            redirectUrl.append("?");
            redirectUrl.append(java.net.URLEncoder.encode(request.getQueryString(), "UTF-8"));
        }
        return redirectUrl.toString();
    }

}
