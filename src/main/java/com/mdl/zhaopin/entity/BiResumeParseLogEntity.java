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
 * @Create Date : 2020年04月24日 17:20
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Entity
@Data
@Table(name = "bi_resume_parse_log")
public class BiResumeParseLogEntity extends BaseEntity{

    @ApiModelProperty("简历id")
    @Column(name = "resume_id")
    private String resumeId;

    @ApiModelProperty("平台")
    @Column(name = "platform")
    private String platform;

    @ApiModelProperty("插件获取的html文件")
    @Column(name = "plug_html")
    private String plugHtml;

    @ApiModelProperty("插件获取的html文件解析出来的json文件")
    @Column(name = "resume_detail")
    private String resumeDetail;

    @ApiModelProperty("解析是否成功")
    @Column(name = "status")
    private String status;

    @ApiModelProperty("跟踪id")
    @Column(name = "trace_id")
    private String traceId;

    @ApiModelProperty("平台方简历的URL地址")
    @Column(name = "platform_resume_url")
    private String platformResumeUrl;

}
