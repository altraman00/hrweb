package com.mdl.zhaopin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.entity
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月21日 17:23
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Entity
@Data
@Table(name = "bi_resume")
public class BiResumeEntity extends BaseEntity{

    @ApiModelProperty("名字")
    @Column(name = "job_seeker_name")
    private String jobSeekerName;

    @ApiModelProperty("学历code")
    @Column(name = "degree")
    private String degree;

    @ApiModelProperty("求职状态")
    @Column(name = "current_state")
    private String currentState;

    @ApiModelProperty("简历来源方式：190001：主动投递，190002：搜索后存入，190003：从简历管理上传后存入，190004：从人才库上传后存入")
    @Column(name = "in_way")
    private String inWay;

    @ApiModelProperty("手机号码")
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty("简历来源 10001：智联，10002:58同城，10003：猎聘，10004：Boss直聘，10005:前程无忧，10006：拉钩")
    @Column(name = "plat_id")
    private String platId;

    @ApiModelProperty("状态")
    @Column(name = "state")
    private Integer state;

    @ApiModelProperty("邮箱")
    @Column(name = "email")
    private String email;

    @ApiModelProperty("性别，70001表示男，70002表示女")
    @Column(name = "age")
    private Integer age;

    @ApiModelProperty("出生年月日")
    @Column(name = "birthday")
    private String birthday;

    @ApiModelProperty("专业")
    @Column(name = "sex")
    private String sex;

    @ApiModelProperty("工作年限最高")
    @Column(name = "work_length_max")
    private Integer workLengthMax;

    @ApiModelProperty("工作年限最低")
    @Column(name = "work_length_min")
    private Integer workLengthMin;

    @ApiModelProperty("工作年限，有的简历可能存的是1-3年，有的可能直接存的3年")
    @Column(name = "work_length")
    private String workLength;

    @ApiModelProperty("已婚，未婚")
    @Column(name = "is_marry")
    private String isMarry;

    @ApiModelProperty("应聘职位")
    @Column(name = "job_title")
    private String jobTitle;

    @ApiModelProperty("住址")
    @Column(name = "reside_address")
    private String resideAddress;

    @ApiModelProperty("期望最高薪资")
    @Column(name = "expect_salary_max")
    private Integer expectSalaryMax;

    @ApiModelProperty("期望最低薪资")
    @Column(name = "expect_salary_min")
    private Integer expectSalaryMin;

    @ApiModelProperty("期望工作地点")
    @Column(name = "expect_place")
    private String expectPlace;

    @ApiModelProperty("期望从事职业")
    @Column(name = "expect_job")
    private String expectJob;

    @ApiModelProperty("期待行业 互联网/电子商务、计算机软件、其他")
    @Column(name = "expect_industry")
    private String expectIndustry;

    @ApiModelProperty("头像路径")
    @Column(name = "photo_path")
    private String photoPath;

    @ApiModelProperty("20001全职，20002兼职，20003实习")
    @Column(name = "expect_job_property")
    private String expectJobProperty;

    @ApiModelProperty("政治面貌 团员，党员等")
    @Column(name = "political_status")
    private String politicalStatus;

    @ApiModelProperty("毕业院校")
    @Column(name = "university")
    private String university;

    @ApiModelProperty("专业")
    @Column(name = "major")
    private String major;

    @ApiModelProperty("民族")
    @Column(name = "nation")
    private String nation;

    @ApiModelProperty("籍贯")
    @Column(name = "bitrh_place")
    private String bitrhPlace;

    @ApiModelProperty("证件类型")
    @Column(name = "certificate_type")
    private String certificateType;

    @ApiModelProperty("证件号")
    @Column(name = "certificate_num")
    private String certificateNum;

    @ApiModelProperty("微信号")
    @Column(name = "wechat_num")
    private String wechatNum;

    @ApiModelProperty("健康状况")
    @Column(name = "health")
    private String health;

    @ApiModelProperty("兴趣爱好")
    @Column(name = "hobbies")
    private String hobbies;

    @ApiModelProperty("居住状况")
    @Column(name = "housing_status")
    private String housingStatus;

    @ApiModelProperty("居住所在区")
    @Column(name = "house_area")
    private String houseArea;

    @ApiModelProperty("目前住址")
    @Column(name = "house_address")
    private String houseAddress;


    /**以下为新添加字段**/
    @ApiModelProperty("户口所在地")
    @Column(name = "domicile_place")
    private String domicilePlace;

    @ApiModelProperty("自我评价")
    @Column(name = "self_evaluation")
    private String selfEvaluation;

    @ApiModelProperty("跟踪id")
    @Column(name = "trace_id")
    private String traceId;

//    @ApiModelProperty("插件获取的html文件")
//    @Column(name = "plug_html")
//    private String plugHtml;
//
//    @ApiModelProperty("插件获取的html文件解析出来的json文件")
//    @Column(name = "resume_detail")
//    private String resumeDetail;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiResumeEntity that = (BiResumeEntity) o;
        return Objects.equals(jobSeekerName, that.jobSeekerName) &&
                Objects.equals(degree, that.degree) &&
                Objects.equals(currentState, that.currentState) &&
                Objects.equals(inWay, that.inWay) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(platId, that.platId) &&
                Objects.equals(state, that.state) &&
                Objects.equals(age, that.age) &&
                Objects.equals(email, that.email) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(workLengthMax, that.workLengthMax) &&
                Objects.equals(workLengthMin, that.workLengthMin) &&
                Objects.equals(workLength, that.workLength) &&
                Objects.equals(isMarry, that.isMarry) &&
                Objects.equals(jobTitle, that.jobTitle) &&
                Objects.equals(resideAddress, that.resideAddress) &&
                Objects.equals(expectSalaryMax, that.expectSalaryMax) &&
                Objects.equals(expectSalaryMin, that.expectSalaryMin) &&
                Objects.equals(expectPlace, that.expectPlace) &&
                Objects.equals(expectJob, that.expectJob) &&
                Objects.equals(expectIndustry, that.expectIndustry) &&
                Objects.equals(photoPath, that.photoPath) &&
                Objects.equals(expectJobProperty, that.expectJobProperty) &&
                Objects.equals(politicalStatus, that.politicalStatus) &&
                Objects.equals(university, that.university) &&
                Objects.equals(major, that.major) &&
                Objects.equals(nation, that.nation) &&
                Objects.equals(bitrhPlace, that.bitrhPlace) &&
                Objects.equals(certificateType, that.certificateType) &&
                Objects.equals(certificateNum, that.certificateNum) &&
                Objects.equals(wechatNum, that.wechatNum) &&
                Objects.equals(health, that.health) &&
                Objects.equals(hobbies, that.hobbies) &&
                Objects.equals(housingStatus, that.housingStatus) &&
                Objects.equals(houseArea, that.houseArea) &&
                Objects.equals(houseAddress, that.houseAddress) &&
                Objects.equals(traceId, that.traceId) &&
                Objects.equals(sortNo, that.sortNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobSeekerName, degree, currentState, inWay, phone, platId, state, age, email, sex, birthday, workLengthMax, workLengthMin, workLength, isMarry, jobTitle, resideAddress, expectSalaryMax, expectSalaryMin, expectPlace, expectJob, expectIndustry, photoPath, expectJobProperty, politicalStatus, university, major, nation, bitrhPlace, certificateType, certificateNum, wechatNum, health, hobbies, housingStatus, houseArea, houseAddress, traceId, sortNo);
    }
}
