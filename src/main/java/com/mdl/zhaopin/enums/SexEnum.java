package com.mdl.zhaopin.enums;

import lombok.Getter;
import lombok.Setter;

public enum SexEnum implements CodeEnum {
    Default(70001, "未知默认为男"),
    MAN(70001, "男"),
    WOMAN(70002, "女");

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String name;

    SexEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SexEnum getByCode(Integer code) {
        for (SexEnum degree : SexEnum.values()) {
            if (degree.getCode().equals(code)) {
                return degree;
            }
        }
        return Default;
    }

    public static SexEnum getByName(String name) {
        for (SexEnum degree : SexEnum.values()) {
            if (degree.getName().equals(name)) {
                return degree;
            }
        }
        return Default;
    }

}
