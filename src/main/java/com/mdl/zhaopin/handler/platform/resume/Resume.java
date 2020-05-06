package com.mdl.zhaopin.handler.platform.resume;

import com.mdl.zhaopin.DTO.ResumeBaseDTO;
import com.mdl.zhaopin.DTO.ResumeParseEducationDTO;
import com.mdl.zhaopin.DTO.ResumeParseSkillsDTO;
import com.mdl.zhaopin.DTO.ResumeParseWorkExpDTO;

import java.util.List;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:37
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface Resume {

    //基本信息
    ResumeBaseDTO getResumeBase();

    //教育经历
    List<ResumeParseEducationDTO> getEduList();

    //工作经验
    List<ResumeParseWorkExpDTO> getWorkExpList();

    //技能
    List<ResumeParseSkillsDTO> getSkillList();

}
