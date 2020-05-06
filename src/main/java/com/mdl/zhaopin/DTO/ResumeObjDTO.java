package com.mdl.zhaopin.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Project : resume-parse
 * @Package Name : com.sunlands.zhaopin.DTO
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月21日 18:29
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Data
public class ResumeObjDTO {

    @ApiModelProperty("基本信息")
    private ResumeBaseDTO resumeBase;

    @ApiModelProperty("教育经历")
    private List<ResumeParseEducationDTO> eduList;

    @ApiModelProperty("工作经验")
    private List<ResumeParseWorkExpDTO> workExpList;

    @ApiModelProperty("技能")
    private List<ResumeParseSkillsDTO> skillList;


}
