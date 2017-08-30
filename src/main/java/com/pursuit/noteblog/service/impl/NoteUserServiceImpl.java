package com.pursuit.noteblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.dao.NoteUserMapper;
import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.service.NoteUserService;
//@Service
public class NoteUserServiceImpl implements NoteUserService{
	@Autowired
	private NoteUserMapper  noteUserMapper;

	@Override
	public String doLogin(String email, String pwd) {
		return null;
	}

	@Override
	public boolean doRegister(String emailOrUsername, String pwd, String fromUserId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String register(String email, String pwd, String fromUserId) {
		return null;
	}

	@Override
	public NoteUser getUserByUid(String userId) {
		return null;
	}

	@Override
	public NoteUser getByEmail(String email) {
		return null;
	}

	@Override
	public NoteUser addNoteUser(NoteUser noteUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateNoteUser(NoteUser noteUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteNoteUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername(String thirdType, String thirdUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String thirdRegister(String thirdType, String thirdUserId, String thirdUsername) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
