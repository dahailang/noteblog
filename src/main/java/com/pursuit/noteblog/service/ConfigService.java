package com.pursuit.noteblog.service;

import java.util.List;
import java.util.Map;

import com.pursuit.noteblog.entity.Config;
import com.pursuit.noteblog.enums.SuccessEnum;

public interface ConfigService {
	  /**
     * 加载配置
     */
    public void reloadSystemConfig();

    /**
     * 更新配置
     * @param configMap
     * @return
     */
    SuccessEnum updateConfig(Map<String, String> configMap);

    /**
     * 获取配置列表
     * @param status
     * @return
     */
    List<Config> getConfigList(int status);
    
    String getGlobalStringConfig(String key);
    List<String> getGlobalArrayConfig(String key);
    Map<String,String> GlobalMapConfigs(String key);
    List<Map<String,String>> getGlobalArrMapConfig(String key);
    
    
}
