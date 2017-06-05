package com.pursuit.noteblog.service.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.entity.Config;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.repository.ConfigRepository;
import com.pursuit.noteblog.service.ConfigService;
import com.pursuit.noteblog.util.ConstUtils;
@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService{

    private Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);
    
    @Autowired
    private ConfigRepository configRepository;
    @PostConstruct  //初始化方法的注解方式  等同:init-method=init  
    public void init() {
        reloadSystemConfig();
    }
    @PreDestroy //销毁方法的注解方式  等同于 xml:destory-method=destory
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
        ConstUtils.COOKIE_DOMAIN = MapUtils.getString(configMap, "cachecloud.cookie.domain", ConstUtils.DEFAULT_COOKIE_DOMAIN);
        logger.info("{}: {}", "ConstUtils.COOKIE_DOMAIN", ConstUtils.COOKIE_DOMAIN);
        
        logger.info("===========ConfigServiceImpl reload config end============");
		
	}

	@Override
	public SuccessEnum updateConfig(Map<String, String> configMap) {
        for (Entry<String, String> entry : configMap.entrySet()) {
            String configKey = entry.getKey();
            String configValue = entry.getValue();
            try {
            	Config config = configRepository.findByKey(configKey);
            	mongoTemplate.updateFirst(new Query(Criteria.where("id").is(config.getId())), new Update().set("key", configKey)
            			.set("value", configValue), Config.class);
            } catch (Exception e) {
                logger.error("key {} value {} update faily" + e.getMessage(), configKey, configValue, e);
                return SuccessEnum.FAIL;
            }
        }
        return SuccessEnum.SUCCESS;
	}

	@Override
	public List<Config> getConfigList(int status) {
        try {
            return configRepository.findByStatus(status);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
	}
	
	
    /**
     * 获取所有配置的key-value
     */
    private Map<String, String> getConfigMap() {
        Map<String, String> configMap = new LinkedHashMap<String, String>();
        Config config2 = new Config();
        config2.setKey("test1");
        config2.setValue("test1");
        config2.setStatus(1);
        configRepository.save(config2);
        List<Config> configList = configRepository.findByStatus(1);
        for (Config config : configList) {
            configMap.put(config.getKey(), config.getValue());
        }
        return configMap;
    }
//	this.GlobalAllConfigs = map[string]interface{}{}
//	this.GlobalStringConfigs = map[string]string{}
//	this.GlobalArrayConfigs = map[string][]string{}
//	this.GlobalMapConfigs = map[string]map[string]string{}
//	this.GlobalArrMapConfigs = map[string][]map[string]string{}
	@Override
	public String getGlobalStringConfig(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getGlobalArrayConfig(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String> GlobalMapConfigs(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Map<String, String>> getGlobalArrMapConfig(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
