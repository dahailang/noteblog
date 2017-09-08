package com.pursuit.noteblog.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.pursuit.noteblog.dao.NoteUserMapper;
import com.pursuit.noteblog.enums.UserStatusEnum;
import com.pursuit.noteblog.enums.UserTypeEnum;
import com.pursuit.noteblog.expection.NBException;
import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.service.NoteUserService;
import com.pursuit.noteblog.util.IdGenerator;
import com.pursuit.noteblog.web.NBResult;
public class NoteUserServiceImpl implements NoteUserService{
	@Autowired
	private NoteUserMapper  noteUserMapper;

	@Override
	public NBResult doLogin(String email, String pwd) {
		NoteUser noteUser = noteUserMapper.selectByEmail(email);
		if(null==noteUser){
			return NBResult.fail("用户不存在");
		}
		if(noteUser.getPassword().equals(pwd)){
			return NBResult.ok(noteUser.getId());
		}
		return NBResult.fail("用户密码不正确");
	}


	@Override
	public NoteUser doRegister(String email, String pwd, String fromUserId) {
		if(null != noteUserMapper.selectByEmail(email)){
			throw new NBException("email已经注册");
		}
		
		NoteUser noteUser = new NoteUser();
		noteUser.setId(IdGenerator.UID.generateSessionId());
		noteUser.setEmail(email);
		noteUser.setPassword(pwd);
		noteUser.setNickname(email);
		noteUser.setStatus(UserStatusEnum.USER_RUNNING.getUserStatus());
		noteUser.setCreateTime(new Date());
		noteUser.setLastUpdateTime(new Date());
		noteUser.setType(UserTypeEnum.REGULAR_USER.getUserType());
		noteUser.setAvatar("/");
		noteUserMapper.insert(noteUser);
		return noteUser;
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
