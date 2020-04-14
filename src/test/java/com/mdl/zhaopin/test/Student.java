package com.mdl.zhaopin.test;

import lombok.Data;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.test
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 13:47
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Data
public class Student {

    private Integer id;
    private String name;
    private String sex;
    private Double grade;

    public Student() {
    }

    public Student(Integer id, String name, String sex, Double grade) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.grade = grade;
    }
}
