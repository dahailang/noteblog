package com.pursuit.noteblog.dao;

import java.util.List;

import com.pursuit.noteblog.po.NoteConfig;

public interface NoteConfigMapper {
    
	int deleteByPrimaryKey(String id);

    int insert(NoteConfig record);

    int insertSelective(NoteConfig record);

    NoteConfig selectByPrimaryKey(String id);
    
    List<NoteConfig> getAllConfig();
    
    int updateByPrimaryKeySelective(NoteConfig record);

    int updateByPrimaryKey(NoteConfig record);
}