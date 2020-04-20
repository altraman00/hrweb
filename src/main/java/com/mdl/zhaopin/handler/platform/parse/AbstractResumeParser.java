package com.mdl.zhaopin.handler.platform.parse;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:39
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public abstract class AbstractResumeParser  {


    /**
     * 通过教育经历来判断年龄
     **/
    protected static Map<String, Integer> EDUCATIONS = new LinkedHashMap<String, Integer>();

    static {
        // 使用LinkedHashMap 保持顺序
        EDUCATIONS.put("博士", new Integer(30));
        EDUCATIONS.put("硕士", new Integer(26));
        EDUCATIONS.put("本科", new Integer(23));
        EDUCATIONS.put("大专", new Integer(22));
        EDUCATIONS.put("高中", new Integer(19));
    }

    /**
     * 从 text 中截取 start和end 字符之间的字符串。
     *
     * @param text
     * @param start
     * @param end
     * @return
     */
    protected String intercept(String text, String start, String end) {
        int head = start == null ? -1 : text.indexOf(start);
        int tail = end == null ? -1 : text.indexOf(end);
        int begin = head == -1 ? 0 : head + start.length();
        if (tail == -1) {
            return text.substring(begin).trim();
        } else {
            return text.substring(begin, tail).trim();
        }
    }

    protected String trim(String text, String... strs) {
        String result = text.trim();
        for (String str : strs) {
            if (result.startsWith(str)) {
                result = result.substring(str.length());
            } else if (result.endsWith(str)) {
                result = result.substring(0, result.length() - str.length());
            }
        }
        return result.trim();
    }


    /**
     * 从文本中提取手机号
     * @param sParam
     * @return
     */
    protected String getTelnum(String sParam) {
        if (sParam == null || sParam.length() <= 0) {
            return null;
        }
        String ss = "(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}";
        return getPatternData(sParam, ss);
    }


    /**
     * 从文本中提取邮箱
     * @param sParam
     * @return
     */
    protected String getEmailnum(String sParam) {
        if (sParam == null || sParam.length() <= 0) {
            return null;
        }
        String ss = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        return getPatternData(sParam, ss);
    }

    private String getPatternData(String sParam, String ss) {
        Pattern pattern = Pattern.compile(ss);
        Matcher matcher = pattern.matcher(sParam);
        StringBuffer bf = new StringBuffer();
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }


}
