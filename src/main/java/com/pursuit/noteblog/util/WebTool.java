package com.pursuit.noteblog.util;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.net.HttpURLConnection;
//import java.net.SocketTimeoutException;
//import java.net.URL;
//import java.security.MessageDigest;
//import java.security.SecureRandom;
//import java.text.NumberFormat;
//import java.text.ParseException;
//import java.text.ParsePosition;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.GregorianCalendar;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.ResourceBundle;
//import java.util.StringTokenizer;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.StatusLine;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.CoreConnectionPNames;
//
//import com.travelsky.b2bplatform.framework.CommonConstants;
//import com.travelsky.b2bplatform.framework.XSSDefendFilter;
//import com.travelsky.b2bplatform.framework.XSSDefendFilterController;
//import com.travelsky.b2bplatform.framework.config.ConfigService;
//import com.travelsky.b2bplatform.framework.config.DBConfigSet;
//
//public class WebTool {
//
//	/**
//	 * 获取费用的数字，只保留小数点2位。55.456 ->55.45 55.633->55.63
//	 * 
//	 * @param fee
//	 * @return
//	 */
//	public static double formatFee(double fee) {
//		return Math.round(fee * 100) * 1.00 / 100;
//	}
//	
//	public static int getDiscount(double fee1, double fee2) {
//		return (int) Math.round(fee1 / fee2 * 100);
//	}
//
//	/**
//	 * 获取价格的数字，四舍五入，不保留小数点。55.51->56 55.490->55
//	 * 
//	 * @param fee
//	 * @return
//	 */
//	public static double formatPrice(double price) {
//		return Math.round(price);
//	}
//
//	/**
//	 * 格式化CNY币种的F值，要求以10结尾<BR/> method:round,
//	 * 四舍五入的方式，即如果个位上有数值，且小于5，则舍弃，如果大于等于5，则进位<BR/> method:up,
//	 * 向上进位的方式，即如果个位上有数值，只要大于0，则进位。默认值<BR/> method:down, 向下取整的方式，即如果各位上有数值，则舍弃<BR/>
//	 */
//	public static long formatFCNY(double fcnyDouble, String method) {
//		long fcnyL = (long) fcnyDouble;
//		long gewei = fcnyL % 10;
//		if (gewei == 0) {
//			return fcnyL;
//		}
//		if ("round".equalsIgnoreCase(method)) {
//			if (gewei >= 5) {
//				return (fcnyL / 10 + 1) * 10;
//			} else {
//				return (fcnyL / 10) * 10;
//			}
//
//		} else if ("down".equalsIgnoreCase(method)) {
//			return (fcnyL / 10) * 10;
//
//		} else {
//			// 默认是up
//			if (gewei > 0) {
//				return (fcnyL / 10 + 1) * 10;
//			} else {
//				return (fcnyL / 10) * 10;
//			}
//		}
//	}
//
//	/**
//	 * 字符串转换为字符串数组
//	 * 
//	 * @param str
//	 * @param token(分隔符)
//	 * @return
//	 */
//	public static String[] getStrArray(String str, String token) {
//		if (isNull(str))
//			return null;
//		StringTokenizer st = new StringTokenizer(str, token);
//		ArrayList<String> strlist = new ArrayList<String>();
//		for (; st.hasMoreElements(); strlist.add(st.nextToken()))
//			;
//		String strarr[] = new String[strlist.size()];
//		for (int i = 0; i < strlist.size(); i++)
//			strarr[i] = strlist.get(i).toString().trim();
//
//		return strarr;
//	}
//
//	/**
//	 * 字符串转换为list
//	 * 
//	 * @param str
//	 * @param token(分隔符)
//	 * @return
//	 */
//	public static List<String> getStrList(String str, String token) {
//		if (isNull(str))
//			return null;
//		StringTokenizer st = new StringTokenizer(str, token);
//		ArrayList<String> strlist = new ArrayList<String>();
//		for (; st.hasMoreElements(); strlist.add(st.nextToken()))
//			;
//		return strlist;
//	}
//
//	/** 判断一个字符是否是数字 */
//	public static boolean isDigital(char c) {
//		return (c >= '0' && c <= '9') || c == '.' || c == '-';
//	}
//
//	public static int getLength(String value) {
//		if (isNull(value))
//			return 0;
//		return value.trim().length();
//	}
//
//	/** 判断一个字符串是否全部是数字组成 */
//	public static boolean isDigitalString(String value) {
//		if (isNull(value))
//			return false;
//		for (int i = 0; i < value.length(); i++) {
//			if (!isDigital(value.charAt(i)))
//				return false;
//		}
//		return true;
//	}
//
//	/** 判断一个字符是否是数字0-9 */
//	public static boolean isInt(char c) {
//		return (c >= '0' && c <= '9');
//	}
//
//	/** 判断一个字符串是否全部是数字0-9组成 */
//	public static boolean isIntString(String value) {
//		if (isNull(value))
//			return false;
//		for (int i = 0; i < value.length(); i++) {
//			if (!isInt(value.charAt(i)))
//				return false;
//		}
//		return true;
//	}
//
//	/** 判断一个字符串是否包含数字0-9组成 */
//	public static boolean isHaveInt(String value) {
//		if (isNull(value))
//			return true;
//		if (value.indexOf("0") > -1 || value.indexOf("1") > -1
//				|| value.indexOf("2") > -1 || value.indexOf("3") > -1
//				|| value.indexOf("4") > -1 || value.indexOf("5") > -1
//				|| value.indexOf("6") > -1 || value.indexOf("7") > -1
//				|| value.indexOf("8") > -1 || value.indexOf("9") > -1) {
//			return true;
//		}
//
//		return false;
//	}
//
//	/** 判断一个字符串是否全部是由以下字符组成（"0-9"，".","/","-","()"） */
//	public static boolean isFixedString(String value) {
//		if (isNull(value))
//			return false;
//		if (getCharCount(value, '(') != getCharCount(value, ')'))
//			return false;
//		for (int i = 0; i < value.length(); i++) {
//			if (!isFixedStr(value.charAt(i)))
//				return false;
//		}
//		return true;
//	}
//
//	/**
//	 * 判断一个字符是否是以下字符（"0-9"，".","/","()"）
//	 */
//	private static boolean isFixedStr(char c) {
//		// TODO 自动生成方法存根
//		return (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '/'
//				|| c == '(' || c == ')';
//	}
//
//	/** 判断一个字符是否是字母 */
//	public static boolean isAlpha(char c) {
//		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
//	}
//
//	/** 判断一个字符是否是数字或者字符 */
//	public static boolean isAlphaOrDigital(char c) {
//		return isDigital(c) || isAlpha(c);
//	}
//
//	/** 判断一个字符串是否全部是数字[0-9]或者字符组成 */
//	public static boolean isIntOrAlpha(String value) {
//		if (isNull(value))
//			return false;
//		for (int i = 0; i < value.length(); i++) {
//			if (!isInt(value.charAt(i)) && !isAlpha(value.charAt(i)))
//				return false;
//		}
//		return true;
//	}
//	
//	
//	public static boolean isInThisString(String valueA,String[] valueB){
//		boolean result = false;
//		for(String temp:valueB){ 
//			if(temp.equals(valueA)){  
//				result = true; 
//				break; 
//				}
//			}
//		return result;
//	}
//	
//	
//
//	/** 判断一个字符串是否全部是数字或者字符组成 */
//	public static boolean isAlphaOrDigital(String value) {
//		if (isNull(value))
//			return false;
//		for (int i = 0; i < value.length(); i++) {
//			if (!isAlphaOrDigital(value.charAt(i)))
//				return false;
//		}
//		return true;
//	}
//
//	/** 判断一个字符串是否全部是字符组成 */
//	public static boolean isAlphaString(String value) {
//		if (isNull(value))
//			return false;
//		for (int i = 0; i < value.length(); i++) {
//			if (!isAlpha(value.charAt(i)))
//				return false;
//		}
//		return true;
//	}
//
//	/** 从一行line获得字符串的数组，没有数据返回null */
//	public static String[] splitString(String line, char deli) {
//		if (WebTool.isNull(line))
//			return new String[] {};
//		String[] array_tokens = line.split("[" + deli + "]");
//		int count = WebTool.getCharCount(line, deli);
//		String[] tokens = new String[count + 1];
//		for (int i = 0; i < count + 1; i++) {
//			if (array_tokens != null && i < array_tokens.length) {
//				if (!isNull(array_tokens[i]))
//					tokens[i] = array_tokens[i].trim().toUpperCase();
//				else
//					tokens[i] = "";
//			} else
//				tokens[i] = "";
//		}
//		return tokens;
//	}
//
//	/**
//	 * 判断某一个字符在字符串中出现的次数
//	 */
//	public static int getCharCount(String value, char c) {
//		if (value == null)
//			return 0;
//		int count = 0;
//		for (int i = 0; i < value.length(); i++) {
//			if (value.charAt(i) == c)
//				count++;
//		}
//		return count;
//	}
//
//	public static int getTravelskyPnrLengthMin() {
//		return 6;
//	}
//
//	public static int getTravelskyPnrLengthMax() {
//		return 6;
//	}
//
//	public static double getDouble(String value) {
//		Double i = null;
//		try {
//			i = new Double(value);
//		} catch (Exception e) {
//
//		}
//		if (i != null)
//			return i.doubleValue();
//		return -1.0;
//	}
//
//	public static int getInt(String value) {
//		Integer i = null;
//		try {
//			i = new Integer(value);
//		} catch (Exception e) {
//
//		}
//		if (i != null)
//			return i.intValue();
//		return -1;
//	}
//
//	public static String trim(String value) {
//		return isNull(value) ? "" : value.trim();
//	}
//
//	public static String replace(String value, char c) {
//		if (isNull(value))
//			return "";
//		StringBuffer buff = new StringBuffer();
//		for (int i = 0; i < value.length(); i++) {
//			if (value.charAt(i) != c)
//				buff.append(value.charAt(i));
//		}
//		return buff.toString();
//	}
//
//	/**
//	 * 用新的字符串替换原字符串的第几个字符。如把"abddddba"的第4位换成f，得到"abddfdba"
//	 * 方法为replace("abddddba", 4, "f")，
//	 * 
//	 * @param oldStr
//	 *            原字符串
//	 * @param index
//	 *            替换的位置，从0开始计数
//	 * @param newValue
//	 *            新的字符串
//	 * @return
//	 */
//	public static String replace(String oldStr, int index, String newValue) {
//		if (isNull(oldStr))
//			return "";
//		if (oldStr.length() < index + 1) {
//			return oldStr;
//		}
//		if (index < 0) {
//			return oldStr;
//		}
//		StringBuffer buff = new StringBuffer();
//		for (int i = 0; i < index; i++) {
//			buff.append(oldStr.charAt(i));
//		}
//		buff.append(newValue);
//		for (int i = index + 1; i < oldStr.length(); i++) {
//			buff.append(oldStr.charAt(i));
//		}
//		return buff.toString();
//	}
//
//	public static boolean validatePnrFormat(String pnrno) {
//		if (isNull(pnrno))
//			return false;
//		pnrno = pnrno.trim();
//		if (pnrno.length() > getTravelskyPnrLengthMax())
//			return false;
//		if (pnrno.length() < getTravelskyPnrLengthMin())
//			return false;
//		if (!isAlphaOrDigital(pnrno))
//			return false;
//		return true;
//	}
//
//	/**
//	 * 把字符串org中去掉字符串filter中出现的所有字符
//	 */
//	public static String replace(String org, String filter) {
//		if (isNull(org))
//			return "";
//		StringBuffer buff = new StringBuffer();
//		for (int i = 0; filter != null && i < filter.length(); i++) {
//			org = replace(org, filter.charAt(i));
//		}
//		buff.append(org);
//		return buff.toString();
//	}
//
//	public static boolean isSameDay(Date date1, Date date2) {
//		if (date1 == null || date2 == null)
//			return false;
//		Calendar cal1 = Calendar.getInstance();
//		cal1.setTime(date1);
//		int year1 = cal1.get(Calendar.YEAR);
//		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
//		Calendar cal2 = Calendar.getInstance();
//		cal2.setTime(date2);
//		int year2 = cal2.get(Calendar.YEAR);
//		int day2 = cal2.get(Calendar.DAY_OF_YEAR);
//
//		if (year1 == year2 && day1 == day2)
//			return true;
//		return false;
//	}
//
//	/** 返回指定日期的星期 */
//	public static int getCnWeek(Date date) {
//		if (date == null)
//			return -1;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int week = (cal.get(Calendar.DAY_OF_WEEK) + 6) % 7;
//		if (week == 0)
//			week = 7;
//		return week;
//	}
//
//	/** 返回指定日期的年，若日期为空则返回-1 */
//	public static int getYear(Date date) {
//		if (date == null)
//			return -1;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		return cal.get(Calendar.YEAR);
//	}
//
//	/** 返回指定日期的月，若日期为空则返回-1 */
//	public static int getMonth(Date date) {
//		if (date == null)
//			return -1;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		return cal.get(Calendar.MONTH);
//	}
//
//	/** 返回指定日期的日，若日期为空则返回-1 */
//	public static int getDay(Date date) {
//		if (date == null)
//			return -1;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		return cal.get(Calendar.DAY_OF_MONTH);
//	}
//
//	/** 返回指定日期的小时，若日期为空则返回-1 */
//	public static int getHour(Date date) {
//		if (date == null)
//			return -1;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		return cal.get(Calendar.HOUR_OF_DAY);
//	}
//
//	/** 返回指定日期的分钟，若日期为空则返回-1 */
//	public static int getMinute(Date date) {
//		if (date == null)
//			return -1;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		return cal.get(Calendar.MINUTE);
//	}
//
//	/** 返回指定日期的秒，若日期为空则返回-1 */
//	public static int getSecond(Date date) {
//		if (date == null)
//			return -1;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		return cal.get(Calendar.SECOND);
//	}
//
//	/** 返回给定日期前几天在某小时某分钟某秒的时间 */
//	public static Date getBeforeDate(Date date, int beforedays, int hour,
//			int min, int secs) {
//		if (date == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int day = cal.get(Calendar.DAY_OF_YEAR);
//		cal.set(Calendar.DAY_OF_YEAR, day - beforedays);
//		cal.set(Calendar.HOUR_OF_DAY, hour);
//		cal.set(Calendar.MINUTE, min);
//		cal.set(Calendar.SECOND, secs);
//		return cal.getTime();
//	}
//
//	/** 返回给定日期后几天在某小时某分钟某秒的时间 */
//	public static Date getAfterDate(Date date, int afterdays, int hour,
//			int min, int secs) {
//		return getBeforeDate(date, -afterdays, hour, min, secs);
//	}
//
//	/** 返回给定日期前几天在某小时某分钟某秒的时间 */
//	public static Date getBeforeDateHour(Date date, int beforehours) {
//		if (date == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int hour = cal.get(Calendar.HOUR_OF_DAY);
//		cal.set(Calendar.HOUR_OF_DAY, hour - beforehours);
//		return cal.getTime();
//	}
//
//	public static Date getBeforeDateMin(Date date, int beforeMins) {
//		if (date == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTimeInMillis(date.getTime() - beforeMins * 60 * 1000);
//		return cal.getTime();
//	}
//
//	/** 返回给定日期前几天在某小时某分钟某秒的时间 */
//	public static int getBeforeDays(Date date1, Date date2) {
//		if (date1 == null || date2 == null)
//			return 0;
//		long beforemills = date1.getTime() - date2.getTime();
//		return (int) Math.ceil(beforemills / (3600 * 24 * 1000.0));
//		// return (int)(beforemills/(3600*24*1000));
//	}
//
//	/** 返回给定日期后某小时的时间 */
//	public static Date getAfterDateHour(Date date, int afterhours) {
//		return getBeforeDateHour(date, -afterhours);
//	}
//
//	public static Date getAfterDateMin(Date date, int afterMins) {
//		return getBeforeDateMin(date, -afterMins);
//	}
//
//	/** 返回给定日期前几天在某小时某分钟某秒的时间 */
//	public static Date getAfterDate(Date date, int afterdays) {
//		return getBeforeDate(date, -afterdays);
//	}
//
//	/** 返回给定日期前几天在某小时某分钟某秒的时间 */
//	public static Date getBeforeDate(Date date, int beforedays) {
//		if (date == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int day = cal.get(Calendar.DAY_OF_YEAR);
//		cal.set(Calendar.DAY_OF_YEAR, day - beforedays);
//		return cal.getTime();
//	}
//	
//	/** 返回给定时间后几秒钟的时间 add by yuran*/
//	public static Date getAfterSecond(Date date, int second) {
//		return getBeforeSecondDate(date, -second);
//	}
//	
//	/** 返回给定时间（最小位为分）前某一秒的时间 add by yuran*/
//	public static Date getBeforeSecondDate(Date date, int second) {
//		if (date == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int sec = cal.get(Calendar.SECOND);
//		cal.set(Calendar.SECOND, sec - second);
//		return cal.getTime();
//	}
//	
//	/** 返回给定日期前几天在某小时某分钟某秒的时间 */
//	public static Date getBeforeDate(String datestr, int beforedays) {
//		if (isNull(datestr))
//			return null;
//		Date date = getSimpleDate(datestr);
//		if (date == null)
//			return null;
//		else
//			return getBeforeDate(date, beforedays);
//	}
//
//	/** 返回给定日期前几月在某小时某分钟某秒的时间 */
//	public static Date getBeforeMonth(Date date, int beforeMonths) {
//		if (date == null) {
//			return null;
//		}
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int month = cal.get(Calendar.MONTH);
//		if ((month + 1 - 3) <= 0) {
//			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
//		}
//		month = (12 + month - beforeMonths) % 12;// 计算date前beforeMonths的月份
//
//		// 获得date前beforeMonths的月份的最大天数，以比较日期是否合理，如2011-05-30的3个月前，计算得出2011-02-30
//		// 因为2月份不能超过28，则不合理，改为2011-03-01
//		Calendar beforeCal = Calendar.getInstance();
//		beforeCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
//		beforeCal.set(Calendar.MONTH, month);
//
//		if (beforeCal.getActualMaximum(Calendar.DAY_OF_MONTH) < cal
//				.get(Calendar.DAY_OF_MONTH)) {
//			cal.set(Calendar.MONTH, month + 1);
//			cal.set(Calendar.DATE, 1);
//		} else {
//			cal.set(Calendar.MONTH, month);
//		}
//
//		return cal.getTime();
//	}
//
//	public static Date getNextYear(Date now) {
//		if (now == null)
//			return null;
//		String year = WebTool.getDateFormatCnStr(now, "yyyy");
//		String month = WebTool.getDateFormatCnStr(now, "MM");
//		String day = WebTool.getDateFormatCnStr(now, "dd");
//		int int_year = getInt(year);
//		Date nextyear = WebTool.get24OfTheDay((int_year + 1) + month + day,
//				"yyyyMMdd");
//		return nextyear;
//	}
//
//	/** 判断第一个浮点数是否大于第二个浮点数 * */
//	public static boolean more(double value1, double value2) {
//		if (!(Math.abs(value1 - value2) < 0.0001 || value1 < value2)) {
//			return true;
//		}
//		return false;
//	}
//
//	/** 返回标准化的浮点数 */
//	public static double getNormalDouble(double value, int num) {
//		return Math.round(value * Math.pow(10, num)) / (Math.pow(10, num));
//	}
//
//	/** 判断两个字符串是否相等 */
//	public static boolean equals(String value1, String value2) {
//		if (value1 != null && value1.equals(value2))
//			return true;
//		return false;
//	}
//
//	/** 判断两个双浮点数是否相等 */
//	public static boolean equals(double value1, double value2) {
//		if (Math.abs(value1 - value2) < 0.0001)
//			return true;
//		return false;
//	}
//
//	/** 判断两个字符串是否相等 */
//	public static boolean equalsIgnoreCase(String value1, String value2) {
//		if (value1 != null && value1.equalsIgnoreCase(value2))
//			return true;
//		return false;
//	}
//
//	/** 判断两个日期是否相等 */
//	public static boolean equals(Date date1, Date date2) {
//		if (date1 != null && date2 != null
//				&& date1.getTime() == date2.getTime())
//			return true;
//		return false;
//	}
//
//	/** 判断字符串是否为空 */
//	public static boolean isNull(String value) {
//		return (null == value || value.trim().length() == 0);
//	}
//	
//	/**判断字符串数组是否为空：当里面的值全为null或"",返回true；否则返回false*/
//	public static boolean isNull(String[] values){
//		if(values == null || values.length == 0){
//			return true ;
//		}
//		int nullNum = 0 ;
//		for(String val : values){
//			if(isNull(val)){
//				nullNum ++ ;
//			}
//		}
//		return nullNum == values.length ;
//	}
//
//	/** 判断对象是否为空 */
//	public static boolean isNull(Object object) {
//		return null == object;
//	}
//
//	/** 判断日期变量是否为空 */
//	public static boolean isNull(Date date) {
//		return null == date;
//	}
//
//	/** 从用户输入得到字符串,空则返回"" */
//	public static String getString(String value) {
//		return value != null ? value.trim() : "";
//	}
//
//	/** 获得字符串变量(upper)，如果字符串为空返回"" */
//	public static String getUpperString(String value) {
//		return getUpperString(value, "");
//	}
//
//	/** 获得字符串变量(upper)，如果字符串为空返回null_str */
//	public static String getUpperString(String value, String null_str) {
//		return value != null ? value.trim().toUpperCase() : null_str;
//	}
//
//	/** 获得字符串变量(lower)，如果字符串为空返回null_str */
//	public static String getLowerUpperString(String value, String null_str) {
//		return value != null ? value.trim().toLowerCase() : null_str;
//	}
//
//	/** 获得字符串变量(lower)，如果字符串为空返回null_str */
//	public static String getLowerUpperString(String value) {
//		return getLowerUpperString(value, "");
//	}
//
//	/** 获得字符串变量，如果字符串为空返回null_str */
//	public static String getString(String value, String null_str) {
//		if (value == null || equalsIgnoreCase(value, "NULL"))
//			return null_str;
//		return value.trim();
//	}
//
//	/** 获得字符串变量，如果字符串为空返回null_str或者"" */
//	public static String getStringnullandspace(String value, String null_str) {
//		value = value.trim();
//		if (value == null || value.equals("")
//				|| equalsIgnoreCase(value, "NULL"))
//			return null_str;
//		return value.trim();
//	}
//
//	/** 获得字符串变量，如果字符串为空返回null_str */
//	public static String getDateString(String value, String null_str) {
//		return value != null ? value.trim() : "0000-00-00";
//	}
//
//	/** 获得中文格式的时间字符串 */
//	public static String getDateFormatCnStr(Date date, String pattern) {
//		if (date == null)
//			return "";
//		String result = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(pattern,
//					Locale.SIMPLIFIED_CHINESE);
//			result = sdf.format(date);
//		} catch (Exception e) {
//			System.out.println("时间转换出错：Date" + date.toString() + ",pattern"
//					+ pattern);
//			e.printStackTrace();
//		}
//		if (!isNull(result))
//			return result;
//		return "";
//	}
//
//	/** 获得英文格式的时间字符串 */
//	public static String getDateFormatUsStr(Date date, String pattern) {
//		if (date == null)
//			return "";
//		String result = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
//			result = sdf.format(date);
//			if (!isNull(result))
//				result = result.toUpperCase();
//		} catch (Exception e) {
//			System.out.println("时间转换出错：Date" + date.toString() + ",pattern"
//					+ pattern);
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/** 显示浮点数 */
//	public static String getSimpleNumberFormat(double value, int int_num,
//			int fract_num) {
//		String result = getNumberFormat(value, int_num, fract_num);
//		return replace(result, ",");
//	}
//
//	/** 显示浮点数 */
//	public static String getNumberFormat(double value, int int_num,
//			int fract_num) {
//		NumberFormat nf = NumberFormat.getInstance();
//		nf.setMaximumIntegerDigits(int_num);
//		nf.setMaximumFractionDigits(fract_num);
//		nf.setMinimumFractionDigits(fract_num);
//		return nf.format(value);
//	}
//
//	/** 显示浮点数 */
//	public static String getCnCurrency(double value, int int_num, int fract_num) {
//		NumberFormat nf = NumberFormat
//				.getCurrencyInstance(Locale.SIMPLIFIED_CHINESE);
//		nf.setMaximumIntegerDigits(int_num);
//		nf.setMaximumFractionDigits(fract_num);
//		nf.setMinimumFractionDigits(fract_num);
//		return nf.format(value);
//	}
//
//	/**
//	 * 获得某天的0:00点钟，输入时间为“2007-12-12”
//	 */
//	public static Date getZeroOfTheDay(Date date) {
//		if (date == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH);
//		int day = cal.get(Calendar.DAY_OF_MONTH);
//		Calendar newcal = Calendar.getInstance();
//		newcal.set(Calendar.YEAR, year);
//		newcal.set(Calendar.MONTH, month);
//		newcal.set(Calendar.DAY_OF_MONTH, day);
//
//		newcal.set(Calendar.HOUR_OF_DAY, 0);
//		newcal.set(Calendar.MINUTE, 0);
//		newcal.set(Calendar.SECOND, 0);
//		newcal.set(Calendar.MILLISECOND, 0);
//		return newcal.getTime();
//	}
//	
//	/**
//	 * 得到指定时间的前一分钟
//	 * @param strDate
//	 * @return
//	 */
//	public static Date getBeforeOneMinute(Date date) {
//		if (date == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH);
//		int day = cal.get(Calendar.DAY_OF_MONTH);
//		int hour= cal.get(Calendar.HOUR_OF_DAY);
//		int minute=cal.get(Calendar.MINUTE);
//		Calendar newcal = Calendar.getInstance();
//		newcal.set(Calendar.YEAR, year);
//		newcal.set(Calendar.MONTH, month);
//		newcal.set(Calendar.DAY_OF_MONTH, day);
//
//		newcal.set(Calendar.HOUR_OF_DAY, hour);
//		newcal.set(Calendar.MINUTE, minute);
//		newcal.set(Calendar.SECOND, 0);
//		newcal.set(Calendar.MILLISECOND, 0);
//		return newcal.getTime();
//		}
//	
//
//	public static String getNormalDateString(String value) {
//		if (isNull(value))
//			return "";
//		StringBuffer buff = new StringBuffer();
//		for (int i = 0; i < value.length(); i++) {
//			if (value.charAt(i) != '/' && value.charAt(i) != '-')
//				buff.append(value.charAt(i));
//		}
//		return buff.toString();
//	}
//
//	/**
//	 * 获得某天的23:59:59点钟，输入时间为“2007-12-12”
//	 */
//	public static Date get24OfTheDay(Date date) {
//		if (date == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH);
//		int day = cal.get(Calendar.DAY_OF_MONTH);
//		Calendar newcal = Calendar.getInstance();
//		newcal.set(Calendar.YEAR, year);
//		newcal.set(Calendar.MONTH, month);
//		newcal.set(Calendar.DAY_OF_MONTH, day);
//
//		newcal.set(Calendar.HOUR_OF_DAY, 23);
//		newcal.set(Calendar.MINUTE, 59);
//		newcal.set(Calendar.SECOND, 59);
//		newcal.set(Calendar.MILLISECOND, 0);
//
//		return newcal.getTime();
//	}
//
//	public static Date getSimpleDate(String date_str) {
//		if (isNull(date_str))
//			return null;
//		date_str = date_str.trim();
//		date_str = WebTool.replace(date_str, "/-: ");
//		if (date_str != null && date_str.length() == 7) {
//			Date date = getCnDate(date_str, "yyyyMdd");
//			if (date == null)
//				date = getCnDate(date_str, "yyyyMMd");
//			return date;
//		} else if (date_str != null && date_str.length() == 8)
//			return getCnDate(date_str, "yyyyMMdd");
//		else if (date_str != null && date_str.length() == 6)
//			return getCnDate(date_str, "yyMMdd");
//		else if (date_str != null && date_str.length() == 12)
//			return getCnDate(date_str, "yyyyMMddHHmm");
//		else if(date_str != null && date_str.length() == 14)
//			return getCnDate(date_str, "yyyyMMddHHmmss");
//		else
//			return null;
//	}
//
//	/**
//	 * 获得时间对象，输入时间为“2007-12-12”
//	 */
//	public static Date getCnDate(String date_str, String patt) {
//		if (isNull(date_str))
//			return null;
//		String pattern = "yyyy-MM-dd";
//		if (!isNull(patt))
//			pattern = patt;
//		Date result = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(pattern,
//					Locale.SIMPLIFIED_CHINESE);
//			result = sdf.parse(date_str.trim());
//		} catch (Exception e) {
//			// System.out.println("时间转换出错：Date:"+date_str+",pattern:"+patt);
//			// e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * 获得时间对象，输入时间为“2007-12-12”
//	 */
//	public static Date getUsDate(String date_str, String patt) {
//		if (isNull(date_str))
//			return null;
//		String pattern = "yyyy-MM-dd";
//		if (!isNull(patt))
//			pattern = patt;
//		Date result = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
//			result = sdf.parse(date_str.trim());
//		} catch (Exception e) {
//			// System.out.println("时间转换出错：Date:"+date_str+",pattern:"+patt);
//			// e.printStackTrace();
//		}
//		return result;
//	}
//
//	/** 获得时间对象，支持两种格式的输入时间 */
//	public static Date getUsDateNew(String date_str) {
//		String pattern = "yyyy-MM-dd";
//		if (!WebTool.isNull(date_str) && date_str.indexOf('-') == -1)
//			pattern = "yyyyMMdd";
//		return getUsDate(date_str, pattern);
//	}
//
//	/** 获得时间对象，支持两种格式的输入时间 */
//	public static Date getCnDateNew(String date_str) {
//		String pattern = "yyyy-MM-dd";
//		if (!WebTool.isNull(date_str) && date_str.indexOf('-') == -1)
//			pattern = "yyyyMMdd";
//		return getCnDate(date_str, pattern);
//	}
//
//	/**
//	 * 获得某天的0:00点钟，输入时间为“2007-12-12”
//	 */
//	public static Date getZeroOfTheDay(String date_str, String patt) {
//		if (isNull(date_str))
//			return null;
//		String pattern = "yyyy-MM-dd";
//		if (!isNull(patt))
//			pattern = patt;
//		Date result = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(pattern,
//					Locale.SIMPLIFIED_CHINESE);
//			result = sdf.parse(date_str);
//		} catch (Exception e) {
//			System.out.println("时间转换出错：Date:" + date_str + ",pattern:" + patt);
//			e.printStackTrace();
//		}
//		if (result != null)
//			return getZeroOfTheDay(result);
//		return result;
//	}
//
//	/**
//	 * 获得某天的23:59:59，即第二天的凌晨差1秒，输入时间为“2007-12-12”
//	 */
//	public static Date get24OfTheDay(String date_str, String patt) {
//		if (isNull(date_str))
//			return null;
//		String pattern = "yyyy-MM-dd";
//		if (!isNull(patt))
//			pattern = patt;
//		Date result = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(pattern,
//					Locale.SIMPLIFIED_CHINESE);
//			result = sdf.parse(date_str);
//		} catch (Exception e) {
//			System.out.println("时间转换出错：Date:" + date_str + ",pattern:" + patt);
//			e.printStackTrace();
//		}
//		if (result != null)
//			return get24OfTheDay(result);
//		return result;
//	}
//
//	/** 获取2个日期之间相差的天数，如果date1在date2的前面，则返回负数 */
//	public static int getIntervalDay(Date date1, Date date2) {
//		if (date1 == null || date2 == null) {
//			return 0;
//		}
//		// 先将日期的时分秒都设置0
//		Date dateZero1 = getZeroOfTheDay(date1);
//		Date dateZero2 = getZeroOfTheDay(date2);
//
//		long intervalMils = dateZero1.getTime() - dateZero2.getTime();
//		return (int) (intervalMils / (1000 * 24 * 3600));
//	}
//
//	/** 获取两个日期之间相差的小时数，保留2位小数，如果date1在date2的前面，则返回负数 */
//	public static double getIntervalHours(Date date1, Date date2) {
//		if (date1 == null || date2 == null) {
//			return 0;
//		}
//		long intervalMils = date1.getTime() - date2.getTime();
//		return Math.round(intervalMils / (3600.0 * 1000.0) * 100.0) / 100.0;
//	}
//
//	/** 判断第一个日期是否早于第二个日期 * */
//	public static boolean isEarlyOnSecDate(Date date1, Date date2) {
//		long intervalMils = date2.getTime() - date1.getTime();
//		if (intervalMils >= 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	/**
//	 * 将日期转为有年月日 时分的日期
//	 * 
//	 * @param date
//	 * @return
//	 */
//	public static Date toDateHHmm(Date date) {
//		try {
//			return new SimpleDateFormat("yyyy-MM-dd HH:mm")
//					.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
//		} catch (ParseException e) {
//			throw new RuntimeException("时间转化出错。");
//		}
//	}
//	
//	/**
//	 * 将日期转为只有年月日的日期
//	 * 
//	 * @param date
//	 * @return
//	 */
//	public static Date toDate(Date date) {
//		try {
//			return new SimpleDateFormat("yyyy-MM-dd")
//					.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
//		} catch (ParseException e) {
//			throw new RuntimeException("时间转化出错。");
//		}
//	}
//
//	/**
//	 * 加n天
//	 * 
//	 * @param date
//	 * @param n
//	 * @return
//	 */
//	public static Date addDay(Date date, int n) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.add(Calendar.DAY_OF_YEAR, n);
//		return cal.getTime();
//	}
//	
//	/**
//	 * 加n天
//	 * 
//	 * @param date
//	 * @param n
//	 * @return
//	 */
//	public static Date addHour(Date date, int h) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.add(Calendar.HOUR_OF_DAY, h);
//		return cal.getTime();
//	}
//	
//	/** 判断时间格式是否有效 */
//
//	public static boolean isAvailableDate(String date_str, String pattern) {
//		Date result = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(pattern,
//					Locale.SIMPLIFIED_CHINESE);
//			result = sdf.parse(date_str);
//		} catch (Exception e) {
//			return false;
//		}
//		return result != null;
//	}
//
//	/** 获得整型的double数，并返回字符串类型 */
//	public static String getIntString(double value) {
//		int int_val = (int) (value);
//		return Integer.toString(int_val);
//	}
//
//	public static boolean betweenDate(Date date, Date begin, Date end) {
//		if (date == null || begin == null || end == null)
//			return false;
//		if (date.getTime() >= begin.getTime() && date.getTime() < end.getTime())
//			return true;
//		return false;
//	}
//
//	/**
//	 * 中文数字
//	 */
//	private static String[] cnNumber = { "零", "一", "二", "三", "四", "五", "六",
//			"七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八",
//			"十九", "二十" };
//	
//	/**
//	 * 英文数字
//	 */
//	private static String[] usNumber ={"Zeroth","First","Second","Third","Fourth","Fifth","Sixth","Seventh",
//			"Eighth","Ninth","Tenth","Eleventh","Twelfth","Thirteenth","Fourteenth","Fifteenth","Sixteenth",
//			"Seventeenth","Eighteenth","Nineteenth","Twentieth"	};
//	
//	/**
//	 * 繁体数字
//	 */
//	private static String[] twNumber ={"零", "壹", "二", "三", "四", "五", "六",
//			"七", "八", "九", "十", "十壹", "十二", "十三", "十四", "十五", "十六", "十七", "十八",
//			"十九", "二十" };
//	
//    public static String getFlightNumber(String lan,int i){
//    	if(equals(lan, CommonConstants.LANGUAGE_TYPE_ZH_TW)){
//    		return getTaiwanNumber(i);
//    	}else if(equals(lan, CommonConstants.LANGUAGE_TYPE_EN_US)){
//    		return getEnglishNumber(i);
//    	}
//    	return getChineseNumber(i);
//    	
//    }
//	
//	public static String getChineseNumber(int i) {
//		if (i >= 0 && i < cnNumber.length)
//			return cnNumber[i];
//		else
//			return "";
//	}
//	
//	public static String getTaiwanNumber(int i) {
//		if (i >= 0 && i < twNumber.length)
//			return twNumber[i];
//		else
//			return "";
//	}
//	public static String getEnglishNumber(int i) {
//		if (i >= 0 && i < usNumber.length)
//			return usNumber[i];
//		else
//			return "";
//	}
//
//	/** 字符串编码 */
//	public static String encode(String password) {
//		return password;
//	}
//
//	/** 字符串解码 */
//	public static String decode(String password) {
//		return password;
//	}
//
//	/**
//	 * 判断大客户代码是否符合以下规则：（九位）六位字母+三位数字，例如PEKXMN001；
//	 */
//	public static boolean isGroupNameValid(String groupName) {
//		if (groupName == null || groupName.length() != 9)
//			return false;
//		String prefix = groupName.substring(0, 6);
//		for (int i = 0; i < prefix.length(); i++) {
//			if (!isAlpha(prefix.charAt(i)))
//				return false;
//		}
//		String suffix = groupName.substring(6);
//		for (int i = 0; i < suffix.length(); i++) {
//			if (!isDigital(suffix.charAt(i)))
//				return false;
//		}
//		return true;
//	}
//
//	/**
//	 * 对大客户代码进行转化，遇数字即转化为字母，规则：0->A,1->B,2->C,3->D,4->E,5->F,6->G,7->H,8->I,9->J
//	 * 例如PEKXMN001转化为PEKXMNAAB
//	 */
//	public static String convertGroupName(String orginalGroupName) {
//		StringBuffer newGroupName = new StringBuffer(orginalGroupName
//				.substring(0, 6));
//		char chNew;
//		for (int j = 6; j < orginalGroupName.length(); j++) {
//			chNew = (char) (orginalGroupName.charAt(j) + 17);
//			newGroupName.append(chNew);
//		}
//		return newGroupName.toString();
//	}
//
//	/**
//	 * 获得指定日期的第二天的0:00点
//	 */
//	public static Date getOneMoreDayZeroOfTheDay(Date date) {
//		if (date == null)
//			return null;
//		Date result = getZeroOfTheDay(date);
//		return getAfterDate(result, 1);
//	}
//
//	public static Date getOneLessDay2399OfTheDate(Date date) {
//		if (date == null)
//			return null;
//		Date result = get24OfTheDay(date);
//		return getBeforeDate(result, 1);
//	}
//
//	/**
//	 * 将整数值转换为二位字符的字符串，0->"00", 1->"01",...,99->"99"
//	 */
//	public static String getStandartNumber(int num) {
//		if (num > 9)
//			return new Integer(num).toString();
//		if (num >= 0 && num <= 9)
//			return "0" + new Integer(num).toString();
//		return "";
//	}
//
//	/*
//	 * public static boolean isSpecialForward(String nexturl){ return
//	 * nexturl.equals(ForwardToPage.XMB2B_HOME_PAGE) ||
//	 * nexturl.equals(ForwardToPage.XMB2B_OK_PAGE) ||
//	 * nexturl.equals(ForwardToPage.XMB2B_ERROR_PAGE) ||
//	 * isJumpToLastPage(nexturl); }
//	 * 
//	 * public static boolean isForwardToHomePage(String nexturl){ return
//	 * nexturl!=null && nexturl.equals(ForwardToPage.XMB2B_HOME_PAGE); }
//	 * 
//	 * public static boolean isJumpToLastPage(String nexturl){ return
//	 * nexturl!=null && nexturl.indexOf(ForwardToPage.XMB2B_LAST_PAGE_PERFIX) !=
//	 * -1; }
//	 * 
//	 * public static String getForwardToPage(String nexturl){ StringBuffer buf =
//	 * new StringBuffer(""); if (isNull(nexturl)) return buf.toString(); if
//	 * (!isSpecialForward(nexturl)){ buf.append("<input type=\"button\"
//	 * value=\" 返 回 \" onclick=\"javascript:window.location.href='").
//	 * append(nexturl).append("'\" />"); return buf.toString(); } if
//	 * (isJumpToLastPage(nexturl)){ int backward =
//	 * Integer.parseInt(nexturl.substring(4)); buf.append("<input type='button'
//	 * value=' 返 回 '
//	 * onclick='javascript:history.go(-").append(backward).append(")'/>"); }
//	 * else { buf.append("<input type=\"button\" value=\" 返 回 \"
//	 * onclick=\"javascript:window.location.href='").
//	 * append(getForwardToPage(nexturl)).append("'\" />"); // buf.append("<a
//	 * href='").append(getForwardToPage(nexturl)).append("'"); // if
//	 * (isForwardToHomePage(nexturl)) // buf.append(" target='_top'"); //
//	 * buf.append(">返 回</a>"); } return buf.toString(); }
//	 */
//
//	/** 获取指定日期后一年的日期 */
//	public static Date getYearsLater(Date baseDate, int yearNum) {
//		if (baseDate == null)
//			return null;
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(baseDate);
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH);
//		int day = cal.get(Calendar.DAY_OF_MONTH);
//		int hour = cal.get(Calendar.HOUR_OF_DAY);
//		int min = cal.get(Calendar.MINUTE);
//		int sec = cal.get(Calendar.SECOND);
//
//		Calendar newcal = Calendar.getInstance();
//		newcal.set(Calendar.YEAR, year + yearNum);
//		newcal.set(Calendar.MONTH, month);
//		newcal.set(Calendar.DAY_OF_MONTH, day);
//		newcal.set(Calendar.HOUR_OF_DAY, hour);
//		newcal.set(Calendar.MINUTE, min);
//		newcal.set(Calendar.SECOND, sec);
//		return newcal.getTime();
//	}
//
//	/** 判断整型变量是否为空 */
//	public static boolean isNull(Integer value) {
//		return value == null || value.intValue() < 0;
//	}
//
//	public static boolean containsInt(int[] valueArray, int value) {
//		if (valueArray == null || valueArray.length == 0)
//			return false;
//		for (int i = 0; i < valueArray.length; i++) {
//			if (value == valueArray[i])
//				return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 将字符串每割fragLength长度增加一个<br />
//	 * 
//	 */
//	public static String getFragString(String originalString, int fragLength) {
//		StringBuffer buf = new StringBuffer();
//		int index = 0;
//		while (index + fragLength < originalString.length()) {
//			buf.append(originalString.substring(index, index + fragLength))
//					.append("<br />");
//			index += fragLength;
//		}
//		buf.append(originalString.substring(index));
//		return buf.toString();
//	}
//
//	public static boolean judgeStrIsGb2312(String str, int[] errCharIndex) {
//		if (str == null) {
//			errCharIndex[0] = -1;
//			return true;
//		}
//		try {
//			for (int i = 0; i < str.length(); i++) {
//				String chara = str.substring(i, i + 1);
//				byte[] bts = chara.getBytes("GBK");
//				if (bts.length == 2) {
//					String s1 = (Integer.toHexString(bts[0]).length() > 2 ? Integer
//							.toHexString(bts[0]).substring(6)
//							: Integer.toHexString(bts[0]));
//					String s2 = (Integer.toHexString(bts[1]).length() > 2 ? Integer
//							.toHexString(bts[1]).substring(6)
//							: Integer.toHexString(bts[1]));
//					// System.out.println(chara+"==="+s1+s2);
//					int i1 = Integer.valueOf(s1, 16).intValue();
//					if (i1 < 0xb0 || i1 > 0xf7) {
//						errCharIndex[0] = i;
//						return false;
//					}
//					int i2 = Integer.valueOf(s2, 16).intValue();
//					if (i2 <= 0xa0 || i2 >= 0xff) {
//						errCharIndex[0] = i;
//						return false;
//					}
//				}
//			}
//		} catch (Exception e) {
//			return true;
//		}
//		errCharIndex[0] = -1;
//		return true;
//	}
//
//	/**
//	 * 如果字符穿中有汉字,判断此汉字是否在gb2312字符集内，西文字符做判断
//	 * 
//	 * @param str
//	 * @return
//	 */
//	public static boolean judgeStrIsGb2312(String str) {
//		int[] errCharIndex = new int[1];
//		return judgeStrIsGb2312(str, errCharIndex);
//	}
//
//	/** 替换字符串中的javascript特殊字符 xbxia */
//	public static String javascriptEscape(String input) {
//		if (input == null) {
//			return null;
//		}
//		StringBuffer result = new StringBuffer();
//		int length = input.length();
//		for (int i = 0; i < length; i++) {
//			char c = input.charAt(i);
//			if (c == '\"') {
//				result.append("\\\"");
//			} else if (c == '\'') {
//				result.append("\\'");
//			} else if (c == '\\') {
//				result.append("\\\\");
//			} else {
//				result.append(c);
//			}
//		}
//		return result.toString();
//	}
//
//	/** 替换字符串中的html特殊字符 xbxia */
//	public static String htmlEscape(String input) {
//		if (input == null) {
//			return null;
//		}
//		StringBuffer result = new StringBuffer();
//		int length = input.length();
//		for (int i = 0; i < length; i++) {
//			char c = input.charAt(i);
//			if (c == '&') {
//				result.append("&amp;");
//			} else if (c == '\"') {
//				result.append("&quot;");
//			} else if (c == '<') {
//				result.append("&lt;");
//			} else if (c == '>') {
//				result.append("&gt;");
//			} else if (c == '\'') {
//				result.append("&#39;");
//			} else if (c == '\\') {
//				result.append("&#092;");
//			} else {
//				result.append(c);
//			}
//		}
//		return result.toString();
//	}
//
//	/**
//	 * 通过Request获得页面资源的相对URL，避免在页面中使用类似hxuo的绝对路径。 RMK:add by liangsm
//	 * 
//	 * @param request
//	 *            用户请求
//	 * @return base标签的href属性值
//	 */
//	public static String getHtmlBaseUrlFromRequest(HttpServletRequest request) {
//		String basePath = request.getScheme() + "://" + request.getServerName()
//				+ ":" + request.getServerPort() + request.getContextPath()
//				+ "/";
//		return basePath;
//	}
//
//	/**
//	 * 获取合法的文件名。 主要是在下载的时候用，当文件名包含回车换行符号时必须做替换。
//	 * 
//	 * @param fileName
//	 * @return
//	 */
//	public static String getValidFileNameInResponse(String fileName) {
//		String fileName1 = fileName.replaceAll("\r\n", "");
//		fileName1 = fileName1.replaceAll("\r", "");
//		fileName1 = fileName1.replaceAll("\n", "");
//		fileName1 = fileName1.replaceAll(":", "");
//		fileName1 = fileName1.replaceAll("=", "");
//
//		return fileName1;
//	}
//
//	/**
//	 * 获取旅客类型 在页面显示旅客类型时使用，传入的旅客类型在CommonConstant中
//	 * 
//	 * @param language
//	 * @param passtype
//	 * @return
//	 */
//	public static String getPasstypeStr(Locale language, int passtype) {
//		ResourceBundle bundle = ResourceBundle.getBundle("CommonResources",
//				language);
//		String result = "";
//		switch (passtype) {
//		case 0:
//			result = bundle.getString("common.passtype.adult");
//			break;
//		case 1:
//			result = bundle.getString("common.passtype.child");
//			break;
//		case 2:
//			result = bundle.getString("common.passtype.infant");
//			break;
//		case 3:
//			result = bundle.getString("common.passtype.elder");
//			break;
//		}
//		return result;
//	}
//
//	/**
//	 * 前台显示空或者0值时的替换方案 用"-"替换
//	 * 
//	 * @param passtype
//	 * @return
//	 */
//	public static String formatNullOrZeroValue(int value) {
//		return value == 0 ? "-" : String.valueOf(value);
//	}
//
//	public static String formatNullOrZeroValue(long value) {
//		return value == 0 ? "-" : String.valueOf(value);
//	}
//
//	public static String formatNullOrZeroValue(double value) {
//		return value == 0 ? "-" : String.valueOf(value);
//	}
//
//	public static String formatNullOrZeroValue(float value) {
//		return value == 0 ? "-" : String.valueOf(value);
//	}
//
//	public static String formatNullOrZeroValue(String value) {
//		return value == null ? "-" : value;
//	}
//
//	/**
//	 * 获得一组票号的公共部分
//	 * 
//	 * @param tktList
//	 *            如 {"999-1234567890", "999-1234567891", "999-1234567892"}
//	 * @return 如"999-123456789"
//	 */
//	public static String getSameConjunc(List<String> tktList) {
//		if (tktList == null || tktList.size() == 0)
//			return "";
//		String common = "";
//		for (int i = 0; i < tktList.size(); i++) {
//			String tkt = tktList.get(i);
//			if (i == 0)
//				common = tkt;
//			else
//				common = getTicketCommonConjunction(tkt, common);
//		}
//		return common;
//	}
//
//	/**
//	 * 根据开始和结束票号，提取公共部分
//	 * 
//	 * @param tkt1
//	 *            "999-1234567890"
//	 * @param tkt2
//	 *            "999-1234567901"
//	 * @return "999-1234567"
//	 */
//	public static String getTicketCommonConjunction(String tkt1, String tkt2) {
//		if (tkt1 == null || tkt2 == null)
//			return "";
//		StringBuffer buff = new StringBuffer();
//		for (int i = 0; i < tkt1.length() && i < tkt2.length(); i++) {
//			char c1 = tkt1.charAt(i);
//			char c2 = tkt2.charAt(i);
//			if (c1 != c2)
//				break;
//			else
//				buff.append(c1);
//		}
//		return buff.toString();
//	}
//
//	/**
//	 * 根据开始票号和结束票号，获得联票信息
//	 * 
//	 * @param tkt1
//	 *            "999-1234567890"
//	 * @param tkt2
//	 *            "999-1234567901"
//	 * @return "999-1234567890/901"
//	 */
//	public static String getTicketConjunction(String tkt1, String tkt2) {
//		if (tkt1 == null || tkt2 == null)
//			return "";
//		StringBuffer buff = new StringBuffer();
//		for (int i = 0; i < tkt1.length() && i < tkt2.length(); i++) {
//			char c1 = tkt1.charAt(i);
//			char c2 = tkt2.charAt(i);
//			if (c1 != c2)
//				break;
//			else
//				buff.append(c1);
//		}
//		return buff.toString() + tkt1.substring(buff.length(), tkt1.length())
//				+ "/" + tkt2.substring(buff.length(), tkt2.length());
//	}
//
//	/**
//	 * 把集合里面的票号转化成字符串<BR/> 如输入： List<String> tktList = new ArrayList<String>();
//	 * tktList.add("999-1111111110"); tktList.add("999-1111111111");
//	 * tktList.add("999-1111111112"); tktList.add("999-1111111113");
//	 * tktList.add("999-1111111116"); tktList.add("999-1111111118");
//	 * tktList.add("999-1111111119"); tktList.add("999-1111111120");
//	 * tktList.add("999-1111111121");
//	 * 则输出："999-1111111110/3;999-1111111116;999-1111111118/21";
//	 * 
//	 * @param tktList
//	 * @exception 当输入非法字符等时，会抛出由java.math抛出的运行时异常
//	 * @return
//	 */
//	public static String getTicketConjunctionByList(List<String> tktList) {
//		// TODO dmtang
//		if (tktList == null || tktList.size() == 0) {
//			return "";
//		}
//		Collections.sort(tktList, new Comparator<String>() {
//
//			public int compare(String object1, String object2) {
//				// 获取第一个数的大数
//				String tkt1 = object1.toString();
//				String validTkt1 = tkt1.replaceAll("-", "");
//				BigDecimal numberTkt1 = new BigDecimal(validTkt1);
//				// 获取第二个数的大数
//				String tkt2 = object2.toString();
//				String validTkt2 = tkt2.replaceAll("-", "");
//				BigDecimal numberTkt2 = new BigDecimal(validTkt2);
//				// 比较两者的差
//				return numberTkt1.compareTo(numberTkt2);
//			}
//
//		});
//
//		List<List<String>> outputList = new ArrayList<List<String>>();
//		// 获取第一个元素的数字型票面信息
//		String firstTkt = tktList.get(0);
//		String validFirstTkt = firstTkt.replaceAll("-", "");
//		BigDecimal numberFirstTkt = new BigDecimal(validFirstTkt);
//		// 如果只有一张票，直接返回
//		if (tktList.size() == 1) {
//			return firstTkt;
//		}
//
//		BigDecimal lastNumberTkt = numberFirstTkt;
//		List<String> currentUnionTktList = new ArrayList<String>();
//		currentUnionTktList.add(firstTkt);
//		// 第二个元素开始
//		for (int i = 1; i < tktList.size(); i++) {
//			String tkt = tktList.get(i);
//			String validTkt = tkt.replaceAll("-", "");
//			BigDecimal currentNumberTkt = new BigDecimal(validTkt);
//			// 当前票为前一个票+1时，增加进当前联票集合
//			if (currentNumberTkt.add(lastNumberTkt.negate()).intValue() == 1) {
//				currentUnionTktList.add(tkt);
//			} else {
//				// 当前票不为前一个票+1时，联票中断，将联票集合保存至outputList，并生成新的联票集合
//				if (currentUnionTktList.size() > 0) {
//					outputList.add(currentUnionTktList);
//					currentUnionTktList = new ArrayList<String>();
//					currentUnionTktList.add(tkt);
//				}
//			}
//			lastNumberTkt = currentNumberTkt;
//			// 如果当前票为最后一个票，联票中断，将联票集合保存至outputList
//			if (i == tktList.size() - 1) {
//				outputList.add(currentUnionTktList);
//			}
//		}
//
//		// 处理ouputList为指定格式
//		StringBuffer sb = new StringBuffer();
//
//		for (int i = 0; i < outputList.size(); i++) {
//			List<String> unionTkt = outputList.get(i);
//			String firstTktInUnion = unionTkt.get(0);
//			// 如果是联票
//			if (unionTkt.size() > 1) {
//				String tktLastStr = firstTktInUnion.substring(firstTktInUnion
//						.length() - 2);
//				int tktLastNumber = Integer.parseInt(tktLastStr);
//				sb.append(firstTktInUnion + "/"
//						+ (tktLastNumber + unionTkt.size() - 1));
//			} else {
//				// 如果为单票
//				sb.append(firstTktInUnion);
//			}
//			if (i != outputList.size() - 1) {
//				sb.append(";");
//			}
//		}
//
//		// System.out.println(sb.toString());
//
//		return sb.toString();
//	}
//
//	public static String getStringWithoutFirstChar(long value) {
//		String v = "" + value;
//		if (v.length() == 0)
//			return "";
//		return v.substring(1);
//	}
//
//	public static long getLong(String value) {
//		Long i = new Long(trim(value));
//		return i.longValue();
//	}
//
//	public final static char P_CHAR[] = { '0', '1', '2', '3', '4', '5', '6',
//			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
//			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
//			'X', 'Y', 'Z' };
//
//	public static String getMD5Encoding(String s) {
//		if (s == null)
//			s = "";
//		byte[] strTemp = s.getBytes();
//		try {
//			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
//			mdTemp.update(strTemp);
//			byte[] md = mdTemp.digest();
//			int len = 0;
//			if (md != null)
//				len = md.length;
//			char str[] = new char[len * 2];
//			int k = 0;
//			for (int i = 0; md != null && i < len; i++) {
//				byte byte0 = md[i];
//				str[k++] = P_CHAR[byte0 >> 4 & 0xf];
//				str[k++] = P_CHAR[byte0 & 0xf];
//			}
//			return new String(str);
//		} catch (Exception e) {
//			return s;
//		}
//	}
//
//	/** 判断是否为三字码 */
//	public static boolean isSanzima(String code) {
//		if (isNull(code) || !isAlphaString(code) || code.length() != 3) {
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * xbxia 把PNR文本内容格式化，为方便在页面上展示
//	 * 
//	 * @param pnrContent
//	 * @return
//	 */
//	public static String formatPnrContentText4PageShow(String pnrContent) {
//		String huanhangfuInPage = "<BR/>";
//		String rtrStr = pnrContent.replaceAll("\r\n", huanhangfuInPage);
//		rtrStr = rtrStr.replace("\r", huanhangfuInPage);
//		rtrStr = rtrStr.replace("\n", huanhangfuInPage);
//		String[] rtrs = rtrStr.split(huanhangfuInPage);
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < rtrs.length; i++) {
//			if (i != 0) {
//				sb.append(huanhangfuInPage);
//			}
//			sb.append(rtrs[i]);
//		}
//		return sb.toString();
//	}
//	
//	/**
//	 * xbxia 把PNR文本内容格式化，为方便在页面上展示,用于子代理用户查看pnr内容，需要屏蔽到FC，FN项
//	 * 
//	 * @param pnrContent
//	 * @return
//	 */
//	public static String subAgentFormatPnrContentText4PageShow(String pnrContent) {
//		
//		if(pnrContent.equalsIgnoreCase("RT查询无内容"))
//			return pnrContent;
//		String huanhangfuInPage = "<BR/>";
//		String rtrStr = pnrContent.replaceAll("\r\n", huanhangfuInPage);
//		rtrStr = rtrStr.replace("\r", huanhangfuInPage);
//		rtrStr = rtrStr.replace("\n", huanhangfuInPage);
//		String[] rtrs = rtrStr.split(huanhangfuInPage);
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < rtrs.length; i++) {
//			String item = rtrs[i];
//			//将FC、FN项过滤掉
//			if(WebTool.isNull(item))
//				continue;
//			if(item.indexOf("FC/")>-1)
//				continue;
//			if(item.indexOf("FN/")>-1)
//				continue;
//			item = item.trim();
//			
//			//有时FC、FN可能有2，3行，这个时候需要过滤
//			//ACNY5040.00这种类型需要过滤掉
//			//3U BJS 231.22y
//			if(!WebTool.isStringStartWithDigitalDot(item))
//				continue;
//			
//			if (i != 0) {
//				sb.append(huanhangfuInPage);
//			}
//			sb.append(rtrs[i]);
//		}
//		return sb.toString();
//	}
//
//	/**判断str是否以“1.”、“12.”。。。“99.” 等开始。*/
//	public static boolean isStringStartWithDigitalDot(String str){
//		String first=str.substring(0,1);
//		String second=str.substring(1, 2);
//		String three =str.substring(2,3);
//		
//		if(WebTool.isDigitalString(first) && WebTool.equals(second, "."))
//			return true;
//		if(WebTool.isDigitalString(first) && WebTool.isDigitalString(second) && WebTool.equals(three, "."))
//			return true;
//		
//		return false;
//	}
//	public static boolean isNull(java.util.Calendar calendar) {
//		return calendar == null || calendar.getTime() == null;
//	}
//
//	/** 正则表达式效验 */
//	public static boolean checkRegular(String str, String regex) {
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(str);
//		return matcher.matches();
//	}
//
//	/**
//	 * 根据总数和每页长度获取总页数totalCount
//	 * 
//	 * @param totalCount
//	 * @param length
//	 * @return
//	 */
//	public static int getPageCount(int totalCount, int length) {
//		if (totalCount == 0 || length == 0)
//			return 0;
//		return (int) Math.ceil((totalCount + length - 1) / length);
//	}
//
//	public static int getRandomNum(int num1, int num2) {
//		return num1 + (int) (Math.random() * num2);
//	}
//
//	/**
//	 * 获取logmodel显示选项 在页面显示日志所属模块时候使用
//	 * 
//	 * @param key
//	 * @param language
//	 * @return
//	 */
//	public static String getLogmodelstr(java.util.Locale lantype) throws Exception {
//		StringBuffer strTW = new StringBuffer();
//		StringBuffer strUS = new StringBuffer();
//		StringBuffer strCN = new StringBuffer();
//		Map<String, String> modelMap = CommonConstants.MODEL_MAP;
//		/**
//		 * zlong 2013-2-18  修改 
//		 * 支持日志查询页面的 查询条件 “模组”下来列表的国际化
//		 */
//		for (Iterator<String> it = modelMap.keySet().iterator(); it.hasNext();) {
//			String modelkey = it.next();
//			if (lantype != null && modelkey.indexOf("_TW") > -1){
//				strTW.append("<option value=\"").append(modelkey.split("_TW")[0]).append("\">")
//				.append(modelMap.get(modelkey)).append("</option> \n");
//			}
//			else if (lantype != null && modelkey.indexOf("_US") > -1){
//				strUS.append("<option value=\"").append(modelkey.split("_US")[0]).append("\">")
//				.append(modelMap.get(modelkey)).append("</option> \n");
//			}
//			else{
//				strCN.append("<option value=\"").append(modelkey).append("\">")
//				.append(modelMap.get(modelkey)).append("</option> \n");
//			}
//		}
//		if (lantype.toString().equals("zh_TW")){
//			return strTW.insert(0, "<option value=\"A\">所有</option>").toString();
//		}
//		if (lantype.toString().equals("en_US")){
//			return strUS.insert(0, "<option value=\"A\">All</option>").toString();
//		}
//		return strCN.insert(0, "<option value=\"A\">所有</option>").toString();
//	}
//
//	/** 获取IP地址 */
//	public static String getIpAddr(HttpServletRequest request) {
//		if (request == null)
//			return "unknown";
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//
//		if (ip.indexOf(":unknown") > 0 || ip.indexOf(": unknown") > 0) {
//			do {
//				ip = ip.substring(ip.indexOf("unknown"));
//
//			} while (ip.indexOf("unknown") == 0 || ip.indexOf("unknown") == 1);
//		}
//
//		return ip;
//	}
//
//	/** 字符分隔，如:",,," 返回为两个空格而不是一个空格，一个逗号 */
//	public static String[] split(String content, String sign, int max) {
//		String[] tmp = new String[max];
//		for (int i = 0; i < max; i++) {
//			tmp[i] = content.substring(0, content.indexOf(sign));
//			content = content.substring(content.indexOf(sign) + 1);
//		}
//
//		return tmp;
//	}
//
//	/** 验证航空公司票号格式 * */
//	public static boolean checkTktNo(String tktno) {
//		String reg = ConfigService.getB2bCommonConfig().getAirTktNoReg();
//		return Pattern.matches(reg, tktno.toUpperCase());
//	}
//
//	/** 验证pnr格式 * */
//	public static boolean checkPnr(String pnr) {
//		String reg = "^\\w{5,6}$";
//		return Pattern.matches(reg, pnr.toUpperCase());
//	}
//
//	/**
//	 * 验证邮件格式
//	 * 
//	 * @param email
//	 * @return
//	 */
//	public static boolean isEmailFormat(String email) {
//		boolean tag = true;
//		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//		final Pattern pattern = Pattern.compile(pattern1);
//		final Matcher mat = pattern.matcher(email);
//		if (!mat.find()) {
//			tag = false;
//		}
//		return tag;
//	}
//
//	/**
//	 * 验证姓名格式（常用乘机人） 若输入英文字母的姓名，姓与名之间需用斜线(/)分开(中文姓名无此限制)；
//	 * 旅客姓名均应由英文26个字母组成，每个旅客姓名最多只能有1个斜线(/)； 对于输入英文字母的姓名，姓不得少于两个字母)；
//	 * 如果乘机人姓名中含有生僻字，请将生僻字及生僻字之后的字使用拼音代替， 如：王偲禹，"偲"字为生僻字，无法输入，则应输入"王siyu"。
//	 * 
//	 * @param name
//	 * @return
//	 */
//	public static boolean isPassengerNameCorrect(String name) {
//		if (name == null) {
//			return false;
//		}
//		// 存在生僻字
//		if (!WebTool.judgeStrIsGb2312(name)) {
//			return false;
//		}
//
//		String cnPattern = "^[\\u4E00-\\u9FA5]+$";// 纯中文
//		String enPattern = "^[A-Za-z]{2,}[/]{1}[A-Za-z]{1,}$";// 纯英文格式
//		String cnEnPattern = "^[\\u4E00-\\u9FA5]+[A-Za-z]+$";// 中英文(生僻字用英文代替)
//		String enCnPattern = "^[A-Za-z]+[\\u4E00-\\u9FA5]+$";// 英文中文(生僻字用英文代替)
//
//		Pattern pattern = Pattern.compile(cnPattern);
//		Matcher mat = pattern.matcher(name);
//		if (mat.find()) {
//			return true;
//		}
//
//		pattern = Pattern.compile(enPattern);
//		mat = pattern.matcher(name);
//		if (mat.find()) {
//			return true;
//		}
//
//		pattern = Pattern.compile(cnEnPattern);
//		mat = pattern.matcher(name);
//		if (mat.find()) {
//			return true;
//		}
//
//		pattern = Pattern.compile(enCnPattern);
//		mat = pattern.matcher(name);
//		if (mat.find()) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 比较输入的密码与老密码是否一致。 add by wmh 提供对不同加密方式的密码进行匹配验证
//	 * @param password
//	 * @param oldpass
//	 * @param type
//	 * @return 0密码匹配，-1 密码不匹配
//	 * @throws Exception
//	 */
//	public static int authenticatePassword(String password, String oldpass,
//			String type) throws Exception {
//
//		if (oldpass == null)
//			return -1;
//		//老川航加密方式
//		if (type != null && WebTool.equals(type, "1")) {
//			return authenticatePass3u(password,oldpass);
//		}
//		//老国航加密方式，同川航加密方式一样，但大小写不敏感
//		if (type != null && WebTool.equals(type, "2")) {
//			return authenticatePass3u(password.toUpperCase(),oldpass);
//		}
//		//默认加密方式
//		if (WebTool.getMD5Encoding(password).equals(oldpass)) {
//			return 0;
//		} else {
//			return -1;
//		}
//	}
//	//老川航加密方式
//	private static int  authenticatePass3u(String password, String oldpass) throws Exception{
//		byte[] abyte0 = password.getBytes();// 输入密码长度
//		byte[] abyte1 = oldpass.getBytes();// 数据库密文28位
//		byte[] salt = new byte[12];// 12位
//		System.arraycopy(abyte1, 0, salt, 0, 12);// 取前12位到salt
//
//		// 注意这里与加密时做的运算一样，
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update(salt);
//		md.update(abyte0);
//		byte[] digest = md.digest();
//		for (int i = 0; i < digest.length; i++) {
//			while (digest[i] < 0x41)
//				digest[i] += 0x10;
//			while (digest[i] > 0x7a) {
//				digest[i] -= 0x10;
//			}
//		}
//
//		byte[] olddigest = new byte[abyte1.length - 12];
//		// 得到运算后的密码，与数据库中的对比进行密码验证
//		System.arraycopy(abyte1, 12, olddigest, 0, abyte1.length - 12);
//		// 密码一致
//		if (Arrays.equals(digest, olddigest)) {
//			return 0;
//		} else {
//			return -1;
//		}
//	}
//
//	/**
//	 * 验证电话号码：023-738833
//	 * 
//	 * @param str
//	 * @return
//	 */
//	public static boolean isPhoneNo(String str) {
//		String phoneRegx = "^0[1-9]\\d(\\d)?\\-\\d{7,8}$";
//		Pattern phonePattern = Pattern.compile(phoneRegx);
//		Matcher mat = phonePattern.matcher(str);
//		if (mat.find()) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 判断List是否为空
//	 * 
//	 * @param list
//	 * @return
//	 */
//	public static boolean isNullList(List list) {
//		return (isNull(list) || list.size() <= 0);
//	}
//
//	/**
//	 * 父串中是否包含子串
//	 * 
//	 * @param orgString父串
//	 * @param subString子串
//	 * @return
//	 */
//	public static boolean isContainedString(String orgString, String subString) {
//		if (!isNull(orgString) && !isNull(subString)) {
//			return orgString.indexOf(subString) >= 0;
//		}
//		return false;
//	}
//
//	public static BigInteger getBigInteger(int j) {
//		return BigInteger.valueOf(j);
//	}
//
//	public static BigInteger getBigInteger(double d) {
//		long item = getLong(getIntString(d));
//		return BigInteger.valueOf(item);
//	}
//
//	public static BigInteger getBigInteger(String s) {
//		int item = getInt(s);
//		return BigInteger.valueOf(item);
//	}
//
//	public static BigDecimal getBigDecimal(String s) {
//		return BigDecimal.valueOf(getDouble(s));
//	}
//
//	public static BigDecimal getBigDecimal(double d) {
//		return BigDecimal.valueOf(d);
//	}
//
//	/**
//	 * PNR号是否为空
//	 * 
//	 * @param pnrNo
//	 * @return
//	 */
//	public static boolean isPnrNoNull(String pnrNo) {
//		return isNull(pnrNo) || "-".equals(pnrNo);
//	}
//
//	/** 获取字符串长度 */
//	public static int getStrLength(String str) {
//		String contentStr = WebTool.getString(str);
//		int c = 0;
//		for (int i = 0; i < contentStr.length(); i++) {
//			if (contentStr.charAt(i) > 255) {
//				c += 2;
//			} else {
//				c++;
//			}
//
//		}
//		return c;
//
//	}
//
////	public static void main(String[] argc) {
////
////		System.out.println(WebTool.getMD5Encoding("yangyong123"));
////		System.out.println(WebTool.isInt('.'));
////		String email = "0-1@asd.com.cn";
////		System.out.println(WebTool.isEmailFormat(email));
////		// double d = 1235.5;
////		// System.out.println("round:" + formatFCNY(d, "round"));
////		// System.out.println("up:" + formatFCNY(d, "up"));
////		// System.out.println("down:" + formatFCNY(d, "down"));
////
////		// try {
////		// System.out.println(getLogmodelstr());
////		// } catch (Exception e) {
////		// // TODO Auto-generated catch block
////		// e.printStackTrace();
////		// }
////		// String levkey = getLogConstantstr("LEV_KEY",new Locale("zh"));
////		// String levvalue = getLogConstantstr("LEV_VALUE",new Locale("zh"));
////		// System.out.println(levkey);
////		// System.out.println(levvalue);
////		// String modelkey = getLogConstantstr("MODEL_KEY",new Locale("zh"));
////		// System.out.println(modelkey);
////		// String modelvalue = getLogConstantstr("MODEL_VALUE",new
////		// Locale("zh"));
////		// System.out.println(modelvalue);
////		// String[] modelkeyArray = modelkey.split(",");
////		// System.out.println(modelkeyArray.length);
////		// String[] modelvalueArray = modelvalue.split(",");
////		// System.out.println(modelvalueArray.length);
////
////		// List tktList=new ArrayList();
////		// tktList.add("9991111111110");
////		//		
////		// tktList.add("9991111111112");
////		// tktList.add("9991111111113");
////		// tktList.add("9991111111116");
////		// tktList.add("9991111111118");
////		// tktList.add("9991111111119");
////		// tktList.add("9991111111120");
////		// tktList.add("9991111111121");
////		// tktList.add("9991111111111");
////		// System.out.println(getTicketConjunctionByList(tktList));
////		// Date now = new Date();
////		//		
////		// Date day = getSimpleDate("20080824");
////		// Date day1 = getSimpleDate("20080827");
////		//		
////		//		
////		// //System.out.println(WebTool.getBeforeDays(day1,day));
////		// System.out.println(WebTool.getDateFormatUsStr(now,"dd MMM yy"));
////		// System.out.println(WebTool.getDateFormatUsStr(now,"dd MMM yy
////		// HH:mm"));
////		// System.out.println(WebTool.getFragString("20080824",4));
////		// System.out.println(WebTool.getNumberFormat(13333333,1,3));
////		//		
////		//
////		//		
////		// String tktStr = getTicketConjunction("999-1111111110",
////		// "999-1111111123"); //getSameConjunc(tktList);
////		//		
////		// System.out.println(tktStr);
////		// System.out.println(WebTool.getDateFormatCnStr(now,"E"));
////		// System.out.println(WebTool.getDateFormatCnStr(now,"yy月MM"));
////		// System.out.println(WebTool.getBeforeDateHour(now,20));
////		// System.out.println(WebTool.getZeroOfTheDay(WebTool.getBeforeDateHour(now,20)));
////		// System.out.println(WebTool.get24OfTheDay(WebTool.getBeforeDateHour(now,20)));
////		// System.out.println(WebTool.isSameDay(now,WebTool.getBeforeDateHour(now,20)));
////		// System.out.println(WebTool.getYearsLater(now,10));
////
////		// System.out.println(WebTool.getDay(new Date()));
////		// System.out.println(WebTool.getHour(new Date()));
////		// System.out.println(WebTool.getMinute(new Date()));
////		// System.out.println(WebTool.getNumberFormat(12222222.0011,5,2));
////		// System.out.println(WebTool.getSimpleNumberFormat(12222222.0011,5,2));
////		// double value=1222.0016;
////		// value = WebTool.getNormalDouble(value,3);
////		// System.out.println(value);
////		// System.out.println(WebTool.getSimpleNumberFormat(value,5,2));
////
////		// String s="0123ss456789e";
////		// for(int i=0;i<s.length();i++)
////		// System.out.println(WebTool.isDigital(s.charAt(i)));
////		//	
////		// Date now = new Date();
////		// System.out.println(getDateFormatUsStr(now,"DDMMM"));
////		// S
////		// System.out.println(WebTool.getDateFormatUsStr(now,"ddMMMyy") );
////		// System.out.println(WebTool.getDateFormatCnStr(WebTool.getNextYear(now),"yyyyMMdd
////		// HH:mm") );
////		System.out.println(isPhoneNo("023-2dd837483"));
////	}
//
//	/**
//	 * 获取出票时限（审核日期在起飞当天或前一天的情况）
//	 * @param flightDate
//	 * @return
//	 */
//	public static Date getTimeLimitOfFlightDateIn1day(Date flightDate) {
//		Date now = new Date();
//		Date timeLimit = null;
//		Date lastDate = WebTool.getBeforeDateHour(flightDate, 3);
//		if (getBeforeDaysIgnoreHHMMSS(flightDate, now) <= 1) {
//			Date twoHoursLater = WebTool.getAfterDateHour(now, 2);
//			timeLimit = getEarlierDate(twoHoursLater, lastDate);
//		}
//		return timeLimit;
//	}
//
//	private static Date getEarlierDate(Date twoHoursLater, Date lastDate) {
//		if (twoHoursLater == null || lastDate == null)
//			return null;
//		if (twoHoursLater.getTime() <= lastDate.getTime()) {
//			return twoHoursLater;
//		}
//		return lastDate;
//	}
//
//	/**
//	 * 获得两日期的间隔天数，忽略时分秒
//	 * @param date1 较大日期
//	 * @param date2 较小日期
//	 * @return
//	 */
//	public static int getBeforeDaysIgnoreHHMMSS(Date date1, Date date2) {
//		if (date1 == null || date2 == null)
//			return 0;
//		Calendar calendar1 = Calendar.getInstance();
//		calendar1.setTime(date1);
//		calendar1.set(Calendar.HOUR_OF_DAY, 0);
//		calendar1.set(Calendar.MINUTE, 0);
//		calendar1.set(Calendar.SECOND, 0);
//
//		Calendar calendar2 = Calendar.getInstance();
//		calendar2.setTime(date2);
//		calendar2.set(Calendar.HOUR_OF_DAY, 0);
//		calendar2.set(Calendar.MINUTE, 0);
//		calendar2.set(Calendar.SECOND, 0);
//		int beforeDays = (int) Math
//				.round((calendar1.getTimeInMillis() - calendar2
//						.getTimeInMillis())
//						/ (3600 * 24 * 1000.0));
//		return beforeDays;
//	}
//	
//	/**
//	 * 将yyyy-MM-dd HH:mm:ss格式字符串转成Date
//	 * @param strDate
//	 * @return
//	 */
//	public static Date strToDateLong(String strDate) {
//		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		   ParsePosition pos = new ParsePosition(0);
//		   Date strtodate = formatter.parse(strDate, pos);
//		   return strtodate;
//		}
//	
//	
//	
//	/**
//	 * 判断时间date是否在一段时间内
//	 * @param date
//	 * @param timePeriod
//	 * @return
//	 */
//	public static boolean isDateInTimePeriod(Date date,String timePeriod){
//		if(isNull(timePeriod)||isNull(date)){
//			return false;
//		}
//		String[] timelist = timePeriod.split(",");
//		for(int i=0; i<timelist.length;i++){
//			String time = timelist[i];
//			Date begindate;
//			Date enddate;
//			if(time.indexOf(">")==0){
//				begindate = getZeroOfTheDay(time.substring(1),"yyyyMMdd");
//				if(WebTool.isEarlyOnSecDate(begindate, date)){
//					return true;
//				}
//			}else if(time.indexOf("<") == 0){
//				enddate = get24OfTheDay(time.substring(1),"yyyyMMdd");
//				if(WebTool.isEarlyOnSecDate(date, enddate)){
//					return true;
//				}
//			}else{
//				String[] times = time.split("-");
//				if(times.length!=2){
//					return false;
//				}
//				begindate = getSimpleDate(times[0]);
//				enddate = getSimpleDate(times[1]);
//				if(WebTool.betweenDate(date, begindate, enddate)){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//	/**
//	 * 判断时间date是否在一天的某段时间内
//	 * @param date
//	 * @param timePeriod
//	 * @return
//	 */
//	public static boolean isInTimePeriod(Date date,String timePeriod){
//		if(isNull(timePeriod)||isNull(date)||WebTool.equals("-",timePeriod)){
//			return false;
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String datestr = sdf.format(date);
//		String[] timelist = timePeriod.split(",");
//		for(int i=0; i<timelist.length;i++){
//			String time = timelist[i];
//			Date begindate;
//			Date enddate;
//			String[] times = time.split("-");
//			if(times.length!=2){
//				return false;
//			}
//			begindate = getSimpleDate(datestr+times[0]);
//			enddate = getSimpleDate(datestr+times[1]);
//			if(WebTool.betweenDate(date, begindate, enddate)){
//				return true;
//			}
//		}
//		return false;
//	}
//	/**
//	 * 依据常客卡号及类型获取常旅客卡号.规则:普通常旅客卡以航空公司二字码开头
//	 * */
//	public static String getFfpCardno(String cardtype,String cardno){
//		cardno = WebTool.getUpperString(cardno);
//		//验证乘机人的常客卡号.普通常客卡,必须以航空公司二字码开头,如果未输入自动添加.
//		if(!WebTool.isNull(cardno) && (WebTool.equals(cardtype, CommonConstants.FFP_NORMAL_TYPE) || WebTool.isNull(cardtype))){			 
//			if(!cardno.startsWith(CommonConstants.getAirlineCode())){
//				cardno=CommonConstants.getAirlineCode()+cardno;
//			}
//		}
//		return cardno;
//	}
//	
//	/**
//	 * 返回给定日期前几年相同月份相同天数的时间
//	 * @param compareDate
//	 * @param beforeYeare
//	 * @return
//	 */
//	public static Date getBeforeYearsDate(Date compareDate, int beforeYear){
//		if(null == compareDate){
//			return null;
//		}
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(compareDate);
//		int year = cal.get(Calendar.YEAR);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int month = cal.get(Calendar.MONTH) + 1;
//        cal.set(Calendar.YEAR, year - beforeYear);
//        if(day >28 && month == 2){
//        	int bYear = year - beforeYear;
//        	if(((GregorianCalendar)cal).isLeapYear(year) 
//        			&& !((GregorianCalendar)cal).isLeapYear(bYear)){ //判断是否为闰年
//        		day = day - 1;//平年只有28天
//            }
//        }
//        cal.set(Calendar.DAY_OF_MONTH, day);
//		return cal.getTime();
//	}
//	/**第一个时间值是否晚于第二个时间值*/
//	public static boolean isFirstDateAfterSecondDate(Date firstDate,Date secondDate){
//		if(firstDate.getTime()>secondDate.getTime())
//			return true;
//		else
//			return false;
//	}
//	
//	/**校验票价与配置的进位是否一致*/
//	public static boolean checkPriceCarryRule(double fare, String currency){
//		if(WebTool.isNull(currency)){
//			return false;
//		}
//		int divisor = 10;
//		String carryRule = CommonConstants.CURRENCY_CARRY_NUM_TENS;
//		if(DBConfigSet.getInstance().getL_Area().containsKey(currency.toUpperCase())){
//			carryRule =  DBConfigSet.getInstance().getL_Area().get(currency.toUpperCase()).getFcnyNum();
//			if(CommonConstants.CURRENCY_CARRY_NUM_UNITS.equalsIgnoreCase(carryRule)){
//				divisor = 1;
//			}else if (CommonConstants.CURRENCY_CARRY_NUM_HUNDREDS.equalsIgnoreCase(carryRule)){
//				divisor = 100;
//			}else if(CommonConstants.CURRENCY_CARRY_NUM_TENS.equalsIgnoreCase(carryRule)){
//				divisor = 10;
//			}
//			//THB币种特殊处理，THB币种必须以0或者5结尾
//			if(CommonConstants.CURRENCY_THB.equalsIgnoreCase(currency)){
//				double gewei = fare % 10;
//				if(gewei == 0 || gewei == 5){
//					return true;
//				}else{
//					return false;
//				}
//			}
//			if(fare % divisor == 0){
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	/**取得随机数
//	 * add by shl
//	 * 
//	 * */
//	
//	/** The default maximum length. */
//    private static final int DEFAULT_MAX_RANDOM_LENGTH = 15;
//    
//    /** The array of printable characters to be used in our random string. */
//    private static final char[] PRINTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345679"
//        .toCharArray();
//	
//	public static String getNewTicketId(final String prefix) {
//		final String number = getNextNumberAsString();
//		final StringBuilder builder = new StringBuilder(prefix.length() + 2
//				+ DEFAULT_MAX_RANDOM_LENGTH + number.length());
//		builder.append(prefix);
//		builder.append("-");
//		builder.append(number);
//		builder.append("-");
//		builder.append(getNewString());
//		return builder.toString();
//	}
//	private static String getNextNumberAsString() {
//		return Long.toString(getNextValue());
//	}
//	private static long getNextValue() {
//		AtomicLong count = new AtomicLong(1);
//		if (count.compareAndSet(Long.MAX_VALUE, 0)) {
//			return Long.MAX_VALUE;
//		}
//		return count.getAndIncrement();
//	}
//	private static String getNewString() {
//		final byte[] random = getNewStringAsBytes();
//
//		return convertBytesToString(random);
//	}
//
//	private static byte[] getNewStringAsBytes() {
//		final byte[] random = new byte[DEFAULT_MAX_RANDOM_LENGTH];
//		SecureRandom randomizer = new SecureRandom();
//		randomizer.nextBytes(random);
//		return random;
//	}
//	private static String convertBytesToString(final byte[] random) {
//		final char[] output = new char[random.length];
//		for (int i = 0; i < random.length; i++) {
//			final int index = Math.abs(random[i] % PRINTABLE_CHARACTERS.length);
//			output[i] = PRINTABLE_CHARACTERS[index];
//		}
//		return new String(output);
//	}
//	
//	/**时时调用
//	 * add by shl
//	 * */
//	public static String callURL(String callURL,int connectTime,int readTime)throws Exception {
//		HttpURLConnection connection = null;
//		try{
//			URL url = new URL(callURL);
//			connection = (HttpURLConnection)url.openConnection();
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			connection.setUseCaches(false);
//			connection.setDefaultUseCaches(false);
//			connection.setConnectTimeout(connectTime);
//			connection.setReadTimeout(readTime);
////			url.openStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//			String line;
//			StringBuilder builder = new StringBuilder(255);
//			while((line = reader.readLine()) != null){
//				builder.append(line);
//			}
//			return builder.toString();
//		}catch (Exception e) {
//			throw new RuntimeException(e);
//		}finally{
//			if(connection != null && connection instanceof HttpURLConnection){
//				((HttpURLConnection)connection).disconnect();
//			}
//		}
//	}
//	
//	/**
//	 * 按字母顺序比较两个字符串的大小
//	 * @param str1
//	 * @param str2
//	 * @return
//	 */
//	public static boolean compareString(String str1, String str2){
//		if (str1.compareTo(str2) < 0){
//			return true;
//		}
//		return false;
//	}
//	
//	
//	private static String XML_TAG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//	private static String SOAP_HEADER_TAG = "<soapenv:Envelope"
//		    + " xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\""
//		    + " xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\""
//		    + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
//		    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
//    		+ " <soapenv:Header/><soapenv:Body>";
//	private static String SOAP_END_TAG = "</soapenv:Body></soapenv:Envelope>";
//	private static String CONTENT_CHARSET = "utf-8";
//	
//
//	/**
//	 * HttpClient 调用 webservice，暂时不支持中文参数传递
//	 * @param url		webservice服务URL
//	 * @param params	参数
//	 * @param connectTime	建立链接的超时时间
//	 * @param requestTime	等待响应的超时时间
//	 * @return 调用失败返回Error
//	 */
//	public static String httpClientInvoke(String url,String params,int connectTime,int waitTime){
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		try{
//			HttpPost httppost = new HttpPost(url);//
//			httppost.setHeader("User-Agent", "IBE WebServices/1.0");
//			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");
//			httppost.setHeader("Pragma", "no-cache");
//			httppost.setHeader("Cache-Control", "no-cache");
//			httppost.setHeader("SoapAction", "\"\"");
//			httppost.setHeader("Connection", "Keep-Alive");
//			HttpEntity contentEntity = null;
//			String message = XML_TAG + SOAP_HEADER_TAG + params + SOAP_END_TAG;
//			contentEntity = new StringEntity(message);
//			httppost.setEntity(contentEntity);
//			httpclient.getParams().setParameter(
//				      HttpMethodParams.HTTP_CONTENT_CHARSET, CONTENT_CHARSET);
//			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTime);
//			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, waitTime);
//			HttpResponse response = httpclient.execute(httppost);
//			StatusLine status = response.getStatusLine();
//			if(status.getStatusCode() == 200){
//				HttpEntity entity = response.getEntity();
////				if (entity != null) {
////					System.out.println("Response content length: "
////							+ entity.getContentLength());
////				}
//				BufferedReader reader = new BufferedReader(new InputStreamReader(
//						entity.getContent(), "UTF-8"));
//				String line = null;
//				StringBuffer sb = new StringBuffer();
//				line = reader.readLine();
//				sb.append(line);
//				if (line != null) {
//					while ((line = reader.readLine()) != null) {
//						sb.append("\n" + line);
//					}
//				}
//				String returnContent = sb.toString();
//				if (entity != null) {
//					entity.consumeContent();
//				}
//				return returnContent.toString();
//			}else{
//				throw new Exception(status.toString());
//			}
//		}catch (SocketTimeoutException e){
//			e.printStackTrace();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		return "Error";
//	}
//	
//	/**
//	 * HttpClient 调用远程Action
//	 * @param url		远程Action的URL
//	 * @param params	参数，是一个<key, value>的Map
//	 * @param contentCharset   内容编码，如utf-8 GBK ISO-8859-1 等
//	 * @param connectTime	建立链接的超时时间，毫秒
//	 * @param requestTime	等待响应的超时时间，毫秒
//	 * @return 调用失败返回Error
//	 */
//	public static String httpClientInvokeRemoteAction(String url,
//			Map<String, String> params, String contentCharset, int connectTimeout, int waitTimeout){
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, contentCharset);
//		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeout);
//		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, waitTimeout);
//		
//		try{
//			HttpPost httppost = new HttpPost(url);
//			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//			if (params != null){
//				java.util.Iterator<String> itor = params.keySet().iterator();
//				while (itor.hasNext()){
//					String name = itor.next();
//					String value = params.get(name);
//					NameValuePair nameValuePair = new BasicNameValuePair(name, value);
//					nameValuePairs.add(nameValuePair);
//				}
//			}
//			HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs, contentCharset);
//			httppost.setEntity(httpEntity);
//			org.apache.http.HttpResponse response = httpclient.execute(httppost);
//			StatusLine status = response.getStatusLine();
//			if (status == null){
//				throw new Exception("status is null");
//			}
//			if(status.getStatusCode() == 200){
//				HttpEntity entity = response.getEntity();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(
//						entity.getContent(), contentCharset));
//				String line = null;
//				StringBuffer sb = new StringBuffer();
//				line = reader.readLine();
//				sb.append(line);
//				if (line != null) {
//					while ((line = reader.readLine()) != null) {
//						sb.append("\n" + line);
//					}
//				}
//				String returnContent = sb.toString();
//				if (entity != null) {
//					entity.consumeContent();
//				}
//				return returnContent.toString();
//			}else{
//				throw new Exception(status.toString());
//			}
//		}catch (SocketTimeoutException e){
//			e.printStackTrace();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		return "Error";
//	}
//	
//	/**
//	  * 对一个使用科学计数法表示的浮点数进行格式化，hasComma为true时返回 2,789,000.00，为否时返回 2789000.00
//	  * @Title: formatDoubleData
//	  * @param @param data
//	  * @param @return    设定文件
//	  * @return String    返回类型
//	  * @throws
//	  */
//	public static String formatDoubleData(String data, boolean hasComma){
//		String resStr = NumberFormat.getInstance().format(Double.valueOf(data));
//		if (!hasComma){
//			resStr = resStr.replaceAll(",", "");
//		}
//		return resStr;
//	}
//	
//	/**
//	  * 对一个使用科学计数法表示的浮点数进行格式化，hasComma为true时返回 2,789,000.00，为否时返回 2789000.00
//	  * @Title: formatDoubleData
//	  * @param @param data
//	  * @param @return    设定文件
//	  * @return String    返回类型
//	  * @throws
//	  */
//	public static String formatDoubleData(double data, boolean hasComma){
//		String resStr = NumberFormat.getInstance().format(data);
//		if (!hasComma){
//			resStr = resStr.replaceAll(",", "");
//		}
//		return resStr;
//	}
//	
//	/**
//	  * 对报表中的浮点数格式进行调整，hasComma为true时返回 2,789,000.00，为否时返回 2789000.00
//	  * @Title: formatDoubleData
//	  * @param @param data
//	  * @param @return    设定文件
//	  * @return String    返回类型
//	  * @throws
//	  */
//	public static String formatDoubleData4Report(String data, boolean hasComma){
//		String resStr = NumberFormat.getInstance().format(Double.valueOf(data));
//		if (!hasComma){
//			/**	加上 \t 后报表中显示的数据不会随着单元格的宽度而自由变化 */
//			resStr = "\"\t" + resStr.replaceAll(",", "") + "\"";
//			return resStr;
//		}
//		return resStr;
//	}
//	
//	/**
//	  * 对报表中的浮点数格式进行调整，hasComma为true时返回 2,789,000.00，为否时返回 2789000.00
//	  * @Title: formatDoubleData
//	  * @param @param data
//	  * @param @return    设定文件
//	  * @return String    返回类型
//	  * @throws
//	  */
//	public static String formatDoubleData4Report(double data, boolean hasComma){
//		String resStr = NumberFormat.getInstance().format(data);
//		if (!hasComma){
//			resStr = "\"\t" + resStr.replaceAll(",", "") + "\"";
//			return resStr;
//		}
//		return resStr;
//	}
//	
//	public static String validateXXSDFilter(HttpServletRequest request){
//		if (WebTool.equals("1", DBConfigSet.getInstance().getC_ConfigFunction()
//				.get("XSS_DEFEND_FILTER_SWITCH").getValue())) {
////			HttpServletRequest request = getRequest();
//			XSSDefendFilterController.init();
//			// System.out.println("Filter URL="+request.getServletPath());
//			Enumeration en = request.getParameterNames();
//			String prtName = "-", prtValue = "-";
//			boolean flag = false;
//			if (request.getServletPath() != null) {
//				if (!XSSDefendFilter.isInExpURL(request.getServletPath())) {
//					while (en.hasMoreElements()) {
//						prtName = (String) en.nextElement();
//						prtValue = request.getParameter(prtName);
//						if (prtValue != null) {
//							if (XSSDefendFilter.judgeHasTag(prtValue)) {
//								System.out.println("ERROR parameter:" + prtName
//										+ "=" + prtValue);
//								flag = true;
//								break;
//							}
//							if (XSSDefendFilterController.patternEnabled != null
//									&& XSSDefendFilterController.patternEnabled
//											.booleanValue() == true) {
//								if (XSSDefendFilter.judgeTagByRegular(prtValue)) {
//									System.out.println("ERROR parameter:"
//											+ prtName + "=" + prtValue);
//									flag = true;
//									break;
//								}
//							}
//						}
//					}
//				}
//			}
//			if (flag) {
//				return "error";
//			}
//		}
//		return null;
//	}
//}
