package com.mdl.zhaopin.DTO;

import com.mdl.zhaopin.entity.BiResumeEducationEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResumeParseEducationDTO extends BaseDTO implements Serializable {

    @ApiModelProperty("简历id")
    private String resumeId;

    @ApiModelProperty("开始时间")
    private String educationFrom;

    @ApiModelProperty("结束时间")
    private String educationTo;

    @ApiModelProperty("毕业院校")
    private String university;

    @ApiModelProperty("专业")
    private String major;

//    @ApiModelProperty("学历code")
//    private String education;

    @ApiModelProperty("学历：本科、大专、中专、高中、初中、硕士、博士、其他")
    private String educationName;

    @ApiModelProperty("学位：无、学士、硕士、博士")
    private String degree;

//    @ApiModelProperty("学历性质 ：统招、自考、成人、其它")
//    private String educationType;

//    @ApiModelProperty("备注")
//    private String comment;


    /**
     * dto转化为entity
     *
     * @return
     */
    public BiResumeEducationEntity convertDTOToEntity() {
        ResumeEduDTOToEntityConvert eduConvert = new ResumeEduDTOToEntityConvert();
        BiResumeEducationEntity convert = eduConvert.convert(this);
        return convert;
    }

    /**
     * entity转化为dto
     *
     * @return
     */
    public ResumeParseEducationDTO convertEntityToDTO(BiResumeEducationEntity entity) {
        ResumeEduEntityToDTOConvert eduConvert = new ResumeEduEntityToDTOConvert();
        ResumeParseEducationDTO convert = eduConvert.convert(entity);
        return convert;
    }

    /**
     * dto转化为数据库能用的entity
     */
    private class ResumeEduDTOToEntityConvert implements DTOConvert<ResumeParseEducationDTO, BiResumeEducationEntity> {
        @Override
        public BiResumeEducationEntity convert(ResumeParseEducationDTO dto) {
            BiResumeEducationEntity entity = new BiResumeEducationEntity();
            entity.setResumeId(dto.getResumeId());
            entity.setUniversity(dto.getUniversity());
            entity.setEducationName(dto.getEducationName());
            entity.setDegree(dto.getDegree());
            entity.setMajor(dto.getMajor());
            entity.setEducationFrom(dto.getEducationFrom());
            entity.setEducationTo(dto.getEducationTo());
//            biResumeEducationEntity.setEducationType();
//            biResumeEducationEntity.setEducation();
//            biResumeEducationEntity.setComment();
            return entity;
        }
    }


    /**
     * entity转化为dto
     */
    private class ResumeEduEntityToDTOConvert implements DTOConvert<BiResumeEducationEntity, ResumeParseEducationDTO> {
        @Override
        public ResumeParseEducationDTO convert(BiResumeEducationEntity entity) {
            ResumeParseEducationDTO dto = new ResumeParseEducationDTO();
            dto.setId(entity.getId());
            dto.setResumeId(entity.getResumeId());
            dto.setUniversity(entity.getUniversity());
            dto.setEducationName(entity.getEducationName());
            dto.setDegree(entity.getDegree());
            dto.setMajor(entity.getMajor());
            dto.setEducationFrom(entity.getEducationFrom());
            dto.setEducationTo(entity.getEducationTo());
            return dto;
        }
    }


}
