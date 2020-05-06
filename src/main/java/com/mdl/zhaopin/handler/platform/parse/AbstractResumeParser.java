package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.enums.SexEnum;
import com.mdl.zhaopin.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:39
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public abstract class AbstractResumeParser {


    /**
     * 通过教育经历来判断年龄
     **/
    protected static Map<String, Integer> EDUCATIONS = new LinkedHashMap<String, Integer>();

    protected static String SALARY_TYPE_YEAR = "万元/年";
    protected static String SALARY_TYPE_MONTH = "元/月";
    protected static String SALARY_TYPE_DAY = "元/天";

    static {
        // 使用LinkedHashMap 保持顺序
        EDUCATIONS.put("博士", new Integer(30));
        EDUCATIONS.put("硕士", new Integer(26));
        EDUCATIONS.put("本科", new Integer(23));
        EDUCATIONS.put("大专", new Integer(22));
        EDUCATIONS.put("高中", new Integer(19));
    }


    /**
     * 判断婚姻状况
     * @param text
     * @return
     */
    protected String isMarry(String text){
        if(StringUtils.isEmpty(text)){
            return null;
        }
        if(text.contains("已婚")){
            return "已婚";
        }else if(text.contains("已育")){
            return "已育";
        }else{
            return "未婚";
        }
    }


    /**
     * 判断性别
     * @param text
     * @return
     */
    protected Integer parseSex(String text){
        if(StringUtils.isEmpty(text)){
            return SexEnum.MAN.getCode();
        }
        if(text.contains(SexEnum.MAN.getName())){
            return SexEnum.MAN.getCode();
        }else if(text.contains(SexEnum.WOMAN.getName())){
            return SexEnum.WOMAN.getCode();
        }else{
            return SexEnum.MAN.getCode();
        }
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
     *
     * @param sParam
     * @return
     */
    protected String getTelnum(String sParam) {
        if (sParam == null || sParam.length() <= 0) {
            return null;
        }
        String regex = "(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}";
        return getPatternData(sParam, regex);
    }


    /**
     * 从文本中提取邮箱
     *
     * @param sParam
     * @return
     */
    protected String getEmailnum(String sParam) {
        if (sParam == null || sParam.length() <= 0) {
            return null;
        }
        String regex = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        return getPatternData(sParam, regex);
    }

    protected String getPatternData(String sParam, String ss) {
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


    /**
     * 获取文本中连续的数值list
     *
     * @param word
     * @return
     */
    protected static List<Integer> getNumArr(String word) {
        List<Integer> numList = new ArrayList<>();
        if(StringUtils.isEmpty(word)){
            return numList;
        }
        String regex = "(\\d{1,})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(word);
        while (matcher.find()) {
            String group = matcher.group(1);
            if (isNumeric(group)) {
                Integer num = Integer.valueOf(group);
                numList.add(num);
            }
        }
        return numList;
    }


    /**
     * 获取每个月的薪资
     * @param word
     * @return
     */
    protected static List<Integer> getSalaryArr(String word){
        List<Integer> salaryArr = getNumArr(word);
        if(word.contains(SALARY_TYPE_YEAR)){
            salaryArr = salaryArr.stream().map(t -> t * 10000 / 12).collect(Collectors.toList());
        }else if(word.contains(SALARY_TYPE_DAY)){
            salaryArr = salaryArr.stream().map(t -> t * 22).collect(Collectors.toList());
        }
        return salaryArr;
    }


    /**
     * 判断是不是数字
     *
     * @param str
     * @return
     */
    protected static boolean isNumeric(String str) {
        //Pattern pattern = Pattern.compile("^-?[0-9]+"); //这个也行
        String regex = "^-?\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     * 截取非数字
     *
     * @param content
     * @return
     */
    protected static String splitNotNumber(String content) {
        return content.replaceAll("\\d+", "");
    }


    /**
     * 获取匹配的所有字符串
     *
     * @param content
     * @param sPattern
     * @return
     */
    public static List<String> getMatcherStrs(String content, String sPattern) {
        Pattern p = Pattern.compile(sPattern);
        Matcher m = p.matcher(content);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }

    /**
     * 获取所有匹配的日期
     * @param content
     * @param splitStr  日期的符号 如：2016-06-06的 splitStr="-";2016.06.06的 splitStr=".";
     * @return
     */
    public static List<String> getDates(String content,String splitStr){
        List<String> list1 =  getMatcherStrs(content,"\\d{4}\\"+splitStr+"\\d{1,2}\\"+splitStr+"\\d{1,2}");
        List<String> list2 =  getMatcherStrs(content,"\\d{4}\\"+splitStr+"\\d{1,2}");
        return list1!=null && !list1.isEmpty() ? list1 : list2;
    }


    public static void main(String[] args) {
        String salaryStr = "手机：10001-15000元/月";
        List<Integer> numbers = getNumArr(salaryStr);
        String numberStr = splitNotNumber(salaryStr);
        System.out.println(JSONUtils.objectToJson(numbers));
        System.out.println(numberStr);


//        String str="&nbsp;&nbsp;发布时间：2016-06-06 15:51:03 ";
//        List<String> dates = getDates(str, "-");

//        String str="&nbsp;&nbsp;发布时间：2016.06 15:51:03 - 2016.08 15:51:03 ";
//        List<String> dates = getDates(str, ".");
//        System.out.println(JSONUtils.objectToJson(dates));



    }


}
