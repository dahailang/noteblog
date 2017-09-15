package com.pursuit.noteblog.web;

import java.util.HashMap;
import java.util.Map;

public class  NBResult{
    // 响应业务状态
    private Integer status;
    // 响应业务状态
    private boolean ok;
	// 响应消息
    private String msg;
    // 内部数据
    private Object info;
    
    private Map<String, Object> datas = null;
   
    public void addAttribute(String key,Object obj){
    	if(null==datas){
    		datas = new HashMap<>();
    	}
    	datas.put(key, obj);
    }
    public Map<String, Object> getAttributes(){
    	return datas;
    }
    public static NBResult ok() {
        return new NBResult(200,true,"OK");
    }
    public static NBResult ok(Object info) {
    	return new NBResult(200,true,"OK",info);
    }
    public static NBResult ok(String msg) {
    	return new NBResult(200,true,msg);
    }
    public static NBResult ok(Map<String,Object> map) {
    	return new NBResult(200,true,"OK",map);
    }
    public static NBResult fail(String msg) {
    	return new NBResult(500,false,msg);
    }
    public NBResult(Integer status, boolean ok,String msg) {
        this.status = status;
        this.ok = ok;
        this.msg = msg;
    }
    public NBResult(Integer status, boolean ok,String msg,Object info) {
    	this.status = status;
    	this.ok = ok;
    	this.msg = msg;
    	this.info = info;
    }
    public NBResult(Integer status, boolean ok,String msg,Map<String,Object> map) {
    	this.status = status;
    	this.ok = ok;
    	this.msg = msg;
    	this.datas = map;
    }

    public Integer getStatus() {
        return status;
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
	public Object getInfo() {
		return info;
	}
}
