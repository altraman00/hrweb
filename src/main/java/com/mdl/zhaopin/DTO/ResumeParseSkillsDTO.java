package com.mdl.zhaopin.DTO;

import com.mdl.zhaopin.entity.BiResumeSkillEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class ResumeParseSkillsDTO extends BaseDTO implements Serializable {

    @ApiModelProperty("简历id")
    private String resumeId;

    @ApiModelProperty("技能名字")
    private String skillName;

    @ApiModelProperty("掌握程度")
    private String level;

    /**
     * dto转化为entity
     * @return
     */
    public BiResumeSkillEntity convertDTOToEntity(){
        ResumeSkillsDTOToEntityConvert resumeConvert = new ResumeSkillsDTOToEntityConvert();
        BiResumeSkillEntity convert = resumeConvert.convert(this);
        return convert;
    }


    /**
     * entity转dto
     * @return
     */
    public ResumeParseSkillsDTO convertEntityToDTO(BiResumeSkillEntity entity){
        ResumeSkillsEntityToDTOConvert resumeConvert = new ResumeSkillsEntityToDTOConvert();
        ResumeParseSkillsDTO convert = resumeConvert.convert(entity);
        return convert;
    }


    /**
     * dto转entity
     */
    private class ResumeSkillsDTOToEntityConvert implements DTOConvert<ResumeParseSkillsDTO,BiResumeSkillEntity> {
        @Override
        public BiResumeSkillEntity convert(ResumeParseSkillsDTO dto) {
            BiResumeSkillEntity biResumeSkillEntity = new BiResumeSkillEntity();
            biResumeSkillEntity.setResumeId(dto.getResumeId());
            biResumeSkillEntity.setSkillName(dto.getSkillName());
            biResumeSkillEntity.setLevel(dto.getLevel());
            return biResumeSkillEntity;
        }
    }


    /**
     * entity转dto
     */
    private class ResumeSkillsEntityToDTOConvert implements DTOConvert<BiResumeSkillEntity,ResumeParseSkillsDTO> {
        @Override
        public ResumeParseSkillsDTO convert(BiResumeSkillEntity entity) {
            ResumeParseSkillsDTO dto = new ResumeParseSkillsDTO();
            dto.setId(entity.getId());
            dto.setResumeId(entity.getResumeId());
            dto.setSkillName(entity.getSkillName());
            dto.setLevel(entity.getLevel());
            return dto;
        }
    }



}
