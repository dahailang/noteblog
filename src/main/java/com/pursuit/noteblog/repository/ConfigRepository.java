package com.pursuit.noteblog.repository;

import java.util.List;

import com.pursuit.noteblog.entity.Config;

public interface ConfigRepository extends BaseRepository<Config> {
    public Config findByKey(String key);
    public Config findByValue(String value);
    public List<Config> findByStatus(int status);
}