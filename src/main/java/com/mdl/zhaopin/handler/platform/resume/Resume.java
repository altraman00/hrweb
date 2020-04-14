package com.mdl.zhaopin.handler.platform.resume;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:37
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface Resume {

    /** 姓名 */
    String getName();

    /** 年龄 */
    String getAge();

    /** 性别 */
    String getSex();

    /** 学历 */
    String getEducation();

    /** 毕业院校 */
    String getSchool();

    /** 工作时间 */
    String getWorkDuration();

    /** 联系电话 */
    String getPhone();

    /** 电子邮箱 */
    String getMail();

    /** 简历来源渠道 */
    String getSource();

    /** 生日 */
    String getBirthday();

    /** 职位 */
    String getJob();

    /** 所在公司 */
    String getCompany();

    /** 所在城市 */
    String getCity();

    /** 现住址 */
    String getAddress();



}
