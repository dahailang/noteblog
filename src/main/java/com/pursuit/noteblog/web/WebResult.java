package com.pursuit.noteblog.web;

import java.util.HashMap;
import java.util.Map;

public class WebResult {
    // 响应业务状态
    private Integer status;
    // 响应业务状态
    private boolean ok;
	// 响应消息
    private String msg;
    
    private Map<String, Object> attributes = null;

    public WebResult() {
    	
    }
    
    public void addAttribute(String key,Object obj){
    	if(null==attributes){
    		attributes = new HashMap<>();
    	}
    	attributes.put(key, obj);
    }
    public Map<String, Object> getAttributes(){
    	return attributes;
    }
    public static WebResult ok() {
        return new WebResult(200,true,"OK");
    }
    public static WebResult ok(String msg) {
    	return new WebResult(200,true,msg);
    }
    public static WebResult ok(Map<String,Object> map) {
    	return new WebResult(200,true,"OK",map);
    }
    public static WebResult fail(String msg) {
    	return new WebResult(500,false,msg);
    }
    public WebResult(Integer status, boolean ok,String msg) {
        this.status = status;
        this.ok = ok;
        this.msg = msg;
    }
    public WebResult(Integer status, boolean ok,String msg,Map<String,Object> map) {
    	this.status = status;
    	this.ok = ok;
    	this.msg = msg;
    	this.attributes = map;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
