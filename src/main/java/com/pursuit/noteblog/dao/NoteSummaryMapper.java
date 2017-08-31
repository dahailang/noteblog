package com.pursuit.noteblog.dao;

import com.pursuit.noteblog.po.NoteSummary;

public interface NoteSummaryMapper {
    
	int deleteByPrimaryKey(String id);

    int insert(NoteSummary record);

    int insertSelective(NoteSummary record);

    NoteSummary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NoteSummary record);

    int updateByPrimaryKey(NoteSummary record);
}