package com.mdl.zhaopin.handler.platform.resume;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.resume
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:43
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class LagouResume extends AbstractResume implements Resume {

    private static final long serialVersionUID = 2093235400790350336L;

    protected String name;

    protected String job;

    protected String company;

    protected String sex = "男";

    protected String education;

    protected String workDuration;

    protected String city;

    protected String phone;

    protected String mail;

    protected String school;

    protected String age;


    @Override
    public String getSource() {
        return "拉勾";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getJob() {
        return job;
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getEducation() {
        return education;
    }

    @Override
    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String getWorkDuration() {
        return workDuration;
    }

    @Override
    public void setWorkDuration(String workDuration) {
        this.workDuration = workDuration;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String getSchool() {
        return school;
    }

    @Override
    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public void setAge(String age) {
        this.age = age;
    }
}
