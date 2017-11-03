package com.sunlands.hr.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @ClassName: RegexUtils 
* @Description: TODO 正则表达式工具类，验证数据是否符合规范
* @author xiekun
* @date 2017年11月1日 上午10:40:21 
*
 */
public class RegexUtils {

    public static void main(String[] agrs) {
//        System.out.println(find(" page 36 ", "^\\s[pP]age[\\s0-9]*$"));
    	
    	if(isInteger("a")) {
    		System.out.println("yes");
    	}else {
    		System.out.println("no");
    	}
    }

    /**
     * 
    * @Title: find 
    * @Description: TODO 判断字符串是否符合正则表达式
    * @param @param str
    * @param @param regex
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public static boolean find(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 
    * @Title: isEmail 
    * @Description: TODO 判断输入的字符串是否符合Email格式.
    * @param @param email
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public static boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(email).matches();
    }

    /**
     * 
    * @Title: isChinese 
    * @Description: TODO判断输入的字符串是否为纯汉字
    * @param @param value
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public static boolean isChinese(String value) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(value).matches();
    }

    /**
     * 
    * @Title: isDouble 
    * @Description: TODO 判断是否为浮点数，包括double和float
    * @param @param value
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public static boolean isDouble(String value) {
        Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
        return pattern.matcher(value).matches();
    }

    /**
     * 
    * @Title: isInteger 
    * @Description: TODO 判断是否为整数
    * @param @param value
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public static boolean isInteger(String value) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        return pattern.matcher(value).matches();
    }
    
    /**
     *  
    * @Title: getValue 
    * @Description: TODO 得一正则表达对应的内容 
    * @param @param con
    * @param @param reg
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    private String getValue(String con, String reg){  
        Pattern p = Pattern.compile(reg);  
        Matcher m = p.matcher(con);  
        String res = "";  
        while (m.find()) {  
            res = m.group(1);  
        }  
        return res;  
    }  
}
