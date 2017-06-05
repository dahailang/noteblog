package com.pursuit.noteblog.util;

import java.util.ResourceBundle;

/**
 * 常量
 */
public class ConstUtils {
    /**
     * 容量转换
     */
    public static final int _1024 = 1024;
    /**
     * 表示空字符串
     */
    public static final String EMPTY = "";
    
    /**
     * cookie登录方式所需要的域
     */
    public static String DEFAULT_COOKIE_DOMAIN = EMPTY;
    public static String COOKIE_DOMAIN = DEFAULT_COOKIE_DOMAIN;
    /**
     * 网站域名
     */
    public static String DEFAULT_CC_DOMAIN = "http://172.21.10.182:10100";
    public static String CC_DOMAIN = DEFAULT_CC_DOMAIN;
    public static String DEFAULT_SITEURL = "http://localhost:8080";
    public static String SITEURL = DEFAULT_SITEURL;
    public static String DEFAULT_BLOGURL = "http://localhost:8080";
    public static String BLOGURL = DEFAULT_BLOGURL;
    public static String DEFAULT_LEAURL = "http://localhost:8080";
    public static String LEAURL = DEFAULT_LEAURL;
    public static String DEFAULT_NOTEURL = "http://localhost:8080/note";
    public static String NOTEURL = DEFAULT_NOTEURL;
    
    public static String[] DEFAULT_INIT_NOTEBOOK = {"life","study","work"};
    
    /**
     * 主页是否是管理员的博客页
     *  0 不是  1 是
     */
    public static int DEFAULT_HOME_PAGE = 0;
    public static int HOME_PAGE = DEFAULT_HOME_PAGE;
    /**
     * 是否开放注册权限
     */
    public static int DEFAULT_OPEN_REGISTER = 1;
    public static int OPEN_REGISTER = DEFAULT_OPEN_REGISTER;
    
    /**
     * 网站管理员
     */
    public static String ADMIN_USERNAME="admin@163.com";
    public static String DEFAULT_ADMIN_EMAILS = "wangjn1130@163.com";
	public static String ADMIN_EMAILS;
	public static String DEFAULT_ADMIN_PHONES = "13180879819";
	public static String ADMIN_PHONES;
    
    static {
        ResourceBundle applicationResourceBundle = ResourceBundle.getBundle("application");
        IS_DEBUG = "true".equals(applicationResourceBundle.getString("isDebug"));
    }
    
    /**
     * 调试模式
     */
    public static  boolean IS_DEBUG = false;


    /**
     * 逗号
     */
    public static final String COMMA = ",";
    
    /**
     * 换行
     */
    public static final String NEXT_LINE = "\n";

    /**
     * 空格
     */
    public static final String SPACE = " ";
    

    /**
     * 冒号
     */
    public static final String COLON = ":";

    /**
     * 登录跳转参数
     */
    public final static String RREDIRECT_URL_PARAM = "redirectUrl";

}
