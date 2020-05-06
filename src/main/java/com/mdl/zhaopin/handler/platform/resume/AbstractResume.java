package com.mdl.zhaopin.handler.platform.resume;

import com.mdl.zhaopin.DTO.ResumeBaseDTO;
import com.mdl.zhaopin.DTO.ResumeParseEducationDTO;
import com.mdl.zhaopin.DTO.ResumeParseSkillsDTO;
import com.mdl.zhaopin.DTO.ResumeParseWorkExpDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:36
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public abstract class AbstractResume implements Resume {

    @ApiModelProperty("基本信息")
    private ResumeBaseDTO resumeBase;

    @ApiModelProperty("教育经历")
    private List<ResumeParseEducationDTO> eduList;

    @ApiModelProperty("工作经验")
    private List<ResumeParseWorkExpDTO> workExpList;

    @ApiModelProperty("技能")
    private List<ResumeParseSkillsDTO> skillList;


    @Override
    public ResumeBaseDTO getResumeBase() {
        return resumeBase;
    }

    @Override
    public List<ResumeParseEducationDTO> getEduList() {
        return eduList;
    }

    @Override
    public List<ResumeParseWorkExpDTO> getWorkExpList() {
        return workExpList;
    }

    @Override
    public List<ResumeParseSkillsDTO> getSkillList() {
        return skillList;
    }

    public void setResumeBase(ResumeBaseDTO resumeBase) {
        this.resumeBase = resumeBase;
    }

    public void setEduList(List<ResumeParseEducationDTO> eduList) {
        this.eduList = eduList;
    }

    public void setWorkExpList(List<ResumeParseWorkExpDTO> workExpList) {
        this.workExpList = workExpList;
    }

    public void setSkillList(List<ResumeParseSkillsDTO> skillList) {
        this.skillList = skillList;
    }
}
