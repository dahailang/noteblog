package com.pursuit.noteblog.dao;

import com.pursuit.noteblog.po.NoteUser;

public interface NoteUserMapper {
    
	int deleteByPrimaryKey(String id);

    int insert(NoteUser record);

    int insertSelective(NoteUser record);

    NoteUser selectByPrimaryKey(String id);
    
    NoteUser selectByEmail(String email);

    int updateByPrimaryKeySelective(NoteUser record);

    int updateByPrimaryKey(NoteUser record);
}