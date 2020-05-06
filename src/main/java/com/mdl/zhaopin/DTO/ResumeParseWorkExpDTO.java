package com.mdl.zhaopin.DTO;

import com.mdl.zhaopin.entity.BiResumeWorkExpEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResumeParseWorkExpDTO extends BaseDTO implements Serializable {

    @ApiModelProperty("简历id")
    private String resumeId;

    @ApiModelProperty("工作的开始日期")
    private String onJobFrom;

    @ApiModelProperty("工作的结束日期")
    private String onJobTo;

    @ApiModelProperty("工作年限")
    private Integer workLength;

    @ApiModelProperty("公司名字")
    private String companyName;

    @ApiModelProperty("职位名称")
    private String positionName;

//    @ApiModelProperty("部门名称")
//    private String deptName;

    @ApiModelProperty("离职薪酬")
    private String salary;

    @ApiModelProperty("证明人")
    private String certifierName;

    @ApiModelProperty("证明人手机号")
    private String certifierPhone;

    @ApiModelProperty("工作内容")
    private String workContent;

//    @ApiModelProperty("备注")
//    private String comment;

//    @ApiModelProperty("离职原因")
//    private String leaveReason;


    /**
     * dto转化为entity
     * @return
     */
    public BiResumeWorkExpEntity convertDTOToEntity(){
        ResumeWorkExpDTOToEntityConvert resumeConvert = new ResumeWorkExpDTOToEntityConvert();
        BiResumeWorkExpEntity convert = resumeConvert.convert(this);
        return convert;
    }

    /**
     * entity转化为dto
     * @return
     */
    public ResumeParseWorkExpDTO convertEntityToDTO(BiResumeWorkExpEntity entity){
        ResumeWorkExpEntityToDTOConvert resumeConvert = new ResumeWorkExpEntityToDTOConvert();
        ResumeParseWorkExpDTO convert = resumeConvert.convert(entity);
        return convert;
    }


    /**
     * dto转化为entity
     */
    private class ResumeWorkExpDTOToEntityConvert implements DTOConvert<ResumeParseWorkExpDTO,BiResumeWorkExpEntity> {
        @Override
        public BiResumeWorkExpEntity convert(ResumeParseWorkExpDTO dto) {
            BiResumeWorkExpEntity entity = new BiResumeWorkExpEntity();
            entity.setResumeId(dto.getResumeId());
            entity.setOnJobFrom(dto.getOnJobFrom());
            entity.setOnJobTo(dto.getOnJobTo());
            entity.setCompanyName(dto.getCompanyName());
            entity.setPositionName(dto.getPositionName());
            entity.setSalary(dto.getSalary());
            entity.setCertifierName(dto.getCertifierName());
            entity.setCertifierPhone(dto.getCertifierPhone());
            entity.setWorkContent(dto.getWorkContent());
//            entity.setComment(dto.getComment());
//            biResumeWorkExpEntity.setDeptName();
//            biResumeWorkExpEntity.setLeaveReason();
            return entity;
        }
    }


    /**
     * entity转化为dto
     */
    private class ResumeWorkExpEntityToDTOConvert implements DTOConvert<BiResumeWorkExpEntity,ResumeParseWorkExpDTO> {
        @Override
        public ResumeParseWorkExpDTO convert(BiResumeWorkExpEntity entity) {
            ResumeParseWorkExpDTO dto = new ResumeParseWorkExpDTO();
            dto.setId(entity.getId());
            dto.setResumeId(entity.getResumeId());
            dto.setOnJobFrom(entity.getOnJobFrom());
            dto.setOnJobTo(entity.getOnJobTo());
            dto.setCompanyName(entity.getCompanyName());
            dto.setPositionName(entity.getPositionName());
            dto.setSalary(entity.getSalary());
            dto.setCertifierName(entity.getCertifierName());
            dto.setCertifierPhone(entity.getCertifierPhone());
            dto.setWorkContent(entity.getWorkContent());
//            dto.setComment(entity.getComment());
//            biResumeWorkExpEntity.setDeptName();
//            biResumeWorkExpEntity.setLeaveReason();
            return dto;
        }
    }


}
