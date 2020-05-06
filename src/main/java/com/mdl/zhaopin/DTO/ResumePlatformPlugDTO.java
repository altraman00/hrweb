package com.mdl.zhaopin.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Project : resume-parse
 * @Package Name : com.sunlands.zhaopin.DTO
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月23日 10:31
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Data
public class ResumePlatformPlugDTO {


    /**插件方式**/
    public static String PARSE_TYPE_PLUG = "plug";
    /**文件上传方式**/
    public static String PARSE_TYPE_FILE = "file";

    @ApiModelProperty("平台")
    @NotEmpty
    private String platform;

    @ApiModelProperty("简历html")
    @NotEmpty
    private String resumeHtml;

    @ApiModelProperty("平台方简历链接地址")
    @NotEmpty
    private String resumeUrl;

}
