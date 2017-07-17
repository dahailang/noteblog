package com.pursuit.noteblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pursuit.noteblog.entity.SystemConfig;

/**
 * 配置修改dao
 */
public interface SystemConfigDao {

    /**
     * 更新配置对key-value
     * 
     * @param configKey
     * @param configValue
     */
    public void update(@Param("configKey") String configKey, @Param("configValue") String configValue);
    public void update(SystemConfig systemConfig);
    /**
     * 获取配置列表
     * 
     * @param status
     * @return
     */
    public SystemConfig getConfig(String config_key);
    public List<SystemConfig> getALLConfigByStatus(@Param("status") int status);
    
    public void removeSystemConfig(String config_key);
    public void addSystemConfig(SystemConfig systemConfig);
    public void updateSystemConfig(SystemConfig systemConfig);

}
