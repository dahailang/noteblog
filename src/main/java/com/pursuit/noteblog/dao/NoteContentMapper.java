package com.pursuit.noteblog.dao;

import java.util.List;

import com.pursuit.noteblog.po.NoteContent;

public interface NoteContentMapper {
    int deleteByPrimaryKey(String id);

    int insert(NoteContent record);

    int insertSelective(NoteContent record);

    NoteContent selectByPrimaryKey(String id);

    List<NoteContent> selectByUid(String uid);
    
    int updateByPrimaryKeySelective(NoteContent record);

    int updateByPrimaryKeyWithBLOBs(NoteContent record);

    int updateByPrimaryKey(NoteContent record);
}