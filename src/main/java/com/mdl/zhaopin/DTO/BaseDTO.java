package com.mdl.zhaopin.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Project : zhaopin
 * @Package Name : com.mdl.zhaopin.model.entity.resume
 * @Description : TODO
 * @Author : admin
 * @Create Date : 2019年06月18日 15:43
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Data
//@MappedSuperclass
public abstract class BaseDTO {

    @ApiModelProperty(value = "uuid", name = "id")
    private String id;

    @ApiModelProperty(value = "是否删除", name = "deleted")
    @JsonIgnore
    private boolean deleted = false;

    @ApiModelProperty(value = "创建时间", name = "createDt")
    @JsonIgnore
    private Timestamp createDt = new Timestamp(System.currentTimeMillis());

    @ApiModelProperty(value = "最后修改时间", name = "modifyDt")
    @JsonIgnore
    private Timestamp modifyDt = new Timestamp(System.currentTimeMillis());

    @ApiModelProperty(value = "版本", name = "version")
    @JsonIgnore
    private String version = "0";

    @ApiModelProperty(value = "排序号", name = "sortNo")
    @JsonIgnore
    protected Integer sortNo = 1;

}
