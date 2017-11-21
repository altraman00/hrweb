package ${servicePackage}.controller;

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

import ${servicePackage}.bean.${interfaceName1}In;
import ${servicePackage}.bean.${interfaceName1}Out;
import ${servicePackage}.service.${interfaceName1}Service;



@Api(description = "${interfaceName} 控制器")
@Controller
@RequestMapping("/${interfaceName}")
public class ${interfaceName1}Controller extends BaseController{

	private static Logger log = Logger.getLogger(${interfaceName1}Controller.class);
	
	public ${interfaceName1}Controller() {
		log.debug("${interfaceName1}Controller");
	}
	
	@Autowired
    private ${interfaceName1}Service service;
    
    @ApiOperation(value = "${codeName}", httpMethod = "GET", produces = "application/json")
    @ApiResponse(code = 200, message = "success", response = ${interfaceName1}Out.class)
    @ResponseBody
    @RequestMapping(value="/query$!{interfaceName}", method = RequestMethod.GET, produces = "application/json")
    public ${interfaceName1}Out ${interfaceName}(
    		 @ApiParam(value = "这里是入参名称") @RequestBody ${interfaceName1}In inData
    		,@ApiParam(name = "userId", required = true, value = "这里也是入参名称") @RequestParam("xyzId") int xyzId
    		,HttpServletRequest request
    		,HttpServletResponse response){
    		
    	${interfaceName1}Out outData = service.query${interfaceName}(inData);
    	
    	InterfaceData result = null;
		
		if(true){
			result = InterfaceData.SUCCESS("******");
		}
		response.setStatus(200);
		
		HtmlUtil.writerJson(response, null);
    	
    	return outData;
    }
}
