package com.pursuit.noteblog.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.enums.UserStatusEnum;
import com.pursuit.noteblog.enums.UserTypeEnum;
import com.pursuit.noteblog.service.AuthService;
import com.pursuit.noteblog.service.UserService;
import com.pursuit.noteblog.util.IdGenerator;
import com.pursuit.noteblog.web.WebResult;
@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private UserService userService;
    
	@Override
	public String doLogin(String email, String pwd) {
		User user = userService.getByEmail(email);
		if(user!=null){
			String password = user.getPassword();
			//password 加密处理
			if(password.equals(pwd)){
				return user.getUid();
			}
		}
		return "-1";
	}

	@Override
	public WebResult register(String email,String pwd, String fromUserId) {
		//1.验证用户是否已经存在  "userHasBeenRegistered-" + email
		//2.密码加密处理
    	User user = new User();
    	email = email.toLowerCase();
    	user.setUid(IdGenerator.UID.generateSessionId());
    	user.setEmail(email);//转为小写存储
    	user.setPassword(pwd);
		user.setNickname(email);//默认用户名为邮箱
		user.setAvatar("/imgs");
		user.setCreatetime(new Date());
		user.setType(UserTypeEnum.REGULAR_USER.getUserType());
		user.setStatus(UserStatusEnum.USER_RUNNING.getUserStatus());
		User userinfo = userService.addUser(user);
		//添加默认笔记本
//		for(String title:ConstUtils.DEFAULT_INIT_NOTEBOOK){
//			Notebook notebook = new Notebook();
//			notebook.setId(new ObjectId().toHexString());
//			notebook.setSeq(-1);
//			notebook.setUserId(userinfo.getId());
//			notebook.setTitle(title);
//			notebookService.save(notebook);
//		}
		// 添加leanote -> 该用户的共享
//		String registerSharedUserId = configService.getGlobalStringConfig("registerSharedUserId");
//		if(StringUtils.isNotBlank(registerSharedUserId)){
//			List<Map<String,String>> registerSharedNotebooks = configService.getGlobalArrMapConfig("registerSharedNotebooks");
//			List<Map<String,String>> registerSharedNotes = configService.getGlobalArrMapConfig("registerSharedNotes");
//			List<String> registerCopyNoteIds = configService.getGlobalArrayConfig("registerCopyNoteIds");
//		
//			// 添加共享笔记本
//			for (Map<String,String> map : registerSharedNotebooks) {
//				shareService.AddShareNotebookToUserId(notebook["notebookId"], perm, registerSharedUserId, userId);
//			}
			// 添加共享笔记
//			for _, note := range registerSharedNotes {
//				perm, _ := strconv.Atoi(note["perm"])
//				shareService.AddShareNoteToUserId(note["noteId"], perm, registerSharedUserId, userId)
//			}
//
//			// 复制笔记
//			for _, noteId := range registerCopyNoteIds {
//				note := noteService.CopySharedNote(noteId, title2Id["life"].Hex(), registerSharedUserId, user.UserId.Hex())
//				//				Log(noteId)
//				//				Log("Copy")
//				//				LogJ(note)
//				noteUpdate := bson.M{"IsBlog": false} // 不要是博客
//				noteService.UpdateNote(user.UserId.Hex(), note.NoteId.Hex(), noteUpdate, -1)
//			}
//		
//		}
		//---------------
		// 添加一条userBlog
//		UserBlog userBlog = new UserBlog();
//		userBlog.setId(new ObjectId().toHexString());
//		userBlog.setUserId(user.getId());
//		userBlog.setTitle(user.getUsername() + " 's Blog");
//		userBlog.setSubTitle("Love Leanote!");
//		userBlog.setAboutMe("Hello, I am (^_^)");
//		userBlog.setCanComment(true);
		//blogService.save(userBlog);
		// 添加一个单页面
		//blogService.AddOrUpdateSingle(user.UserId.Hex(), "", "About Me", "Hello, I am (^_^)")
		
		//存入登录
		return WebResult.ok(userinfo.getUid());
	}

	@Override
	public boolean doRegister(String emailOrUsername, String pwd, String fromUserId) {
		return false;
	}

	@Override
	public String getUsername(String thirdType, String thirdUsername) {
		return null;
	}

	@Override
	public String thirdRegister(String thirdType, String thirdUserId, String thirdUsername) {
		return null;
	}
	
}
