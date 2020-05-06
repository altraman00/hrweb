package com.mdl.zhaopin.DTO;

import com.sunlands.zhaopin.entity.BiResumeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class ResumeBaseDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名字")
    private String jobSeekerName;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("出生年月日")
    private String birthday;

    @ApiModelProperty("学历code")
    private String degree;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    //出生地
    @ApiModelProperty("籍贯")
    private String bitrhPlace;

    //民族
    @ApiModelProperty("民族")
    private String nation;

    //户口所在地
    @ApiModelProperty("户口所在地")
    private String domicilePlace;

    //身份证号
    @ApiModelProperty("证件号")
    private String certificateNum;

    //婚姻状况
    @ApiModelProperty("已婚，未婚")
    private String isMarry;

    //居住状况
    @ApiModelProperty("居住状况")
    private String housingStatus;

    //当前住址
    @ApiModelProperty("目前住址")
    private String houseAddress;

    //健康状况
    @ApiModelProperty("健康状况")
    private String health;

    //政治面貌
    @ApiModelProperty("政治面貌 团员，党员等")
    private String politicalStatus;

    //兴趣爱好
    @ApiModelProperty("兴趣爱好")
    private String hobbies;

    //期望薪资
    @ApiModelProperty("期望最高薪资")
    private Integer expectSalaryMax;

    @ApiModelProperty("期望最低薪资")
    private Integer expectSalaryMin;

    @ApiModelProperty("期望工作地点")
    private String expectPlace;

    //目前在职状况
    @ApiModelProperty("求职状态")
    private String currentState;

    //自我评价
    @ApiModelProperty("自我评价")
    private String selfEvaluation;

    @ApiModelProperty("简历来源 10001：智联，10002:58同城，10003：猎聘，10004：Boss直聘，10005:前程无忧，10006：拉钩")
    private String platId;

    @ApiModelProperty("简历来源方式：190001：主动投递，190002：搜索后存入，190003：从简历管理上传后存入，190004：从人才库上传后存入")
    private String inWay;

    @ApiModelProperty("跟踪id")
    private String traceId;


    /**
     * dto转化为entity
     *
     * @return
     */
    public BiResumeEntity convertDTOToEntity() {
        ResumeBaseDTOToEntityConvert resumeConvert = new ResumeBaseDTOToEntityConvert();
        BiResumeEntity convert = resumeConvert.convert(this);
        return convert;
    }

    /**
     * entity转化为dto
     *
     * @return
     */
    public ResumeBaseDTO convertEntityToDTO(BiResumeEntity entity) {
        ResumeEntityToDTOConvert resumeConvert = new ResumeEntityToDTOConvert();
        ResumeBaseDTO convert = resumeConvert.convert(entity);
        return convert;
    }

    /**
     * dto转化为数据库能用的entity
     */
    private class ResumeBaseDTOToEntityConvert implements DTOConvert<ResumeBaseDTO, BiResumeEntity> {
        @Override
        public BiResumeEntity convert(ResumeBaseDTO dto) {
            BiResumeEntity entity = new BiResumeEntity();
            entity.setJobSeekerName(dto.getJobSeekerName());
            entity.setDegree(dto.getDegree());
            entity.setCurrentState(dto.getCurrentState());
            entity.setInWay(dto.getInWay());
            entity.setPhone(dto.getPhone());
            entity.setPlatId(dto.getPlatId());
            entity.setEmail(dto.getEmail());
            entity.setAge(dto.getAge());
            entity.setSex(dto.getSex());
            entity.setIsMarry(dto.getIsMarry());
            entity.setExpectSalaryMax(dto.getExpectSalaryMax());
            entity.setExpectSalaryMin(dto.getExpectSalaryMin());
            entity.setExpectPlace(dto.getExpectPlace());
            entity.setPoliticalStatus(dto.getPoliticalStatus());
            entity.setNation(dto.getNation());
            entity.setBitrhPlace(dto.getBitrhPlace());
            entity.setCertificateNum(dto.getCertificateNum());
            entity.setHealth(dto.getHealth());
            entity.setHobbies(dto.getHobbies());
            entity.setHousingStatus(dto.getHousingStatus());
            entity.setHouseAddress(dto.getHouseAddress());
            entity.setDomicilePlace(dto.getDomicilePlace());
            entity.setSelfEvaluation(dto.getSelfEvaluation());
            entity.setTraceId(dto.getTraceId());
//            biResumeEntity.setState();
//            biResumeEntity.setBirthday();
//            biResumeEntity.setWorkLengthMax();
//            biResumeEntity.setWorkLengthMin();
//            biResumeEntity.setWorkLength();
//            biResumeEntity.setJobTitle();
//            biResumeEntity.setResideAddress();
//            biResumeEntity.setExpectJob();
//            biResumeEntity.setExpectIndustry();
//            biResumeEntity.setPhotoPath();
//            biResumeEntity.setExpectJobProperty();
//            biResumeEntity.setUniversity();
//            biResumeEntity.setMajor();
//            biResumeEntity.setCertificateType();
//            biResumeEntity.setWechatNum();
//            biResumeEntity.setHouseArea();
//            biResumeEntity.setResumeDetail();
            return entity;
        }
    }


    /**
     * entity转化为数据库能用的dto
     */
    private class ResumeEntityToDTOConvert implements DTOConvert<BiResumeEntity, ResumeBaseDTO> {
        @Override
        public ResumeBaseDTO convert(BiResumeEntity entity) {
            ResumeBaseDTO dto = new ResumeBaseDTO();
            dto.setId(entity.getId());
            dto.setJobSeekerName(entity.getJobSeekerName());
            dto.setDegree(entity.getDegree());
            dto.setCurrentState(entity.getCurrentState());
            dto.setInWay(entity.getInWay());
            dto.setPhone(entity.getPhone());
            dto.setPlatId(entity.getPlatId());
            dto.setEmail(entity.getEmail());
            dto.setAge(entity.getAge());
            dto.setSex(entity.getSex());
            dto.setIsMarry(entity.getIsMarry());
            dto.setExpectSalaryMax(entity.getExpectSalaryMax());
            dto.setExpectSalaryMin(entity.getExpectSalaryMin());
            dto.setExpectPlace(entity.getExpectPlace());
            dto.setPoliticalStatus(entity.getPoliticalStatus());
            dto.setNation(entity.getNation());
            dto.setBitrhPlace(entity.getBitrhPlace());
            dto.setCertificateNum(entity.getCertificateNum());
            dto.setHealth(entity.getHealth());
            dto.setHobbies(entity.getHobbies());
            dto.setHousingStatus(entity.getHousingStatus());
            dto.setHouseAddress(entity.getHouseAddress());
            dto.setDomicilePlace(entity.getDomicilePlace());
            dto.setSelfEvaluation(entity.getSelfEvaluation());
            return dto;
        }
    }


}
