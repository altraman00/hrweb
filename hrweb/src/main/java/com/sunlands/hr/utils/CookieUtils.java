package com.sunlands.hr.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtils {

	/**
	 * 获得cookie 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request,String cookieName){
		Cookie [] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies) {
				String str = cookie.getName();
				if(str.equals(cookieName)){
					return cookie;
				}
			}
		}
		return null;
	}
	
	public static String getCookieValue(HttpServletRequest request,String key){
	    Cookie [] cookies = request.getCookies();
	    if(cookies != null && cookies.length > 0){
	        for (Cookie cookie : cookies) {
                String str = cookie.getName();
                if(str.equals(key)){
                    return cookie.getValue();
                }
            }
	    }
	    return null;
	}
	
	/**
	 * 获得cookie 中的值
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getValue(HttpServletRequest request,String cookieName){
		Cookie cookie = getCookie(request,cookieName);
		if (cookie != null) {
			String[] tmpArray = cookie.getValue().split(":");
			if (tmpArray != null && tmpArray.length == 3) {
				return tmpArray[0];
			}
		}
		return null;
	}
	
	/**
	 * 删除指定的cookie
	 * @param response
	 * @param key
	 * @param path
	 */
	public static void clear(HttpServletResponse response,String cookieName){
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 保存cookie
	 * @param response
	 * @param key
	 * @param value
	 * @param second
	 * @param path
	 * @throws UnsupportedEncodingException 
	 */
	public static void setCookie(HttpServletResponse response,String cookieName,String value,boolean httpOnly) throws UnsupportedEncodingException{
		value = URLDecoder.decode(value, "UTF-8");
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookie.setPath("/");
		cookie.setHttpOnly(httpOnly);
		response.addCookie(cookie);
	}
}
