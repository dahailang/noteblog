package com.pursuit.noteblog.entity;

public class SystemConfig extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	public String configKey;//配置key
	public String configValue;//配置value',
	public String info;//配置说明
	public int status; // 1:可用,0:不可用',
	public int order_id; //顺序
	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	@Override
	public String toString() {
		return "SystemConfig [configKey=" + configKey + ", configValue=" + configValue + ", info=" + info
				+ ", status=" + status + ", order_id=" + order_id + "]";
	}

}
