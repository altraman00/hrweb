package com.mdl.zhaopin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.entity
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月21日 18:50
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Entity
@Data
@Table(name = "bi_resume_skills")
public class BiResumeSkillEntity extends BaseEntity{

    @ApiModelProperty("简历id")
    @Column(name = "resume_id")
    private String resumeId;

    @ApiModelProperty("技能名字")
    @Column(name = "skill_name")
    private String skillName;

    @ApiModelProperty("掌握程度")
    @Column(name = "level")
    private String level;


}
