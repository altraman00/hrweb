package com.mdl.zhaopin.service;

import com.mdl.zhaopin.DTO.ResumeBaseDTO;

import java.io.IOException;

public interface ParseResumeService {

	ResumeBaseDTO getResumeInfo(String file) throws IOException;

	void saveResume();

}
