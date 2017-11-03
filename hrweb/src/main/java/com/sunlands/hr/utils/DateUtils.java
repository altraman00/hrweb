package com.sunlands.hr.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	

	public static Date getDate() {
		return new Date();
	}

	public static long getTimestamp() {
		return getDate().getTime();
	}

	public static String getSysDateStr(String template) {
		return new SimpleDateFormat(template).format(getDate());
	}

	/**
	 * 拿到系统时间搓
	 */
	public static String getSystemDateToString() {
		return getSysDateStr(yyyy_MM_dd_HH_mm_ss);
	}

	/**
	 * 解析时间搓函数
	 */
	public static Date parseStringToDate(String format, String dateStr) {
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(String.format("解析字符串错误,字符串 :%s ,/r/n pattern:%s", dateStr, format));
		}
	}

	public static Long parseStringToDate(String dateStr) {
		return parseStringToDate(yyyy_MM_dd_HH_mm_ss, dateStr).getTime();
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds
	 *            精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss格式字符串转date  
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTiemStringToDate(String time,String format) throws ParseException {
		SimpleDateFormat fm =  new SimpleDateFormat(format);
		return fm.parse(time);
	}
	
	
	public static String parseTiemStringToDateString(String time,String format) throws ParseException {
		SimpleDateFormat fm =  new SimpleDateFormat(format);
		return fm.format(fm.parse(time));
	}
	
	/**
	 * 将时间戳转为Date
	 * @param format
	 * @param timestamp
	 * @return
	 */
	public static Date timeStamp2Date(Long seconds, String format){
		SimpleDateFormat fm = new SimpleDateFormat(format);
		Long time = new Long(seconds * 1000);
		String d = fm.format(time);
		Date date = null;
		try {
			date = fm.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期格式字符串转换成时间戳
	 * 
	 * @param date
	 *            字符串日期
	 * @param format
	 *            如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String date2TimeStamp(String date_str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(date_str).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * 
	 * @return
	 */
	public static String timeStamp() {
		long time = System.currentTimeMillis();
		String t = String.valueOf(time / 1000);
		return t;
	}

}
