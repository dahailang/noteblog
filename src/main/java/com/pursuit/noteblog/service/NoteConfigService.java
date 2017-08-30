package com.pursuit.noteblog.service;

import java.util.List;
import java.util.Map;

import com.pursuit.noteblog.po.NoteConfig;

public interface NoteConfigService {
	 /**
     * 加载配置
     */
    public void reloadSystemConfig();

    /**
     * 更新配置
     * @param configMap
     * @return
     */
    public String updateConfig(Map<String, String> configMap);

    /**
     * 获取配置列表
     * @param status
     * @return
     */
    public List<NoteConfig> getConfigList(int status);
    
    
    
}
