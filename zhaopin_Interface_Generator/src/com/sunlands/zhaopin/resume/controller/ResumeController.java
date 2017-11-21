package com.sunlands.zhaopin.resume.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.sunlands.common.base.InterfaceData;
import com.sunlands.common.controller.BaseController;
import com.sunlands.common.utils.HtmlUtil;
import com.sunlands.zhaopin.user.bean.User;
import com.sunlands.zhaopin.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

import org.apache.log4j.Logger;

import com.sunlands.zhaopin.resume.bean.ResumeIn;
import com.sunlands.zhaopin.resume.bean.ResumeOut;
import com.sunlands.zhaopin.resume.service.ResumeService;



@Api(description = "resume 控制器")
@Controller
@RequestMapping("/resume")
public class ResumeController extends BaseController{

	private static Logger log = Logger.getLogger(ResumeController.class);
	
	public ResumeController() {
		log.debug("ResumeController");
	}
	
	@Autowired
    private ResumeService service;
    
    @ApiOperation(value = "简历列表", httpMethod = "GET", produces = "application/json")
    @ApiResponse(code = 200, message = "success", response = ResumeOut.class)
    @ResponseBody
    @RequestMapping(value="/queryresume", method = RequestMethod.GET, produces = "application/json")
    public ResumeOut resume(
    		 @ApiParam(value = "这里是入参名称") @RequestBody ResumeIn inData
    		,@ApiParam(name = "userId", required = true, value = "这里也是入参名称") @RequestParam("xyzId") int xyzId
    		,HttpServletRequest request
    		,HttpServletResponse response){
    		
    	ResumeOut outData = service.queryresume(inData);
    	
    	InterfaceData result = null;
		
		if(true){
			result = InterfaceData.SUCCESS("******");
		}
		response.setStatus(200);
		
		HtmlUtil.writerJson(response, null);
    	
    	return outData;
    }
}
