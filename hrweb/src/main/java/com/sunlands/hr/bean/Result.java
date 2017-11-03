package com.sunlands.hr.bean;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
* @ClassName: Result 
* @Description: TODO返回状态码 
* @author xiekun
* @date 2017年11月1日 上午9:46:46 
*
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 200;

	public static final int ERROR = 500;

	public Integer code;// 状态

	public Object result;// 数据

	public String message;// 消息
	
	private void assertNotNull(){
		if(code == null){
			throw new RuntimeException("code can not be null");
		}
	}
	
	public String toJson() {
		assertNotNull();
		return new Gson().toJson(this).toString();
	}
	public String toJson(GsonBuilder gsonBuilder){
		assertNotNull();
		return gsonBuilder.create().toJson(this).toString();
	}
	
	public Result setData(Object result) {
		this.result = result;
		return this;
	}
	public Integer getCode() {
		return code;
	}
	public Result setCode(Integer code) {
		this.code = code;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public Result setMessage(String message) {
		this.message = message;
		return this;
	}
}
