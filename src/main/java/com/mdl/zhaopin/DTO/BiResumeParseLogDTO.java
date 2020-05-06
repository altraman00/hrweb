package com.mdl.zhaopin.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @Project : resume-parse
 * @Package Name : com.sunlands.zhaopin.DTO
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月24日 17:34
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BiResumeParseLogDTO extends BaseDTO implements Serializable {

    public static String STATUS_INIT = "init";
    public static String STATUS_SUCCESS = "success";
    public static String STATUS_FAILED = "failed";

    @ApiModelProperty("简历id")
    @Column(name = "resume_id")
    private String resumeId;

    @ApiModelProperty("平台")
    private String platform;

    @ApiModelProperty("插件获取的html文件")
    private String plugHtml;

    @ApiModelProperty("插件获取的html文件解析出来的json文件")
    private String resumeDetail;

    @ApiModelProperty("解析是否成功")
    private String parseSuccess;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("平台方简历的URL地址")
    private String platformResumeUrl;

    @ApiModelProperty("跟踪id")
    private String traceId;

    public BiResumeParseLogDTO(String traceId,String platform, String plugHtml,String platformResumeUrl,String status) {
        this.traceId = traceId;
        this.platform = platform;
        this.plugHtml = plugHtml;
        this.platformResumeUrl = platformResumeUrl;
        this.status = status;
    }


    public BiResumeParseLogDTO(String traceId, String status) {
        this.traceId = traceId;
        this.status = status;
    }

}
