package com.mdl.zhaopin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * @Project : zhaopin
 * @Package Name : com.sunlands.zhaopin.model.entity.resume
 * @Description : TODO
 * @Author : admin
 * @Create Date : 2019年06月18日 15:43
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Data
@MappedSuperclass
public abstract class BaseEntity {

    /** uuid **/
    @Id
    @GenericGenerator(name="uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

//    //自增的id
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private int id;


    @ApiModelProperty(value = "是否删除", name = "deleted")
    @Column(name = "deleted")
    private boolean deleted = false;

    @ApiModelProperty(value = "创建时间", name = "createDt")
    @Column(name = "create_dt")
    private Timestamp createDt = new Timestamp(System.currentTimeMillis());

    @ApiModelProperty(value = "最后修改时间", name = "modifyDt")
    @Column(name = "modify_dt", updatable = false)
    private Timestamp modifyDt = new Timestamp(System.currentTimeMillis());

    @ApiModelProperty(value = "版本", name = "version")
    @Column(name = "version")
    private String version = "0";

    @ApiModelProperty(value = "排序号", name = "sortNo")
    @Column(name = "sort_no")
    protected Integer sortNo = 1;

}
