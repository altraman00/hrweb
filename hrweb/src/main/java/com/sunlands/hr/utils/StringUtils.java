package com.sunlands.hr.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 
 * @author lianghao
 */
public class StringUtils {
	
	private static final Logger logger = Logger.getLogger(StringUtils.class);

	private static final String EMAIL_REGEXP = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	
	private static final String PHONE_REGEXP = "^1(3|4|5|7|8)\\d{9}$";
	
	private static final String PASSWORD_REGEXP="^\\w{6,17}$";
	
	private static final String USERNAME_REGEXP = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,}$";
	
	private static final String IS_NUMBER = "^[0-9]*$";
	
	
	public static String convertToString(Object o){
		if(o==null||String.valueOf(o).trim().equals("")){
			return null;
		}
		return String.valueOf(o);
	}
	
	public static boolean isEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	
	public static String join(List<String> strs,String symbol){
		if(strs.size() == 0){
			return "";
		}
		if(strs.size() == 1){
			return strs.get(0);
		}
		StringBuffer sb = new StringBuffer();
		if(isEmpty(symbol)){
			symbol = "";
		}
		for(String str:strs){
			sb.append(str).append(symbol);
		}
		return sb.deleteCharAt(sb.length()-1).toString();
	}
	
	public static String join(String[] strs,String symbol){
		if(strs.length == 0){
			return "";
		}
		if(strs.length == 1){
			return strs[0];
		}
		StringBuffer sb = new StringBuffer();
		if(isEmpty(symbol)){
			symbol = "";
		}
		for(String str:strs){
			sb.append(str).append(symbol);
		}
		return sb.deleteCharAt(sb.length()-1).toString();
	}
	public static String join(String[] strs){
		return join(strs,null);
	}
	
	
	/**
	 * 对 标准格式的 dto 中的指定 字段判断是否为空
	 * 
	 * @param obj
	 * @param strs
	 *            字段民
	 * @return
	 */
	public static boolean checkDtoIsNotNullByFieldStr(Object obj, String... strs) {
		try {
			Class<?> clazz = obj.getClass();
			for (int i = 0; i < strs.length; i++) {
				Method method = clazz.getMethod(getMethodStrByFieldStr("get", strs[i]), null);
				// 这里不能用String.valueOf() ，这个方法会把 null 变成 "null",我他妈也是醉了
				String value = (String) method.invoke(obj);
				if (isEmpty(value)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	private static String getMethodStrByFieldStr(String firstChara, String field) {
		return new StringBuffer(firstChara).append(field.substring(0, 1).toUpperCase()).append(field.substring(1))
				.toString();
	}

	private static boolean validataByRegexp(String reg,String str){
		if (isEmpty(str)) {
			return false;
		}
		return Pattern.matches(reg,str);
	}
	
	public static boolean isEmail(String str) {
		return validataByRegexp(EMAIL_REGEXP,str);	
	}
	public static boolean isPhone(String str){
		return validataByRegexp(PHONE_REGEXP,str);
	}
	public static boolean checkPassword(String str){
		return validataByRegexp(PASSWORD_REGEXP,str);
	}
	public static boolean isUserName(String str) {
		return validataByRegexp(USERNAME_REGEXP,str);
	}
	
	public static boolean isNumber(String str){
		return validataByRegexp(IS_NUMBER,str);
	}
	
	/**
	 * 是否包含中文
	 */
	public static boolean containChinese(String str){
		if(isEmpty(str)){
			return false;
		}
		for(int i=0;i<str.length();i++){
			char a = str.charAt(i);
//			\u4e00-\u9fa5 网上搜到中文字符区间
			if(a >= '\u4e00' && a <= '\u9fa5'){
				return true;
			}
		}
		return false;
	}
	
	public static List<String> intersection(String[] arr0,String[] arr1){
		List<String> list = new ArrayList<>();
		for(String a0:arr0){
			for(String a1:arr1){
				if(a0.equals(a1)){
					list.add(a0);
				}
			}
		}
		return list;
	}
	
	public static List<String> intersection(String[] arr0,List<String> arr1){
		List<String> list = new ArrayList<>();
		for(String a0:arr0){
			for(String a1:arr1){
				if(a0.equals(a1)){
					list.add(a0);
				}
			}
		}
		return list;
	}
	
    
    /**
     * 一次性判断多个或单个对象为空。
     * @param objects
     * @author cai niao
     * @return 只要有一个元素为Blank，则返回true
     */
    public static boolean isBlank(Object...objects){
        Boolean result = false ;
        for (Object object : objects) {
            if(object == null || "".equals(object.toString().trim()) 
                    || "null".equals(object.toString().trim())
                    || "[null]".equals(object.toString().trim())
                    || "[]".equals(object.toString().trim())){
                result = true ; 
                break ; 
            }
        }
        return result ; 
    }
	

}
