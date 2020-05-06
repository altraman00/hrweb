package com.mdl.zhaopin.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PlatformEnums implements CodeEnum {

    Default(10000, "全部"),

    ZHI_LIAN(10001, "智联招聘"),
    SAME_CITY(10002, "58同城"),
    LIE_PIN(10003, "猎聘网"),
    BOSS(10004, "BOSS直聘"),
    JOBS(10005, "前程无忧"),
    LAGOU(10006, "拉勾网"),
    TALENT_POOL(10007, "企业人才库"),
    WAIT_REPLY(10008, "内推"),
    ZIJIAN(10009, "自荐"),
    ZIWOWAJUE(10010, "自我挖掘"),
    LIETOU(10011, "猎头推荐"),
    SCHOOL(10012,"校招"),//10012
    WEIPIN(10013,"微聘")

    ;

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String name;

    PlatformEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public static List<Map<String, Object>> getIdAndNameList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (PlatformEnums r : PlatformEnums.values()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", r.getCode());
            map.put("name", r.getName());
            list.add(map);
        }
        return list;
    }
    public static PlatformEnums getByCode(Integer code){
        for (PlatformEnums resumeSourceEnums: PlatformEnums.values()){
            if (resumeSourceEnums.getCode().equals(code)){
                return resumeSourceEnums;
            }
        }
        return null;
    }
    public static PlatformEnums getByName(String name){
        for (PlatformEnums resumeSourceEnums: PlatformEnums.values()){
            if (resumeSourceEnums.getName().equals(name)){
                return resumeSourceEnums;
            }
        }
        return null;
    }
}
