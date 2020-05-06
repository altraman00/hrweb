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
@Data
@Entity
@Table(name = "bi_resume_work_exp")
public class BiResumeWorkExpEntity extends BaseEntity{

    @ApiModelProperty("简历id")
    @Column(name = "resume_id")
    private String resumeId;

    @ApiModelProperty("工作的开始日期")
    @Column(name = "on_job_from")
    private String onJobFrom;

    @ApiModelProperty("工作的结束日期")
    @Column(name = "on_job_to")
    private String onJobTo;

    @ApiModelProperty("工作年限")
    @Column(name = "work_length")
    private Integer workLength;

    @ApiModelProperty("公司名字")
    @Column(name = "company_name")
    private String companyName;

    @ApiModelProperty("职位名称")
    @Column(name = "position_name")
    private String positionName;

//    @ApiModelProperty("部门名称")
//    @Column(name = "dept_name")
//    private String deptName;

    @ApiModelProperty("薪酬（税前）")
    @Column(name = "salary")
    private String salary;

    @ApiModelProperty("证明人")
    @Column(name = "certifier_name")
    private String certifierName;

    @ApiModelProperty("证明人手机号")
    @Column(name = "certifier_phone")
    private String certifierPhone;

    @ApiModelProperty("工作内容")
    @Column(name = "work_content")
    private String workContent;

    @ApiModelProperty("备注")
    @Column(name = "comment")
    private String comment;

//    @ApiModelProperty("离职原因")
//    @Column(name = "leave_reason")
//    private String leaveReason;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiResumeWorkExpEntity that = (BiResumeWorkExpEntity) o;
        return Objects.equals(resumeId, that.resumeId) &&
                Objects.equals(onJobFrom, that.onJobFrom) &&
                Objects.equals(onJobTo, that.onJobTo) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(positionName, that.positionName) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(certifierName, that.certifierName) &&
                Objects.equals(certifierPhone, that.certifierPhone) &&
                Objects.equals(workContent, that.workContent) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeId, onJobFrom, onJobTo, companyName, positionName, salary, certifierName, certifierPhone, workContent, comment);
    }
}
