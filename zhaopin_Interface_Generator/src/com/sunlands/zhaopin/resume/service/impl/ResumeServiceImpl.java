package com.sunlands.zhaopin.resume.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sunlands.zhaopin.resume.bean.ResumeIn;
import com.sunlands.zhaopin.resume.bean.ResumeOut;
import com.sunlands.zhaopin.resume.dao.ResumeDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunlands.zhaopin.resume.service.ResumeService;


@Service
public class ResumeServiceImpl implements ResumeService{

	private final static Logger log = LoggerFactory.getLogger(ResumeService.class);
	
	@Autowired
    private ResumeDao resumedao;
    
	public ResumeOut queryresume(ResumeIn inData){
		
		log.info("******");
		
		//TODO:请在这里写业务代码
		
		return null;
	}
}
