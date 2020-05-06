package com.mdl.zhaopin.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public enum InWayEnums implements  CodeEnum{
    DEFAULT(190007, "所有类型",1),
    RESUME_DELIVERY(190001, "主动投递",1),
    SEARCH_DOWNLOAD(190002, "搜索下载",1),
    RESUME_UPLOAD(190003, "简历上传",0),
    RESUME_HISTORY(190005, "第三方下载的历史简历",0),
    TALENT(190006, "人才库调转",1),
    OTHER(190004, "其他",1);

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Integer isUse;

    InWayEnums(Integer code, String name, Integer isUse) {
        this.code = code;
        this.name = name;
        this.isUse = isUse;
    }

    public static List<Map<String, Object>> getIdAndNameList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (InWayEnums r : InWayEnums.values()) {
            if (Objects.equals(r.isUse,1)){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("code", r.getCode());
                map.put("name", r.getName());
                list.add(map);
            }
        }
        return list;
    }
    public static InWayEnums getByCode(Integer code){
        if (Objects.isNull(code)){
            return  null;
        }
        for (InWayEnums reportTypeEnums: InWayEnums.values()){
            if (reportTypeEnums.getCode().equals(code)){
                return reportTypeEnums;
            }
        }
        return null;
    }
    public static InWayEnums getByName(String name){
        for (InWayEnums reportTypeEnums: InWayEnums.values()){
            if (reportTypeEnums.getName().equals(name)){
                return reportTypeEnums;
            }
        }
        return null;
    }
}
