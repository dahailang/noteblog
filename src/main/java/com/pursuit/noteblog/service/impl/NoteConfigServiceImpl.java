package com.pursuit.noteblog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pursuit.noteblog.dao.NoteConfigMapper;
import com.pursuit.noteblog.po.NoteConfig;
import com.pursuit.noteblog.service.NoteConfigService;
import com.pursuit.noteblog.util.ConstUtils;

public class NoteConfigServiceImpl  implements NoteConfigService{

    private Logger logger = LoggerFactory.getLogger(NoteConfigServiceImpl.class);
    
    @Autowired
    private NoteConfigMapper noteConfigMapper;
   

    public void init() {
       reloadSystemConfig();
    }

    public void destroy(){
    	System.out.println("调用销毁化方法....");  
    }
	@Override
	public void reloadSystemConfig() {
		logger.info("===========ConfigServiceImpl reload config start============");
        // 加载配置
        Map<String, String> configMap = getConfigMap();

        // 管理员配置
        ConstUtils.ADMIN_EMAILS = MapUtils.getString(configMap, "noteblog.owner.email", ConstUtils.DEFAULT_ADMIN_EMAILS);
        logger.info("{}: {}", "ConstUtils.EMAILS", ConstUtils.ADMIN_EMAILS);

        ConstUtils.ADMIN_PHONES = MapUtils.getString(configMap, "noteblog.owner.phone", ConstUtils.DEFAULT_ADMIN_PHONES);
        logger.info("{}: {}", "ConstUtils.PHONES", ConstUtils.ADMIN_PHONES);

        //cookie登录方式所需要的domain
        ConstUtils.COOKIE_DOMAIN = MapUtils.getString(configMap, "noteblog.cookie.domain", ConstUtils.DEFAULT_COOKIE_DOMAIN);
        logger.info("{}: {}", "ConstUtils.COOKIE_DOMAIN", ConstUtils.COOKIE_DOMAIN);
        
        logger.info("===========ConfigServiceImpl reload config end============");
		
	}

	@Override
	public String updateConfig(Map<String, String> configMap) {
        for (Entry<String, String> entry : configMap.entrySet()) {
            String configKey = entry.getKey();
            String configValue = entry.getValue();
            try {
            	System.out.println("执行更新操作");
            } catch (Exception e) {
                logger.error("key {} value {} update faily" + e.getMessage(), configKey, configValue, e);
                return "fail";
                
            }
        }
        return "success";
        		
	}
	
    /**
     * 获取所有配置的key-value
     */
    private Map<String, String> getConfigMap() {
    	List<NoteConfig> result = noteConfigMapper.getAllConfig();
    	Map<String, String> configmap = new HashMap<>();
    	for (NoteConfig config :result ) {
    		configmap.put(config.getConfigKey(), config.getConfigValue());
		}
        return configmap;
    }
    @Override
    public List<NoteConfig> getConfigList(int status) {
    	return null;
    }
    
}
