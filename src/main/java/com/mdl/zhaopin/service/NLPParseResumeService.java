package com.mdl.zhaopin.service;


import com.mdl.zhaopin.DTO.ResumeBaseDTO;

import java.io.IOException;

public interface NLPParseResumeService {

	ResumeBaseDTO getResumeInfo(String file) throws IOException;

}
