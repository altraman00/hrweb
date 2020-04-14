package com.mdl.zhaopin.handler.platform.resume;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.resume
 * @Description : 前程无忧简历
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:41
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class Job51Resume extends AbstractResume implements Resume{

    private static final long serialVersionUID = 7062333547931765074L;

    public static final Set<String> KEY_WORDS = new HashSet<String>();

    public static final String KEY_NAME = "NAME";
    public static final String KEY_WORK_DURATION = "WORK_DURATION";
    public static final String KEY_SEX = "SEX";
    public static final String KEY_AGE = "AGE";
    public static final String KEY_JOB = "JOB";
    public static final String KEY_BIRTHDAY = "BIRTHDAY";

    public static final String KEY_CITY = "居住地：";
    //public static final String KEY_Hukou = "户　口：";
    public static final String KEY_ADDRESS = "地　址：";
    public static final String KEY_PHONE = "电　话：";
    public static final String KEY_MAIL = "E-mail：";
    public static final String KEY_COMPANY = "公　司：";
    //public static final String KEY_HANGYE = "行　业：";
    //public static final String KEY_JOB_NOW = "职　位：";
    public static final String KEY_EDUCATION = "学　历：";
    //public static final String KEY_ZHUANYE = "专　业：";
    public static final String KEY_SCHOOL = "学　校：";

    static {
        KEY_WORDS.add(KEY_CITY);
        KEY_WORDS.add(KEY_ADDRESS);
        KEY_WORDS.add(KEY_PHONE);
        KEY_WORDS.add(KEY_MAIL);
        KEY_WORDS.add(KEY_COMPANY);
        //KEY_WORDS.add(KEY_JOB_NOW);
        KEY_WORDS.add(KEY_EDUCATION);
        //KEY_WORDS.add(KEY_ZHUANYE);
        KEY_WORDS.add(KEY_SCHOOL);
    }

    protected Map<String, String> cache = new HashMap<String, String>();

    @Override
    public String getSource() {
        return "前程无忧";
    }

    @Override
    public String getName() {
        return cache.get(KEY_NAME);
    }

    @Override
    public String getJob() {
        return cache.get(KEY_JOB);
    }

    @Override
    public String getCompany() {
        return cache.get(KEY_COMPANY);
    }

    @Override
    public String getSex() {
        return cache.get(KEY_SEX);
    }

    @Override
    public String getEducation() {
        return cache.get(KEY_EDUCATION);
    }

    @Override
    public String getWorkDuration() {
        return cache.get(KEY_WORK_DURATION);
    }

    @Override
    public String getCity() {
        return cache.get(KEY_CITY);
    }

    @Override
    public String getPhone() {
        return cache.get(KEY_PHONE);
    }

    @Override
    public String getMail() {
        return cache.get(KEY_MAIL);
    }

    @Override
    public String getSchool() {
        return cache.get(KEY_SCHOOL);
    }

    @Override
    public String getAge() {
        return cache.get(KEY_AGE);
    }

    @Override
    public String getAddress() {
        return cache.get(KEY_ADDRESS);
    }

    @Override
    public String getBirthday() {
        return cache.get(KEY_BIRTHDAY);
    }

    public void set(String key, String value) {
        cache.put(key, value);
    }



}
