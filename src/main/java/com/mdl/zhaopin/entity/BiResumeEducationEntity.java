package com.mdl.zhaopin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Project : zhaopin
 * @Package Name : com.mdl.zhaopin.model.entity
 * @Description : TODO
 * @Author : eleven
 * @Create Date : 2019年09月20日 17:27
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Entity
@Data
@Table(name = "bi_resume_education")
public class BiResumeEducationEntity extends BaseEntity{


    @Column(name = "resume_id")
    private String resumeId;

    @ApiModelProperty("毕业院校")
    @Column(name = "university")
    private String university;

    @ApiModelProperty("学历：本科、大专、中专、高中、初中、硕士、博士、其他")
    @Column(name = "education")
    private String education;

    @Column(name = "education_name")
    private String educationName;

    @ApiModelProperty("学位：无、学士、硕士、博士")
    @Column(name = "degree")
    private String degree;

    @ApiModelProperty("学历性质 ：统招、自考、成人、其它")
    @Column(name = "education_type")
    private String educationType;

    @ApiModelProperty("专业")
    @Column(name = "major")
    private String major;

    @ApiModelProperty("开始时间")
    @Column(name = "education_from")
    private String educationFrom;

    @ApiModelProperty("结束时间")
    @Column(name = "education_to")
    private String educationTo;

    @ApiModelProperty("备注")
    @Column(name = "comment")
    private String comment;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiResumeEducationEntity that = (BiResumeEducationEntity) o;
        return Objects.equals(resumeId, that.resumeId) &&
                Objects.equals(university, that.university) &&
                Objects.equals(education, that.education) &&
                Objects.equals(degree, that.degree) &&
                Objects.equals(educationType, that.educationType) &&
                Objects.equals(major, that.major) &&
                Objects.equals(educationFrom, that.educationFrom) &&
                Objects.equals(educationTo, that.educationTo) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeId, university, education, degree, educationType, major, educationFrom, educationTo, comment);
    }
}
