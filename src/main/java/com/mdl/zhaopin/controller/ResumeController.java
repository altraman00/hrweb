package com.mdl.zhaopin.controller;

import com.mdl.zhaopin.DTO.ResumeObjDTO;
import com.mdl.zhaopin.DTO.ResumePlatformPlugDTO;
import com.mdl.zhaopin.common.exception.ResumeParseException;
import com.mdl.zhaopin.common.exception.ResumePlatformException;
import com.mdl.zhaopin.common.response.BaseResponse;
import com.mdl.zhaopin.common.response.ResultCode;
import com.mdl.zhaopin.service.ResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.controller
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月21日 10:45
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    private ResumeService resumeService;

    @Value("${zhaopin.resume.parse.check.url}")
    private String zpCheckUrl;


    /**
     * 解析插件获取的html简历
     * @param parseResumeInfo
     * @return
     */
    @PostMapping("/plug/html")
    public BaseResponse<Map<String,String>> parseResumeInfo(@RequestBody ResumePlatformPlugDTO parseResumeInfo) {
        logger.debug("【ResumeController】platform:{},resumeUrl:{}",parseResumeInfo.getPlatform(),parseResumeInfo.getResumeUrl());
        String tmpId;
        try {
            tmpId = resumeService.parseResumeInfo(parseResumeInfo);
        } catch (ResumeParseException e) {
            return new BaseResponse<>(e.getCode(), e.getMsg());
        } catch (ResumePlatformException e) {
            return new BaseResponse<>(e.getCode(), e.getMsg());
        }

        String targetUrl = String.format(zpCheckUrl,tmpId);
        logger.debug("【ResumeController】解析后跳转地址:{}",targetUrl);

        Map<String,String> resMap = new HashMap<String,String>(){{
            put("targetUrl",targetUrl);
        }};

        return new BaseResponse<>(ResultCode.SUCCESS,resMap);
    }

    /**
     * 根据简历id查询简历信息
     *
     * @param resumeId 该简历id是简历解析库的临时简历id，和招聘系统的简历id不是一回事
     * @return
     */
    @GetMapping("/{tmpId}")
    public BaseResponse<ResumeObjDTO> findFirstByResumeId(@PathVariable(value = "tmpId") String resumeId) {
        logger.debug("【ResumeController】id:{}", resumeId);
        ResumeObjDTO dto = resumeService.findFirstByResumeId(resumeId);
        return new BaseResponse<>(ResultCode.SUCCESS, dto);
    }


    /**
     * 保存简历信息
     *
     * @param resumeBaseDTO
     * @return
     */
    @PostMapping("/save")
    public BaseResponse<?> saveResumeInfo(@RequestBody ResumeObjDTO resumeBaseDTO) {
        logger.debug("【ResumeController】saveResumeInfo:{}", resumeBaseDTO);
        resumeService.saveResumeInfo(resumeBaseDTO,"");
        return new BaseResponse<>(ResultCode.SUCCESS);
    }

}
