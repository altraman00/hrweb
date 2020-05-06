package com.mdl.zhaopin.service;

import com.mdl.zhaopin.DTO.ResumeObjDTO;
import com.mdl.zhaopin.DTO.ResumePlatformPlugDTO;
import com.mdl.zhaopin.common.exception.ResumeParseException;
import com.mdl.zhaopin.common.exception.ResumePlatformException;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.service
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月21日 15:27
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface ResumeService {

    /**
     * 解析简历
     * @param parseResumeInfo
     * @return
     * @throws ResumePlatformException
     * @throws ResumeParseException
     */
    String parseResumeInfo(ResumePlatformPlugDTO parseResumeInfo) throws ResumePlatformException, ResumeParseException;

    /**
     * 保存简历
     * @param resumeObjDTO
     */
    String saveResumeInfo(ResumeObjDTO resumeObjDTO, String resumeHtml);

    /**
     * 根据简历id查找简历
     * @param id
     * @return
     */
    ResumeObjDTO findFirstByResumeId(String id);

}
