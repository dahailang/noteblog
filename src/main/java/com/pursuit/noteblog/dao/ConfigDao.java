package com.pursuit.noteblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pursuit.noteblog.entity.SystemConfig;

/**
 * 配置修改dao
 */
public interface ConfigDao {

    /**
     * 更新配置对key-value
     * 
     * @param configKey
     * @param configValue
     */
    public void update(@Param("configKey") String configKey, @Param("configValue") String configValue);

    /**
     * 获取配置列表
     * 
     * @param status
     * @return
     */
    public List<SystemConfig> getConfigList(@Param("status") int status);

}
