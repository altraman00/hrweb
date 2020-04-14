package com.mdl.zhaopin.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class ResumeBaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age = 18;

    @ApiModelProperty("工作年限")
    private String workLength;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("学历")
    private String degree;

    @ApiModelProperty("学校")
    private String university;

    @ApiModelProperty("期望工作地点")
    private String expectPlace;

    @ApiModelProperty("专业")
    private String major;

    @ApiModelProperty("职业")
    private String profession;

}
