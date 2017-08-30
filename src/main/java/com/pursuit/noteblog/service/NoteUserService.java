package com.pursuit.noteblog.service;

import com.pursuit.noteblog.po.NoteUser;

public interface NoteUserService {
	
	/**
     * 通过id获取用户
     */
	public String doLogin(String email,String pwd);
	
	public boolean doRegister(String emailOrUsername,String pwd,String fromUserId);
	
	public String register(String email,String pwd, String fromUserId);
	
	/**
     * 通过id获取用户
     */
    NoteUser getUserByUid(String userId);

    /**
     * 通过邮箱查询
     */
    public NoteUser getByEmail(String email);


    /**
     * 保存用户
     */
    public NoteUser addNoteUser(NoteUser noteUser);

    /**
     * 更新用户
     */
    public String updateNoteUser(NoteUser noteUser);

    /**
     * 删除用户
     */
    public String deleteNoteUser(String userId);
    
	public String getUsername(String thirdType,String thirdUsername);

	public String thirdRegister(String thirdType,String thirdUserId,String thirdUsername);

}
