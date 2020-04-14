package com.mdl.zhaopin.handler.platform.resume;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:36
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public abstract class AbstractResume implements Resume {

    /** 姓名 */
    private String name;

    /** 年龄 */
    private String age;

    /** 性别 */
    private String sex;

    /** 学历 */
    private String education;

    /** 毕业院校 */
    private String school;

    /** 工作时间 */
    private String workDuration;

    /** 联系电话 */
    private String phone;

    /** 邮箱 **/
    private String mail;


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(String workDuration) {
        this.workDuration = workDuration;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
