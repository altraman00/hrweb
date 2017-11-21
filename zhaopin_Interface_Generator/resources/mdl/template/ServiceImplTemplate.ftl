package ${servicePackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${servicePackage}.bean.${interfaceName1}In;
import ${servicePackage}.bean.${interfaceName1}Out;
import ${servicePackage}.dao.${interfaceName1}Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${servicePackage}.service.${interfaceName1}Service;


@Service
public class ${interfaceName1}ServiceImpl implements ${interfaceName1}Service{

	private final static Logger log = LoggerFactory.getLogger(${interfaceName1}Service.class);
	
	@Autowired
    private ${interfaceName1}Dao ${interfaceName}dao;
    
	public ${interfaceName1}Out query$!{interfaceName}(${interfaceName1}In inData){
		
		log.info("******");
		
		//TODO:请在这里写业务代码
		
		return null;
	}
}
