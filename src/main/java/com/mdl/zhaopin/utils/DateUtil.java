package com.mdl.zhaopin.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of
	 * December in the year 2002
	 */
	public static final String ISO_DATE_FORMAT = "yyyyMMdd";

	/**
	 * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th day of
	 * December in the year 2002
	 */
	public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

	public static final String ISO_EXPANDED_DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

	public static final String EXPANDED_DATE_FORMAT_YMDHM = "yyyy/MM/dd HH:mm";

	public static final String EXPANDED_DATE_FORMAT = "yyyy/MM/dd";

	public static final String EXPANDED_DATE_FORMAT_WORD_1 = "yyyy年MM月dd日";

	public static final String EXPANDED_DATE_FORMAT_WORD_2 = "yyyy年MM月";

	/**
	 * yyyy-MM-dd hh:mm:ss
	 */
	public static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String DATETIME_PATTERN_HHMM = "HH:mm";

	public static String DATETIME_PATTERN_YYYYMM = "yyyyMM";

	public static String DATETIME_PATTERN_YYYY_POINT_MM = "yyyy.MM";

	public static String DATETIME_PATTERN_YYYY_PATTERN_MM = "yyyy/MM";

	public static String DATETIME_PATTERN_YYYY_MM = "yyyy-MM";

	public static String DATETIME_PATTERN_SPRIT = "yyyy/MM/dd HH:mm:ss";

	public static String DATE_PATTERN = "yyyyMMddHHmmss";

	/**
	 * 则个
	 */
	private static boolean LENIENT_DATE = false;

	private static DateFormat sdf = new SimpleDateFormat(ISO_EXPANDED_DATE_FORMAT);

	private static Random random = new Random();
	private static final int ID_BYTES = 10;

	public synchronized static String generateId() {
		StringBuffer result = new StringBuffer();
		result = result.append(System.currentTimeMillis());
		for (int i = 0; i < ID_BYTES; i++) {
			result = result.append(random.nextInt(10));
		}
		return result.toString();
	}

	protected static final float normalizedJulian(float JD) {

		float f = Math.round(JD + 0.5f) - 0.5f;

		return f;
	}

	/**
	 * Returns the Date from a julian. The Julian date will be converted to noon
	 * GMT, such that it matches the nearest half-integer (i.e., a julian date of
	 * 1.4 gets changed to 1.5, and 0.9 gets changed to 0.5.)
	 *
	 * @param JD
	 *            the Julian date
	 * @return the Gregorian date
	 */
	public static final Date toDate(float JD) {

		/*
		 * To convert a Julian Day Number to a Gregorian date, assume that it is for 0
		 * hours, Greenwich time (so that it ends in 0.5). Do the following
		 * calculations, again dropping the fractional part of all multiplicatons and
		 * divisions. Note: This method will not give dates accurately on the Gregorian
		 * Proleptic Calendar, i.e., the calendar you get by extending the Gregorian
		 * calendar backwards to years earlier than 1582. using the Gregorian leap year
		 * rules. In particular, the method fails if Y<400.
		 */
		float Z = (normalizedJulian(JD)) + 0.5f;
		float W = (int) ((Z - 1867216.25f) / 36524.25f);
		float X = (int) (W / 4f);
		float A = Z + 1 + W - X;
		float B = A + 1524;
		float C = (int) ((B - 122.1) / 365.25);
		float D = (int) (365.25f * C);
		float E = (int) ((B - D) / 30.6001);
		float F = (int) (30.6001f * E);
		int day = (int) (B - D - F);
		int month = (int) (E - 1);

		if (month > 12) {
			month = month - 12;
		}

		int year = (int) (C - 4715); // (if Month is January or February) or C-4716 (otherwise)

		if (month > 2) {
			year--;
		}

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1); // damn 0 offsets
		c.set(Calendar.DATE, day);

		return c.getTime();
	}

	/**
	 * Returns the days between two dates. Positive values indicate that the second
	 * date is after the first, and negative values indicate, well, the opposite.
	 * Relying on specific times is problematic.
	 *
	 * @param early
	 *            the "first date"
	 * @param late
	 *            the "second date"
	 * @return the days between the two dates
	 */
	public static final int daysBetween(Date early, Date late) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(early);
		c2.setTime(late);

		return daysBetween(c1, c2);
	}

	/**
	 * Returns the days between two dates. Positive values indicate that the second
	 * date is after the first, and negative values indicate, well, the opposite.
	 *
	 * @param early
	 * @param late
	 * @return the days between two dates.
	 */
	public static final int daysBetween(Calendar early, Calendar late) {

		return (int) (toJulian(late) - toJulian(early));
	}

	/**
	 * Return a Julian date based on the input parameter. This is based from
	 * calculations found at
	 * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day
	 * Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
	 *
	 * @param c
	 *            a calendar instance
	 * @return the julian day number
	 */
	public static final float toJulian(Calendar c) {

		int Y = c.get(Calendar.YEAR);
		int M = c.get(Calendar.MONTH);
		int D = c.get(Calendar.DATE);
		int A = Y / 100;
		int B = A / 4;
		int C = 2 - A + B;
		float E = (int) (365.25f * (Y + 4716));
		float F = (int) (30.6001f * (M + 1));
		float JD = C + D + E + F - 1524.5f;

		return JD;
	}

	/**
	 * Return a Julian date based on the input parameter. This is based from
	 * calculations found at
	 * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day
	 * Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
	 *
	 * @param date
	 * @return the julian day number
	 */
	public static final float toJulian(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return toJulian(c);
	}

	/**
	 * @param isoString
	 * @param fmt
	 * @param field
	 *            Calendar.YEAR/Calendar.MONTH/Calendar.DATE
	 * @param amount
	 * @return
	 * @throws ParseException
	 */
	public static final String dateIncrease(String isoString, String fmt, int field, int amount) {

		try {
			Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
			cal.setTime(stringToDate(isoString, fmt, true));
			cal.add(field, amount);

			return dateToString(cal.getTime(), fmt);

		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Time Field Rolling function. Rolls (up/down) a single unit of time on the
	 * given time field.
	 *
	 * @param isoString
	 * @param field
	 *            the time field.
	 * @param up
	 *            Indicates if rolling up or rolling down the field value.
	 *            use formating char's
	 * @exception ParseException
	 *                if an unknown field value is given.
	 */
	public static final String roll(String isoString, String fmt, int field, boolean up) throws ParseException {

		Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(stringToDate(isoString, fmt));
		cal.roll(field, up);

		return dateToString(cal.getTime(), fmt);
	}

	/**
	 * Time Field Rolling function. Rolls (up/down) a single unit of time on the
	 * given time field.
	 *
	 * @param isoString
	 * @param field
	 *            the time field.
	 * @param up
	 *            Indicates if rolling up or rolling down the field value.
	 * @exception ParseException
	 *                if an unknown field value is given.
	 */
	public static final String roll(String isoString, int field, boolean up) throws ParseException {

		return roll(isoString, DATETIME_PATTERN, field, up);
	}

	/**
	 * java.util.Date
	 *
	 * @param dateText
	 * @param format
	 * @param lenient
	 * @return
	 */
	public static Date stringToDate(String dateText, String format, boolean lenient) {

		if (dateText == null) {

			return null;
		}

		DateFormat df = null;

		try {

			if (format == null) {
				df = new SimpleDateFormat();
			} else {
				df = new SimpleDateFormat(format);
			}

			// setLenient avoids allowing dates like 9/32/2001
			// which would otherwise parse to 10/2/2001
			df.setLenient(false);

			return df.parse(dateText);
		} catch (ParseException e) {

			return null;
		}
	}

	/**
	 * @return Timestamp
	 */
	public static java.sql.Timestamp getCurrentTimestamp() {
		return new java.sql.Timestamp(new Date().getTime());
	}

	/**
	 * java.util.Date
	 *
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String dateString, String format) {

		return stringToDate(dateString, format, LENIENT_DATE);
	}

	/**
	 * java.util.Date
	 *
	 */
	public static Date stringToDate(String dateString) {
		return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
	}

	/**
	 * @return
	 * @param pattern
	 * @param date
	 */
	public static String dateToString(Date date, String pattern) {

		if (date == null) {

			return null;
		}

		try {

			SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
			sfDate.setLenient(false);

			return sfDate.format(date);
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * yyyy-MM-dd
	 *
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {

		return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
	}

	/**
	 * @return
	 */
	public static Date getCurrentDateTime() {
		Calendar calNow = Calendar.getInstance();
		Date dtNow = calNow.getTime();

		return dtNow;
	}

	/**
	 *
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateString(String pattern) {
		return dateToString(getCurrentDateTime(), pattern);
	}

	/**
	 * yyyy-MM-dd
	 *
	 * @return
	 */
	public static String getCurrentDateString() {
		return dateToString(getCurrentDateTime(), ISO_EXPANDED_DATE_FORMAT);
	}

	/**
	 * 返回固定格式的当前时间 yyyy-MM-dd hh:mm:ss
	 *
	 * @return
	 */
	public static String dateToStringWithTime() {

		return dateToString(new Date(), DATETIME_PATTERN);
	}

	/**
	 * yyyy-MM-dd hh:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static String dateToStringWithTime(Date date) {

		return dateToString(date, DATETIME_PATTERN);
	}

	/**
	 *
	 * @param date
	 * @param days
	 * @return java.util.Date
	 */
	public static Date dateIncreaseByDay(Date date, int days) {

		Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	/**
	 *
	 * @param date
	 * @return java.util.Date
	 */
	public static Date dateIncreaseByMonth(Date date, int mnt) {

		Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.MONTH, mnt);

		return cal.getTime();
	}

	/**
	 *
	 * @param date
	 * @param mnt
	 * @return java.util.Date
	 */
	public static Date dateIncreaseByYear(Date date, int mnt) {

		Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.YEAR, mnt);

		return cal.getTime();
	}

	/**
	 *
	 * @param date
	 *            yyyy-MM-dd
	 * @param days
	 * @return yyyy-MM-dd
	 */
	public static String dateIncreaseByDay(String date, int days) {
		return dateIncreaseByDay(date, ISO_DATE_FORMAT, days);
	}

	/**
	 * @param date
	 * @param fmt
	 * @param days
	 * @return
	 */
	public static String dateIncreaseByDay(String date, String fmt, int days) {
		return dateIncrease(date, fmt, Calendar.DATE, days);
	}

	/**
	 *
	 * @param src
	 * @param srcfmt
	 * @param desfmt
	 * @return
	 */
	public static String stringToString(String src, String srcfmt, String desfmt) {
		return dateToString(stringToDate(src, srcfmt), desfmt);
	}

	/**
	 *
	 * @param date
	 * @return string
	 */
	public static String getYear(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy");
		String cur_year = formater.format(date);
		return cur_year;
	}

	/**
	 *
	 * @param date
	 * @return string
	 */
	public static String getMonth(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("MM");
		String cur_month = formater.format(date);
		return cur_month;
	}

	/**
	 * @param date
	 * @return string
	 */
	public static String getDay(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("dd");
		String cur_day = formater.format(date);
		return cur_day;
	}

	/**
	 * @param date
	 * @return string
	 */
	public static String getHour(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("HH");
		String cur_day = formater.format(date);
		return cur_day;
	}

	public static int getMinsFromDate(Date dt) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dt);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		return ((hour * 60) + min);
	}

	/**
	 * Function to convert String to Date Object. If invalid input then current or
	 * next day date is returned (Added by Ali Naqvi on 2006-5-16).
	 *
	 * @param str
	 *            String input in YYYY-MM-DD HH:MM[:SS] format.
	 * @param isExpiry
	 *            boolean if set and input string is invalid then next day date is
	 *            returned
	 * @return Date
	 */
	public static Date convertToDate(String str, boolean isExpiry) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dt = null;
		try {
			dt = fmt.parse(str);
		} catch (ParseException ex) {
			Calendar cal = Calendar.getInstance();
			if (isExpiry) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
			} else {
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
			}
			dt = cal.getTime();
		}
		return dt;
	}

	public static Date convertToDate(String str) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date dt = null;
		try {
			dt = fmt.parse(str);
		} catch (ParseException ex) {
			dt = new Date();
		}
		return dt;
	}

	public static String dateFromat(Date date, int minute) {
		String dateFormat = null;
		int year = Integer.parseInt(getYear(date));
		int month = Integer.parseInt(getMonth(date));
		int day = Integer.parseInt(getDay(date));
		int hour = minute / 60;
		int min = minute % 60;
		dateFormat = String.valueOf(year) + (month > 9 ? String.valueOf(month) : "0" + String.valueOf(month))
				+ (day > 9 ? String.valueOf(day) : "0" + String.valueOf(day)) + " "
				+ (hour > 9 ? String.valueOf(hour) : "0" + String.valueOf(hour))
				+ (min > 9 ? String.valueOf(min) : "0" + String.valueOf(min)) + "00";
		return dateFormat;
	}

	public static String sDateFormat() {
		return new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime());
	}


	//下月的1号
	public static String getDateFirst(String date){
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		String ss = new SimpleDateFormat(ISO_EXPANDED_DATE_FORMAT).format(c.getTime());
		return ss;
	}

	//当月1号
	public static String getDateFirstC(String date){
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7))-1;
		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		String ss = new SimpleDateFormat(ISO_EXPANDED_DATE_FORMAT).format(c.getTime());
		return ss;
	}

	// 获得当天0点时间
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	// 获得昨天0点时间
	public static Date getYesTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	// 获得当前时间
	public static String getTimeCurrent() {
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime()) + " 23:59:59";
	}

	// 获得当前时间
	public static String getStringDate(String date) {
		if (date!=null&&date.contains("T")){
			date=date.substring(0, date.indexOf("T"));
		}
		return date;
	}

	// 获得当天24点时间
	public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return  cal.getTime();
	}

	// 获得本周一0点时间  String
	public static String getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return sdf.format(cal.getTime()) + " 00:00:00";
	}

	// 获得本周日24点时间
	public  static String getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning1());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return sdf.format(cal.getTime());
	}

	// 获得本周一0点时间  date
	public static Date getTimesWeekmorning1() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 根据当前日期获得上周的日期区间（上周周一和周日日期）
	 *
	 * @return
	 * @author zhaoxuepu
	 */
	public static String getLastTimeOne() {
		Calendar calendar1 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		int offset1 = 1 - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		String lastBeginDate = sdf .format(calendar1.getTime())+" 00:00:00";
		return lastBeginDate;
	}

	public static String getLastTimeLast() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		int offset2 = 7 - dayOfWeek;
		calendar2.add(Calendar.DATE, offset2 - 7);
		String lastEndDate = sdf.format(calendar2.getTime());
		lastEndDate += " 23:59:59";
		return lastEndDate;
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) throws ParseException{
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	/**
	 * 将时间戳转换为时间Date
	 * @param s 秒
	 * @return
	 */
	public static Date stampToDate(String s){
		long lt = new Long(s);
		Date date = new Date(lt*1000);
		return date;
	}

	/**
	 *
	 * @param s
	 * @return
	 */
	public static String stampToString(String s,String pattern){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		long lt = new Long(s);
		Date date = new Date(lt*1000);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 *
	 * @Description: 获取当天结束时间
	 * @return
	 */
	public static Long getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}

	/**
	 *
	 * @Description: 获取指定天开始时间
	 * @return
	 */
	public static Long getStartTime(Date date) {
		Calendar start = Calendar.getInstance();
		start.setTime(date);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		return start.getTime().getTime();
	}

	/**
	 *
	 * @Description: 获取指定天结束时间
	 * @return
	 */
	public static Long getEndTime(Date date) {
		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 999);
		return end.getTime().getTime();
	}


	public static Date addTime(Date time, int field, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(field, offset);
		return c.getTime();
	}

	/**
	 * 得到当前时间增加几天
	 *
	 * @return date
	 */
	public static Date addDay(Date startDate, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 *
	 * @Description:
	 * @param startDate
	 * @param day
	 * @return
	 */
	public static Long addDays(Date startDate, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取几天前前的日期
	 */
	public static Long getTimeInMillisBeforeToday(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, amount);
		return calendar.getTimeInMillis();
	}
	/**
	 * 获取日期为周几
	 * 返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
	 */

	public static String getDayofweek(Date date){
		String[] week = new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar cal = Calendar.getInstance();
		if (date.equals("")) {
			cal.setTime(new Date(System.currentTimeMillis()));
		}else {
			cal.setTime(date);
		}
		int index = cal.get(Calendar.DAY_OF_WEEK)-1;
		String day = week[index];
		return day;
	}
	/**
	 * 获取某个时间是上午还是下午
	 */
	public static String getAmOrPm(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int i = calendar.get(GregorianCalendar.AM_PM);
		return i==0?"上午":"下午";
	}
	/**
	 * 获取两个时间中的每一天
	 * @param bigtimeStr 开始时间 yyyy-MM-dd
	 * @param endTimeStr 结束时间 yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getDays(String bigtimeStr, String endTimeStr) throws ParseException {
		Date bigtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bigtimeStr + " 00:00:00");
		Date endtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr + " 00:00:00");
		//定义一个接受时间的集合
		List<Date> lDate = new ArrayList<>();
		lDate.add(bigtime);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(bigtime);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(endtime);
		// 测试此日期是否在指定日期之后
		while (endtime.after(calBegin.getTime()))  {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		List<String> datas = new LinkedList<>();
		for (Date date : lDate) {
			datas.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
		return datas;
	}

	/**
	 * 判断两个日期是否是同一个日期
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean  isSameDay(Date date1, Date date2){
		String dateToString = dateToString(date1, ISO_DATE_FORMAT);
		String dateToString2 = dateToString(date2, ISO_DATE_FORMAT);
		if (dateToString.equals(dateToString2)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Date dateReduceBySecond(Date date, int seconds){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);//设置参数时间
		System.out.println("系统当前时间      ："+df.format(date));
		seconds = 0 - seconds;
		c.add(Calendar.SECOND,seconds);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
		date=c.getTime();
		String str = df.format(date);
		System.out.println("系统前【"+ (0-seconds)+"】秒时间："+str);
		return c.getTime();
	}

	/**
	 * 返回两个时间之间时间集合
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String[] getDaysBetween(String startTime,String endTime){
		int i1 = compareTime2(startTime,endTime)+1;
		String[] dates = new String[i1];
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendarTemp = Calendar.getInstance();
			calendarTemp.setTime(sdf.parse(startTime));

			Calendar calendarEndTemp = Calendar.getInstance();
			calendarEndTemp.setTime(sdf.parse(endTime));
			int i = 0;
			while (calendarTemp.getTime().getTime()!= calendarEndTemp.getTime().getTime()) {//遍历查询时间
				String dt = new SimpleDateFormat("yyyy-MM-dd").format(calendarTemp.getTime().getTime() / 1000 * 1000);
//    	    	String dt = new SimpleDateFormat("yyyy-MM-dd").format(calendarTemp.getTime());
				calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
				dates[i] = dt;
				i++;
			}
			dates[dates.length-1] = sdf.format(sdf.parse(endTime));
		} catch (ParseException e) {
			logger.error("访问属性异常",e);
		}
		return dates;
	}


	/**
	 * 比较两个日期相差的天数
	 *
	 * @param endDt
	 * @param startDt
	 * @return
	 */
	public static int compareTime2(String startDt,String endDt) {
		//算两个日期间隔多少天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = parseDate(endDt);
		Date date2 = parseDate(startDt);

		int a = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
		return a;
	}

	public static Date parseDate(String date) {
		return parseDate(date,new String[] { "yyyy-MM","yyyy-MM-dd", "yyyy/MM/dd","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm" });
	}

	public static Date parseDate(String date,String ... partten) {
		Date dt = null;
		try {
			if(StringUtils.isNotEmpty(date)){
				dt = org.apache.commons.lang3.time.DateUtils.parseDate(date,partten);
			}else{
				return null;
			}
		} catch (ParseException e) {
			logger.error("字符串无法解析为时间："+date);
		}
		return dt;
	}

//	public Boolean chackDateStr(String dateStr){
//
//		Pattern.matches(pattern, content)
//
//	}

	public static void main(String[] args) {

//		System.out.println(DateUtil.dateReduceBySecond(new Date(),3));
//		System.out.println(DateUtil.dateIncreaseByDay(new Date(),2));

//		String[] daysBetween = DateUtil.getDaysBetween("2019-10-01", "2019-10-10");
//		System.out.println(daysBetween);

//		int i = DateUtil.compareTime2("2019-10-10", "2019-10-10");
//		System.out.println(i);

//		String currentDateString = getCurrentDateString(DATETIME_PATTERN_YYYY_MM);
//
//		System.out.println(currentDateString);

//		String nextDialTime = "2019-10-10 09:00";
//		Date date = DateUtil.stringToDate(nextDialTime);
//		Date nextDialDate = DateUtil.stringToDate(nextDialTime, DateUtil.DATETIME_PATTERN);
//
//		System.out.println(date + "----" + nextDialDate);



////		String patter = "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$";
//		String patter = "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$";
//		String content = "2019|10|10 09:00";
//
//		boolean matches = Pattern.matches(patter, content);
//
//		System.out.println(matches);
//
//		Date date = DateUtil.stringToDate(content);
//
//		System.out.println(date);



//		String str = "2019-10-10 09:00";
//		String pattern = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
//
//		Pattern r = Pattern.compile(pattern);
//		Matcher m = r.matcher(str);
//
//		System.out.println(m.matches());

//		String dateStr = "2019-10-10 09:00";
//		Date date = DateUtil.stringToDate(dateStr, DateUtil.ISO_EXPANDED_DATE_FORMAT_YMDHM);
//		System.out.println(date);

		String dateStr = "2019年10月10日";
		String dateStr2 = DateUtil.stringToString(dateStr, DateUtil.EXPANDED_DATE_FORMAT_WORD_1, DateUtil.ISO_EXPANDED_DATE_FORMAT);
		System.out.println(dateStr2);

//		String currentDateStr = DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN_YYYY_MM);
//		System.out.println(currentDateStr);


	}

}
