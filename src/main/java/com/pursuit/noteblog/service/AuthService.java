package com.pursuit.noteblog.service;

import com.pursuit.noteblog.web.WebResult;

/**
 * @describe: 登录与权限 Login & Register
 */
public interface AuthService {

	/**
	 *	使用bcrypt认证或者Md5认证
	 *	Use bcrypt (Md5 depreciated)
	 */
	public String doLogin(String email,String pwd);

	public WebResult register(String email,String pwd, String fromUserId);
	// 添加笔记本, 生活, 学习, 工作
	public boolean doRegister(String emailOrUsername,String pwd,String fromUserId);
	// 第三方注册
	
	// 第三方得到用户名, 可能需要多次判断
	public String getUsername(String thirdType,String thirdUsername);
	public String thirdRegister(String thirdType,String thirdUserId,String thirdUsername);
}
