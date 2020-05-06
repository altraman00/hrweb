package com.mdl.zhaopin.enums;

import lombok.Getter;
import lombok.Setter;

public enum DegreeEnum implements CodeEnum {
    Default(50001, "未知"),
    ZhongZhuan(50002, "中专"),
    GaoZhong(50003, "高中"),
    DaZhuan(50004, "大专"),
    BenKe(50005, "本科"),
    ShuoShi(50006, "硕士"),
    BoShi(50007, "博士"),
    GaoZhongYiXia(50008, "高中以下");

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String name;

    DegreeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DegreeEnum getByCode(Integer code) {
        for (DegreeEnum degree : DegreeEnum.values()) {
            if (degree.getCode().equals(code)) {
                return degree;
            }
        }
        return Default;
    }

    public static DegreeEnum getByName(String name) {
        for (DegreeEnum degree : DegreeEnum.values()) {
            if (degree.getName().equals(name)) {
                return degree;
            }
        }
        return Default;
    }

}
