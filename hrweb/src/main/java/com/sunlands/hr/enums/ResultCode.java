package com.sunlands.hr.enums;

import com.sunlands.hr.bean.Result;
import com.google.gson.GsonBuilder;

/**
 * 
* @ClassName: ResultCode 
* @Description: TODO 系统统一返回的result
* @author xiekun
* @date 2017年11月1日 上午9:45:12 
*
 */
public enum ResultCode {

	 SUCCESS(Result.SUCCESS,null)
	,ERROR(Result.ERROR,"系统错误")
	
	,MISS_PARAMS(10000,"缺少指定参数")//用户检验参数缺少的
	
	,LOGIN_PASSaWORD_ERROR(10010,"密码错误")
	,LOGIN_LOGINNAME_ERROR(10011,"登录名错误")
	
	;
	private Integer status;
	private String msg;
	
	ResultCode(Integer status,String msg){
		this.status = status;
		this.msg = msg;
	}
	
	//拿到Result对象
	public Result get(){
		return new Result().setCode(this.status).setMessage(this.msg);
	}
	
	public String toJson(){
		return this.get().toJson();
	}
	
	public String toJson(GsonBuilder gsonBuilder){
		return this.get().toJson(gsonBuilder);
	}
}
