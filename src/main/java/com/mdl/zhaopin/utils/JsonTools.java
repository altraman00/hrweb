package com.mdl.zhaopin.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.utils
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月13日 14:25
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class JsonTools {

    private static final Logger log = LoggerFactory.getLogger(JsonTools.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 对象字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);

        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        // 统一日期格式yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 忽略在json字符串中存在,但是在java对象中不存在对应属性的情况
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * object转Json字符串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Parse object to String error", e);
            return null;
        }
    }

    /**
     * Object转json字符串并格式化美化
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Parse object to String error", e);
            return null;
        }
    }

    /**
     * string转object
     *
     * @param str   json字符串
     * @param clazz 被转对象class
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.error("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * string转object
     *
     * @param str           json字符串
     * @param typeReference 被转对象引用类型
     * @param <T>
     * @return
     */
    public static <T> T string2ObjRef(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            log.error("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * string转collection 用于转为集合对象
     *
     * @param str             json字符串
     * @param collectionClass 被转集合class
     * @param elementClasses  被转集合中对象类型class
     * @param <T>
     * @return
     */
    public static <T> T string2Collection(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.error("Parse String to Collection error", e);
            return null;
        }
    }

    /**
     * 根据JSONArray String获取到List
     *
     * @param <T>
     * @param <T>
     * @param jArrayStr
     * @return
     */
    public static <T> List<T> getListByJSONArray(Class<T> class1, String jArrayStr) {
        List<T> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(jArrayStr);
        if (jsonArray == null || jsonArray.isEmpty()) {
            return list;//nerver return null
        }
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            T t = JSONObject.toJavaObject(jsonObject, class1);
            list.add(t);
        }
        return list;
    }

    /**
     * 根据List获取到对应的JSONArray
     *
     * @param list
     * @return
     */
    public static JSONArray getJSONArrayByList(List<?> list) {
        JSONArray jsonArray = new JSONArray();
        if (list == null || list.isEmpty()) {
            //nerver return null
            return jsonArray;
        }
        for (Object object : list) {
            jsonArray.add(object);
        }
        return jsonArray;
    }

}
