package com.pursuit.noteblog.repository;

import java.util.List;

import com.pursuit.noteblog.entity.SystemConfig;


public interface ConfigRepository extends BaseRepository<SystemConfig> {
    public SystemConfig findByKey(String key);
    public SystemConfig findByValue(String value);
    public List<SystemConfig> findByStatus(int status);
}