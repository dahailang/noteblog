package com.pursuit.noteblog.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 简单字符串类
 * 减少common-lang依赖
 */
public class StringUtil {
    
	// time format
 	public static final String DATE_FORMAT = "yyyy-MM-dd";
 	public static final String DATE_FORMAT_1 = "yyyy/MM/dd";
 	public static final String DATE_FORMAT_2 = "dd/MM/yyyy";
 	public static final String TIME_FORMAT = "HH:mm";
 	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
 	public static final String LONG_DATE_TIME_FORMAT = "yyMMddhhmmssSSS";
 	
 	public static final String GBK_CHARSET ="GBK";
 	public static final String ISO_8859_1 ="ISO-8859-1";
 	
 	// boolean format
 	public static final int BOOLEAN_01 = 0;
 	public static final int BOOLEAN_YESNO = 1;
 	public static final int BOOLEAN_TRUEFLASE = 2;
 	
 	// pad format 
 	public static final int STR_PAD_LEFT = 1;
 	public static final int STR_PAD_RIGHT = 2;
 	public static final int STR_PAD_BOTH = 3;
 	
 	//email address pattern
 	public static final String EMAIL_ADDRESS_PATTERN 
 			= "^(\\w+([-+.]\\w+)*@\\w+([-]\\w+)*.\\w+)$|^(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+).\\w+$";

 	//BSC00015P
 	private static final String beginFormula = "{#";
 	private static final String endFormula = "}";
 	//system root path
 	public static String sysRootPath ="" ;

 	/**
 	 * 中文数字
 	 */
 	private static String[] cnNumber = { "零", "一", "二", "三", "四", "五", "六",
 			"七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八",
 			"十九", "二十" };
 	/**
 	 * 繁体数字
 	 */
 	private static String[] twNumber = { "零", "壹", "二", "三", "四", "五", "六",
 			"七", "八", "九", "十", "十壹", "十二", "十三", "十四", "十五", "十六", "十七", "十八",
 			"十九", "二十" };
 	/**
 	 * 英文数字
 	 */
 	private static String[] usNumber = { "Zeroth", "First", "Second", "Third",
 			"Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth",
 			"Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth",
 			"Sixteenth", "Seventeenth", "Eighteenth", "Nineteenth", "Twentieth" };

 	/** 正则表达式效验 */
 	public static boolean checkRegular(final String str, final String regex) {
 		final Pattern pattern = Pattern.compile(regex);
 		final Matcher matcher = pattern.matcher(str);
 		return matcher.matches();
 	}
    /**
     * 列表转为字符串(用逗号分隔)
     * @param list
     * @param separator
     * @return
     */
    public static String simpleListJoinToStrWithSeparator(List<String> list, String separator) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder finalEmailStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                finalEmailStr.append(separator);
            }
            finalEmailStr.append(list.get(i));
        }
        return finalEmailStr.toString();
    }
 	/**
 	 * 将浮点型数据保留小数点后K位.
 	 * 
 	 * @param numStr
 	 *            the num str
 	 * @param k
 	 *            the k
 	 * @return the string
 	 */
 	public static String decimal(final String numStr, final int k) {
 		final int point = numStr.indexOf(".");
 		System.out.print(numStr + " ii " + point);
 		if ((point == -1) || ((point + k + 1) > numStr.length())) {// 小数点不存在或者本来就满足条件
 			System.out.println("小数点不存在");
 			return numStr;
 		}
 		return numStr.substring(0, point + k + 1);
 	}

 	/** 字符串解码 */
 	public static String decode(final String password) {
 		return password;
 	}

 	/** 字符串编码 */
 	public static String encode(final String password) {
 		return password;
 	}

 	/** 判断两个字符串是否相等 */
 	public static boolean equals(final String value1, final String value2) {
 		if ((value1 != null) && value1.equals(value2)) {
 			return true;
 		}
 		return false;
 	}

 	/** 判断两个字符串是否相等 */
 	public static boolean equalsIgnoreCase(final String value1,
 			final String value2) {
 		if ((value1 != null) && value1.equalsIgnoreCase(value2)) {
 			return true;
 		}
 		return false;
 	}

 	/**
 	 * 将汉字转为拼音 例如：中国->ZHONG GUO.
 	 * 
 	 * @param str
 	 *            the str
 	 * @return the all char spell
 	 */
 	public static String getAllCharSpell(String str) {
 		final StringBuffer resultStringBuffer = new StringBuffer();
 		if (StringUtil.isEmpty(str)) {
 			str = "";
 		}
 		for (int j = 0; j < str.length(); j++) {
 			final char word = str.charAt(j);
 			// 仇 ： chou2 qiu2
 			final String[] pinyinArray = PinyinHelper
 					.toHanyuPinyinStringArray(word);
 			if ((pinyinArray != null) && (pinyinArray.length != 0)) {
 				final String firstPinyin = pinyinArray[0];
 				resultStringBuffer.append(firstPinyin.substring(0,
 						firstPinyin.length() - 1));
 				// resultStringBuffer.append(" ");
 			} else {
 				resultStringBuffer.append(word);
 			}
 		}
 		return resultStringBuffer.toString().toUpperCase();
 	}

 	/**
 	 * 判断某一个字符在字符串中出现的次数
 	 */
 	public static int getCharCount(final String value, final char c) {
 		if (value == null) {
 			return 0;
 		}
 		int count = 0;
 		for (int i = 0; i < value.length(); i++) {
 			if (value.charAt(i) == c) {
 				count++;
 			}
 		}
 		return count;
 	}

 	public static String getChineseNumber(final int i) {
 		if ((i >= 0) && (i < StringUtil.cnNumber.length)) {
 			return StringUtil.cnNumber[i];
 		} else {
 			return "";
 		}
 	}

 	/** 获得字符串变量，如果字符串为空返回null_str */
 	public static String getDateString(final String value, final String null_str) {
 		return value != null ? value.trim() : "0000-00-00";
 	}

 	public static String getEnglishNumber(final int i) {
 		if ((i >= 0) && (i < StringUtil.usNumber.length)) {
 			return StringUtil.usNumber[i];
 		} else {
 			return "";
 		}
 	}

 	/**
 	 * 将字符串每割fragLength长度增加一个<br />
 	 * 
 	 */
 	public static String getFragString(final String originalString,
 			final int fragLength) {
 		final StringBuffer buf = new StringBuffer();
 		int index = 0;
 		while ((index + fragLength) < originalString.length()) {
 			buf.append(originalString.substring(index, index + fragLength))
 					.append("<br />");
 			index += fragLength;
 		}
 		buf.append(originalString.substring(index));
 		return buf.toString();
 	}

 	/**
 	 * 汉字拼音首字母 例如：日本->R.
 	 * 
 	 * @param str
 	 *            the str
 	 * @return the head char spell
 	 */
 	public static String getHeadCharSpell(final String str) {
 		final StringBuffer resultStringBuffer = new StringBuffer();
 		final char word = str.charAt(0);
 		final String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
 		if (pinyinArray != null) {
 			resultStringBuffer.append(pinyinArray[0].charAt(0));
 		} else {
 			resultStringBuffer.append(word);
 		}
 		return resultStringBuffer.toString().toUpperCase();
 	}

 	public static int getLength(final String value) {
 		if (StringUtil.isNull(value)) {
 			return 0;
 		}
 		return value.trim().length();
 	}

 	/** 获得字符串变量(lower)，如果字符串为空返回null_str */
 	public static String getLowerUpperString(final String value) {
 		return StringUtil.getLowerUpperString(value, "");
 	}

 	/** 获得字符串变量(lower)，如果字符串为空返回null_str */
 	public static String getLowerUpperString(final String value,
 			final String null_str) {
 		return value != null ? value.trim().toLowerCase() : null_str;
 	}

 	/**
 	 * 将整数值转换为二位字符的字符串，0->"00", 1->"01",...,99->"99"
 	 */
 	public static String getStandartNumber(final int num) {
 		if (num > 9) {
 			return new Integer(num).toString();
 		}
 		if ((num >= 0) && (num <= 9)) {
 			return "0" + new Integer(num).toString();
 		}
 		return "";
 	}

 	/**
 	 * 字符串转换为字符串数组
 	 * 
 	 * @param str
 	 * @param token
 	 *            (分隔符)
 	 * @return
 	 */
 	public static String[] getStrArray(final String str, final String token) {
 		if (StringUtil.isNull(str)) {
 			return null;
 		}
 		final StringTokenizer st = new StringTokenizer(str, token);
 		final ArrayList<String> strlist = new ArrayList<String>();
 		for (; st.hasMoreElements(); strlist.add(st.nextToken())) {
 		}
 		final String strarr[] = new String[strlist.size()];
 		for (int i = 0; i < strlist.size(); i++) {
 			strarr[i] = strlist.get(i).toString().trim();
 		}
 		return strarr;
 	}

 	/** 从用户输入得到字符串,空则返回"" */
 	public static String getString(final String value) {
 		return value != null ? value.trim() : "";
 	}

 	/** 获得字符串变量，如果字符串为空返回null_str */
 	public static String getString(final String value, final String null_str) {
 		if ((value == null) || StringUtil.equalsIgnoreCase(value, "NULL")) {
 			return null_str;
 		}
 		return value.trim();
 	}

 	/** 获得字符串变量，如果字符串为空返回null_str或者"" */
 	public static String getStringnullandspace(String value,
 			final String null_str) {
 		value = value.trim();
 		if ((value == null) || value.equals("")
 				|| StringUtil.equalsIgnoreCase(value, "NULL")) {
 			return null_str;
 		}
 		return value.trim();
 	}

 	public static String getStringWithoutFirstChar(final long value) {
 		final String v = "" + value;
 		if (v.length() == 0) {
 			return "";
 		}
 		return v.substring(1);
 	}

 	/** 获取字符串长度 */
 	public static int getStrLength(final String str) {
 		final String contentStr = StringUtil.getString(str);
 		int c = 0;
 		for (int i = 0; i < contentStr.length(); i++) {
 			if (contentStr.charAt(i) > 255) {
 				c += 2;
 			} else {
 				c++;
 			}
 		}
 		return c;
 	}

 	/**
 	 * 字符串转换为list
 	 * 
 	 * @param str
 	 * @param token
 	 *            (分隔符)
 	 * @return
 	 */
 	public static List<String> getStrList(final String str, final String token) {
 		if (StringUtil.isNull(str)) {
 			return null;
 		}
 		final StringTokenizer st = new StringTokenizer(str, token);
 		final ArrayList<String> strlist = new ArrayList<String>();
 		for (; st.hasMoreElements(); strlist.add(st.nextToken())) {
 		}
 		return strlist;
 	}

 	public static String getTaiwanNumber(final int i) {
 		if ((i >= 0) && (i < StringUtil.twNumber.length)) {
 			return StringUtil.twNumber[i];
 		} else {
 			return "";
 		}
 	}

 	/** 获得字符串变量(upper)，如果字符串为空返回"" */
 	public static String getUpperString(final String value) {
 		return StringUtil.getUpperString(value, "");
 	}

 	/** 获得字符串变量(upper)，如果字符串为空返回null_str */
 	public static String getUpperString(final String value,
 			final String null_str) {
 		return value != null ? value.trim().toUpperCase() : null_str;
 	}

 	/**
 	 * 获取合法的文件名。 主要是在下载的时候用，当文件名包含回车换行符号时必须做替换。
 	 * 
 	 * @param fileName
 	 * @return
 	 */
 	public static String getValidFileNameInResponse(final String fileName) {
 		String fileName1 = fileName.replaceAll("\r\n", "");
 		fileName1 = fileName1.replaceAll("\r", "");
 		fileName1 = fileName1.replaceAll("\n", "");
 		fileName1 = fileName1.replaceAll(":", "");
 		fileName1 = fileName1.replaceAll("=", "");
 		return fileName1;
 	}

 	/** 替换字符串中的html特殊字符 xbxia */
 	public static String htmlEscape(final String input) {
 		if (input == null) {
 			return null;
 		}
 		final StringBuffer result = new StringBuffer();
 		final int length = input.length();
 		for (int i = 0; i < length; i++) {
 			final char c = input.charAt(i);
 			if (c == '&') {
 				result.append("&amp;");
 			} else if (c == '\"') {
 				result.append("&quot;");
 			} else if (c == '<') {
 				result.append("&lt;");
 			} else if (c == '>') {
 				result.append("&gt;");
 			} else if (c == '\'') {
 				result.append("&#39;");
 			} else if (c == '\\') {
 				result.append("&#092;");
 			} else {
 				result.append(c);
 			}
 		}
 		return result.toString();
 	}

 	/**
 	 * Htmlspecialchars.
 	 * 
 	 * @param str
 	 *            the str
 	 * @return the string
 	 */
 	public static String htmlspecialchars(String str) {
 		if (str == null) {
 			return "";
 		}
 		str = str.replaceAll("&", "&amp;");
 		str = str.replaceAll("<", "&lt;");
 		str = str.replaceAll(">", "&gt;");
 		str = str.replaceAll("\"", "&quot;");
 		System.out.println("str=" + str);
 		return str;
 	}

 	/** 判断一个字符是否是字母 */
 	public static boolean isAlpha(final char c) {
 		return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
 	}

 	/** 判断一个字符是否是数字或者字符 */
 	public static boolean isAlphaOrDigital(final char c) {
 		return StringUtil.isDigital(c) || StringUtil.isAlpha(c);
 	}

 	/** 判断一个字符串是否全部是数字或者字符组成 */
 	public static boolean isAlphaOrDigital(final String value) {
 		if (StringUtil.isNull(value)) {
 			return false;
 		}
 		for (int i = 0; i < value.length(); i++) {
 			if (!StringUtil.isAlphaOrDigital(value.charAt(i))) {
 				return false;
 			}
 		}
 		return true;
 	}

 	/** 判断一个字符串是否全部是字符组成 */
 	public static boolean isAlphaString(final String value) {
 		if (StringUtil.isNull(value)) {
 			return false;
 		}
 		for (int i = 0; i < value.length(); i++) {
 			if (!StringUtil.isAlpha(value.charAt(i))) {
 				return false;
 			}
 		}
 		return true;
 	}

 	/**
 	 * 父串中是否包含子串
 	 * 
 	 * @param orgString父串
 	 * @param subString子串
 	 * @return
 	 */
 	public static boolean isContainedString(final String orgString,
 			final String subString) {
 		if (!StringUtil.isNull(orgString) && !StringUtil.isNull(subString)) {
 			return orgString.indexOf(subString) >= 0;
 		}
 		return false;
 	}

 	/** 判断一个字符是否是数字 */
 	public static boolean isDigital(final char c) {
 		return ((c >= '0') && (c <= '9')) || (c == '.') || (c == '-');
 	}

 	/** 判断一个字符串是否全部是数字组成 */
 	public static boolean isDigitalString(final String value) {
 		if (StringUtil.isNull(value)) {
 			return false;
 		}
 		for (int i = 0; i < value.length(); i++) {
 			if (!StringUtil.isDigital(value.charAt(i))) {
 				return false;
 			}
 		}
 		return true;
 	}

 	/**
 	 * 字符串是否是Double类型.
 	 * 
 	 * @param str
 	 *            the str
 	 * @return true, if is double
 	 */
 	public static boolean isDouble(final String str) {
 		try {
 			Double.valueOf(str);
 			return true;
 		} catch (final NumberFormatException e) {
 			return false;
 		}
 	}

 	/**
 	 * 字符串Map是否为空.
 	 * 
 	 * @param params
 	 *            the params
 	 * @return true, if is empty
 	 */
 	public static boolean isEmpty(final Map<?, ?> params) {
 		if ((params == null) || (params.size() == 0)) {
 			return true;
 		}
 		final Iterator<?> iter = params.keySet().iterator();
 		int count = 0;
 		while (iter.hasNext()) {
 			final String key = (String) iter.next();
 			final String value = (String) params.get(key);
 			if (StringUtil.isEmpty(value)) {
 				count++;
 			}
 		}
 		if (count == params.size()) {
 			return true;
 		}
 		return false;
 	}

 	/**
 	 * Checks if is empty.
 	 * 
 	 * @param obj
 	 *            the obj
 	 * @return true, if is empty
 	 */
 	public static boolean isEmpty(final Object obj) {
 		return !StringUtil.notEmpty(obj);
 	}

 	/**
 	 * 判断一个字符是否是以下字符（"0-9"，".","/","()"）
 	 */
 	private static boolean isFixedStr(final char c) {
 		return ((c >= '0') && (c <= '9')) || (c == '.') || (c == '-')
 				|| (c == '/') || (c == '(') || (c == ')');
 	}

 	/** 判断一个字符串是否全部是由以下字符组成（"0-9"，".","/","-","()"） */
 	public static boolean isFixedString(final String value) {
 		if (StringUtil.isNull(value)) {
 			return false;
 		}
 		if (StringUtil.getCharCount(value, '(') != StringUtil.getCharCount(
 				value, ')')) {
 			return false;
 		}
 		for (int i = 0; i < value.length(); i++) {
 			if (!StringUtil.isFixedStr(value.charAt(i))) {
 				return false;
 			}
 		}
 		return true;
 	}

 	/**
 	 * 字符串是否是Float类型.
 	 * 
 	 * @param str
 	 *            the str
 	 * @return true, if is float
 	 */
 	public static boolean isFloat(final String str) {
 		try {
 			Float.valueOf(str);
 			return true;
 		} catch (final NumberFormatException e) {
 			return false;
 		}
 	}

 	/** 判断一个字符串是否包含数字0-9组成 */
 	public static boolean isHaveInt(final String value) {
 		if (StringUtil.isNull(value)) {
 			return true;
 		}
 		if ((value.indexOf("0") > -1) || (value.indexOf("1") > -1)
 				|| (value.indexOf("2") > -1) || (value.indexOf("3") > -1)
 				|| (value.indexOf("4") > -1) || (value.indexOf("5") > -1)
 				|| (value.indexOf("6") > -1) || (value.indexOf("7") > -1)
 				|| (value.indexOf("8") > -1) || (value.indexOf("9") > -1)) {
 			return true;
 		}
 		return false;
 	}

 	/** 判断一个字符是否是数字0-9 */
 	public static boolean isInt(final char c) {
 		return ((c >= '0') && (c <= '9'));
 	}

 	/**
 	 * 字符串是否是Integer类型.
 	 * 
 	 * @param str
 	 *            the str
 	 * @return true, if is integer
 	 */
 	public static boolean isInteger(final String str) {
 		try {
 			Integer.valueOf(str);
 			return true;
 		} catch (final NumberFormatException e) {
 			return false;
 		}
 	}

 	/** 判断一个字符串是否全部是数字[0-9]或者字符组成 */
 	public static boolean isIntOrAlpha(final String value) {
 		if (StringUtil.isNull(value)) {
 			return false;
 		}
 		for (int i = 0; i < value.length(); i++) {
 			if (!StringUtil.isInt(value.charAt(i))
 					&& !StringUtil.isAlpha(value.charAt(i))) {
 				return false;
 			}
 		}
 		return true;
 	}

 	/** 判断一个字符串是否全部是数字0-9组成 */
 	public static boolean isIntString(final String value) {
 		if (StringUtil.isNull(value)) {
 			return false;
 		}
 		for (int i = 0; i < value.length(); i++) {
 			if (!StringUtil.isInt(value.charAt(i))) {
 				return false;
 			}
 		}
 		return true;
 	}

 	/**
 	 * 字符串是否是Long类型.
 	 * 
 	 * @param str
 	 *            the str
 	 * @return true, if is long
 	 */
 	public static boolean isLong(final String str) {
 		try {
 			Long.valueOf(str);
 			return true;
 		} catch (final NumberFormatException e) {
 			return false;
 		}
 	}

 	/** 判断字符串数组是否为空：当里面的值全为null或"",返回true；否则返回false */
 	public static boolean isNull(final String[] values) {
 		if ((values == null) || (values.length == 0)) {
 			return true;
 		}
 		int nullNum = 0;
 		for (int i = 0; i < values.length; i++) {
 			final String val =  values[i];
 			if (StringUtil.isNull(val)) {
 				nullNum++;
 			}
 		}
 		return nullNum == values.length;
 	}

 	/** 判断str是否以“1.”、“12.”。。。“99.” 等开始。 */
 	public static boolean isStringStartWithDigitalDot(final String str) {
 		final String first = str.substring(0, 1);
 		final String second = str.substring(1, 2);
 		final String three = str.substring(2, 3);
 		if (StringUtil.isDigitalString(first) && StringUtil.equals(second, ".")) {
 			return true;
 		}
 		if (StringUtil.isDigitalString(first)
 				&& StringUtil.isDigitalString(second)
 				&& StringUtil.equals(three, ".")) {
 			return true;
 		}
 		return false;
 	}

 	/** 替换字符串中的javascript特殊字符 xbxia */
 	public static String javascriptEscape(final String input) {
 		if (input == null) {
 			return null;
 		}
 		final StringBuffer result = new StringBuffer();
 		final int length = input.length();
 		for (int i = 0; i < length; i++) {
 			final char c = input.charAt(i);
 			if (c == '\"') {
 				result.append("\\\"");
 			} else if (c == '\'') {
 				result.append("\\'");
 			} else if (c == '\\') {
 				result.append("\\\\");
 			} else {
 				result.append(c);
 			}
 		}
 		return result.toString();
 	}

 	/**
 	 * 如果字符穿中有汉字,判断此汉字是否在gb2312字符集内，西文字符做判断
 	 * 
 	 * @param str
 	 * @return
 	 */
 	public static boolean judgeStrIsGb2312(final String str) {
 		final int[] errCharIndex = new int[1];
 		return StringUtil.judgeStrIsGb2312(str, errCharIndex);
 	}

 	public static boolean judgeStrIsGb2312(final String str,
 			final int[] errCharIndex) {
 		if (str == null) {
 			errCharIndex[0] = -1;
 			return true;
 		}
 		try {
 			for (int i = 0; i < str.length(); i++) {
 				final String chara = str.substring(i, i + 1);
 				final byte[] bts = chara.getBytes("GBK");
 				if (bts.length == 2) {
 					final String s1 = (Integer.toHexString(bts[0]).length() > 2 ? Integer
 							.toHexString(bts[0]).substring(6) : Integer
 							.toHexString(bts[0]));
 					final String s2 = (Integer.toHexString(bts[1]).length() > 2 ? Integer
 							.toHexString(bts[1]).substring(6) : Integer
 							.toHexString(bts[1]));
 					// System.out.println(chara+"==="+s1+s2);
 					final int i1 = Integer.valueOf(s1, 16).intValue();
 					if ((i1 < 0xb0) || (i1 > 0xf7)) {
 						errCharIndex[0] = i;
 						return false;
 					}
 					final int i2 = Integer.valueOf(s2, 16).intValue();
 					if ((i2 <= 0xa0) || (i2 >= 0xff)) {
 						errCharIndex[0] = i;
 						return false;
 					}
 				}
 			}
 		} catch (final Exception e) {
 			return true;
 		}
 		errCharIndex[0] = -1;
 		return true;
 	}

 	/**
 	 * Not empty.
 	 * 
 	 * @param obj
 	 *            the obj
 	 * @return true, if successful
 	 */
 	public static boolean notEmpty(final Object obj) {
 		return (obj != null)
 				&& (obj.toString().length() > 0)
 				&& !obj.toString().replaceAll("\"", "").toLowerCase()
 						.equals("null") ? true : false;
 	}

 	/**
 	 * Not empty.
 	 * 
 	 * @param value
 	 *            the value
 	 * @return true, if successful
 	 */
 	public static boolean notEmpty(final String value) {
 		return (value != null)
 				&& !value.replaceAll("\"", "").toLowerCase().equals("null")
 				&& (value.length() > 0) ? true : false;
 	}

 	public static String replace(final String value, final char c) {
 		if (StringUtil.isNull(value)) {
 			return "";
 		}
 		final StringBuffer buff = new StringBuffer();
 		for (int i = 0; i < value.length(); i++) {
 			if (value.charAt(i) != c) {
 				buff.append(value.charAt(i));
 			}
 		}
 		return buff.toString();
 	}

 	/**
 	 * 用新的字符串替换原字符串的第几个字符。如把"abddddba"的第4位换成f，得到"abddfdba"
 	 * 方法为replace("abddddba", 4, "f")，
 	 * 
 	 * @param oldStr
 	 *            原字符串
 	 * @param index
 	 *            替换的位置，从0开始计数
 	 * @param newValue
 	 *            新的字符串
 	 * @return
 	 */
 	public static String replace(final String oldStr, final int index,
 			final String newValue) {
 		if (StringUtil.isNull(oldStr)) {
 			return "";
 		}
 		if (oldStr.length() < (index + 1)) {
 			return oldStr;
 		}
 		if (index < 0) {
 			return oldStr;
 		}
 		final StringBuffer buff = new StringBuffer();
 		for (int i = 0; i < index; i++) {
 			buff.append(oldStr.charAt(i));
 		}
 		buff.append(newValue);
 		for (int i = index + 1; i < oldStr.length(); i++) {
 			buff.append(oldStr.charAt(i));
 		}
 		return buff.toString();
 	}

 	/**
 	 * 把字符串org中去掉字符串filter中出现的所有字符
 	 */
 	public static String replace(String org, final String filter) {
 		if (StringUtil.isNull(org)) {
 			return "";
 		}
 		final StringBuffer buff = new StringBuffer();
 		for (int i = 0; (filter != null) && (i < filter.length()); i++) {
 			org = StringUtil.replace(org, filter.charAt(i));
 		}
 		buff.append(org);
 		return buff.toString();
 	}

 	/** 从一行line获得字符串的数组，没有数据返回null */
 	public static String[] splitString(final String line, final char deli) {
 		if (StringUtil.isNull(line)) {
 			return new String[] {};
 		}
 		final String[] array_tokens = line.split("[" + deli + "]");
 		final int count = StringUtil.getCharCount(line, deli);
 		final String[] tokens = new String[count + 1];
 		for (int i = 0; i < (count + 1); i++) {
 			if ((array_tokens != null) && (i < array_tokens.length)) {
 				if (!StringUtil.isNull(array_tokens[i])) {
 					tokens[i] = array_tokens[i].trim().toUpperCase();
 				} else {
 					tokens[i] = "";
 				}
 			} else {
 				tokens[i] = "";
 			}
 		}
 		return tokens;
 	}

 	/**
 	 * 以某字符开头.
 	 * 
 	 * @param source
 	 *            源字符串
 	 * @param str
 	 *            开始字符串
 	 * @return true 如果源字符串以指定的开始字符串开始，返回true,否则false
 	 */
 	public static boolean startWith(final String source, final String str) {
 		final String temp = source + "";
 		return temp.trim().toLowerCase().startsWith(str.toLowerCase());
 	}

 	/**
 	 * 以某些字符开头.
 	 * 
 	 * @param source
 	 *            the source
 	 * @param strs
 	 *            the strs
 	 * @return true, if successful
 	 */
 	public static boolean startWith(final String source, final String[] strs) {
 		for (int i = 0; i < strs.length; i++) {
 			final String str = strs[i];
 			if (StringUtil.startWith(source, str)) {
 				return true;
 			}
 		}
 		return false;
 	}

 	/**
 	 * 截取最后一个出现该字符的字符串段.
 	 * 
 	 * @param source
 	 *            the source
 	 * @param str
 	 *            the str
 	 * @return the string
 	 */
 	public static String subStringByLastFlag(final String source,
 			final String str) {
 		if (source == null) {
 			return source;
 		}
 		final int index = source.lastIndexOf(str);
 		if (index != -1) {
 			return source.substring(index + str.length());
 		}
 		return source;
 	}
 	
 	/**
 	 * return null input string as emptry string
 	 * @param s input String
 	 * @return String
 	 */
 	public static String null2Str(String s){
 		return null2Str(s, "");
 	}
 	
 	/**
 	 * return null input string as emptry string
 	 * @param s input String
 	 * @param def default String if input s is null
 	 * @return String
 	 */	
 	public static String null2Str(String s, String def){
 		if(isNull(s))
 			return def;
 		else 
 			return s.trim();
 	}		

 	/**
 	 * check if the input string is null
 	 * @param s String
 	 * @return boolean
 	 */			
 	public static boolean isNull(String s){
 		if(s == null || s.trim().equals(""))
 			return true;
 		else
 			return false;
 	}

 	/**
 	 * return 0 if input string is null or integer of input string
 	 * @param s input String
 	 * @return int
 	 */	
 	public static int str2Int(String s){
 		int i=0;
 		try{
 			// if the number format is "2,000" , clean the ","
 			i = Integer.parseInt(strReplace(",","",null2Str(s, "0")));
 		}catch(Exception e){
 			i = 0;
 		}
 		return i;
 	}

 	/**
 	 * return 0 if input string is null or integer of input string
 	 * @param s input String
 	 * @return long
 	 */	
 	public static long str2Long(String s){
 		long i=0;
 		try{
 			//if the number format is "2,000" , clean the ","
 			i = Long.parseLong(strReplace(",","",null2Str(s, "0")));
 		}catch(Exception e){
 			i = 0;
 		}
 		return i;
 	}

 	/**
 	 * return String of input integer
 	 * @param i integer
 	 * @return String
 	 */		
 	public static String int2Str(int i){
 		return int2Str(i, false);
 	}

 	/**
 	 * return String of input integer
 	 * @param i integer
 	 * @param bShowZero boolean
 	 * @return String
 	 */			
 	public static String int2Str(int i, boolean bShowZero){
 		return int2Str(i, bShowZero, false);
 	}
 	/**
 	 * return String of input integer
 	 * @param i integer
 	 * @param bShowZero boolean
 	 * @param bShowGroup boolean
 	 * @return String
 	 */			
 	public static String int2Str(int i, boolean bShowZero, boolean bShowGroup){
 		if(!bShowZero && i==0) {
 			return "";
 		} else {
 			NumberFormat nf = NumberFormat.getInstance();
 			nf.setGroupingUsed(bShowGroup);
 			nf.setMinimumIntegerDigits(1);
 			nf.setMaximumFractionDigits(0);
 			nf.setMinimumFractionDigits(0);
 			return nf.format(i);
 		}
 			
 	}
 	
 	/**
 	 * return String of input double
 	 * @param d
 	 * @return
 	 */
 	public static String double2Str(double d) {
 		return double2Str(d, 2);
 	}
 	
 	/**
 	 * return String of input double with decimal points
 	 * @param d
 	 * @param decimalPoints
 	 * @return
 	 */
 	public static String double2Str(double d, int decimalPoints) {
 		NumberFormat nf = NumberFormat.getInstance();
 		nf.setGroupingUsed(true);
 		nf.setMinimumIntegerDigits(1);
 		nf.setMaximumFractionDigits(decimalPoints);
 		nf.setMinimumFractionDigits(decimalPoints);
 		return nf.format(d);
 	}
 	
 	/**
 	 * 按小数位四舍五入
 	 * @param d
 	 * @return
 	 */
 	public static Double doubleRound(double d,int decimalPoints){
         double p= Math.pow(10, decimalPoints); 
         return new Double(Math.round( d * p ) / p);
 	}
 	
 	/**
 	 * return double of input string value (can input format is "2,000" ) 直接截取固定小数位,未四舍五入
 	 * @param s
 	 * @return
 	 */
 	public static double str2Double(String s) {
 		NumberFormat nf = NumberFormat.getInstance();
 		nf.setGroupingUsed(true);
 		try {
 			//if the number format is "2,000" , clean the ","
 			return nf.parse(strReplace(",","",null2Str(s, "0"))).doubleValue();
 		} catch (ParseException e)
 		{
 			return 0;
 		}
 	}

 	/**
 	 * return Date of input string
 	 * @param s String
 	 * @return String
 	 */			
 	public static java.util.Date str2Date(String s){
 		return str2Date(s, DATE_FORMAT);
 	}
 		
 	/**
 	 * return Date of input string
 	 * @param s String
 	 * @param format String
 	 * @return String
 	 */			
 	public static java.util.Date str2Date(String s, String format){
 		java.util.Date dRet = null;
 		SimpleDateFormat sdf = null;
 		try{
 			sdf = new SimpleDateFormat(format);
 			dRet = sdf.parse(s);
 			return dRet ;
 		}catch(ParseException pe){
 		}
 		try{
 			sdf = new SimpleDateFormat(DATE_FORMAT);
 			dRet = sdf.parse(s);
 			return dRet ;
 		}catch(ParseException pe){
 		}
 		try{
 			sdf = new SimpleDateFormat(DATE_FORMAT_1);
 			dRet = sdf.parse(s);
 			return dRet ;
 		}catch(ParseException pe){
 		}
 		try{
 			sdf = new SimpleDateFormat(DATE_FORMAT_2);
 			dRet = sdf.parse(s);
 			return dRet ;
 		}catch(ParseException pe){
 		}
 		
 		return dRet;
 	}

 	/**
 	 * return String of input date
 	 * @param d Date
 	 * @return String
 	 */			
 	public static String date2Str(java.util.Date d){
 		return date2Str(d, DATE_FORMAT);
 	}
 	
 	/**
 	 * return String of input date
 	 * @param d Date
 	 * @param format String
 	 * @return String
 	 */			
 	public static String date2Str(java.util.Date d, String format){
 		if(d == null)
 			return "";
 			
 		SimpleDateFormat sdf = new SimpleDateFormat(format);
 		return sdf.format(d);
 	}
 	
 	/**
 	 * return String of input date
 	 * @param d Date
 	 * @return String
 	 */			
 	public static String time2Str(java.util.Date d){
 		return date2Str(d, TIME_FORMAT);
 	}
 	
 	/**
 	 * return String of input date
 	 * @param d Date
 	 * @param format String
 	 * @return String
 	 */			
 	public static String datetime2Str(java.util.Date d){
 		if(d == null) return "";
 			
 		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
 		return sdf.format(d);
 	}
 	
 	/**
 	 * 将String类型日期转化成java.sql.Date类型"2003-01-01"格式
 	 * @param str String
 	 * @param format String
 	 * @return Date
 	 */
 	public static java.sql.Date str2SqlDate(String str,String format) {
 		if (str==null||format==null) {
 			return null;
 		}
 		return new java.sql.Date(str2Date(str,format).getTime());
 	}
 	
 	
 	/**
 	 * 将String类型日期转化成java.sql.Date类型"2003-01-01"格式
 	 * @param str String
 	 * @return Date
 	 */
 	public static java.sql.Date str2SqlDate(String str) {
 		
 		if (str==null) {
 			return null;
 		}
 		return new java.sql.Date(str2Date(str).getTime());
 	}
 	
 	/**
 	 * 将String 类型转化为boolean类型,接受"true" ,"false" , "0" , "1","Y","N","YES","NO"等类型,其他形式为false返回
 	 * @param str
 	 * @return
 	 */
 	public static boolean str2boolean(String str){
 		if (str == null) {
 			return false;
 		} else if (str.equalsIgnoreCase("true") || str.equals("1") || str.equalsIgnoreCase("Y") || str.equalsIgnoreCase("YES")) {
 			return true ;
 		} else if (str.equalsIgnoreCase("false") || str.equals("0") || str.equalsIgnoreCase("N") || str.equalsIgnoreCase("NO")) {
 			return false;
 		} else {
 			return false;
 		}
 	}
 	
 	/**
 	 * 将boolean类型转化为String 类型输出,格式有"0","1","Y","N","T","F"等类型,默认使用"0","1"
 	 * @param str
 	 * @return
 	 */
 	public static String boolean2Str(boolean bl , int format){
 		switch (format) {
 			case BOOLEAN_01 : {
 				return bl ? "1":"0";
 			}
 			case BOOLEAN_YESNO : {
 				return bl ? "Y":"N";
 			}
 			case BOOLEAN_TRUEFLASE :{
 				return bl ? "T":"F";
 			}
 			default :
 				return "0";
 		}
 	}
 	
 	/**
 	 * 将boolean类型转化为String 类型输出,默认使用"0","1"
 	 * @param str
 	 * @return
 	 */
 	public static String boolean2Str(boolean bl){
 		return boolean2Str(bl,BOOLEAN_01);
 	}
 	
 	/**
 	 * 将java.util.Date日期转化成java.sql.Date类型
 	 * @param Date
 	 * @return 格式化后的java.sql.Date
 	 */
 	public static java.sql.Date toSqlDate(java.util.Date date) {
 		 	
 		if (date==null) {
 			return null;
 		}
 		return new java.sql.Date(date.getTime());
 	}
 	
 	
 	/**
 	 * 将java.util.Date日期转化成java.sql.Timestamp类型
 	 * @param Date
 	 * @return 格式化后的java.sql.Timestamp
 	 */
 	public static java.sql.Timestamp toSqlTimestamp(java.util.Date date) {
 	 	
 		if (date==null) {
 			return null;
 		}
 		return new java.sql.Timestamp(date.getTime());
 	}
 		
 	/**
 	 * 将日历转化为日期
      * @param calendar Calendar
      * @return Date
      */
 	public static java.util.Date converToDate(java.util.Calendar calendar){
 		return Calendar.getInstance().getTime();
 	}

 	/**
 	 * 将日期转化为日历
 	 * @param date Date
 	 * @return Calendar
 	 */
 	public static java.util.Calendar converToCalendar(java.util.Date date){
 		Calendar calendar = Calendar.getInstance();
 		calendar.setTime(date);
 		return calendar;
 	}

 	/**
 	 * 求得从某天开始，过了几年几月几日几时几分几秒后，日期是多少
 	 * 几年几月几日几时几分几秒可以为负数
 	 * @param date Date
 	 * @param year int
 	 * @param month int
 	 * @param day int
 	 * @param hour int
 	 * @param min int
 	 * @param sec int
 	 * @return Date
 	 */
 	public static java.util.Date modifyDate(java.util.Date date,int year ,int month,int day,int hour,int min,int sec){
 		Calendar cal = Calendar.getInstance();
 		cal.setTime(date);
 		cal.add(Calendar.YEAR,year);
 		cal.add(Calendar.MONTH,month);
 		cal.add(Calendar.DATE,day);
 		cal.add(Calendar.HOUR,hour);
 		cal.add(Calendar.MINUTE,min);
 		cal.add(Calendar.SECOND,sec);

 		return cal.getTime();

 	}


 	/**
 	 * 取得当前日期时间
 	 * 1:year
 	 * 2:month
 	 * 3:date
 	 * 4:day
 	 */
 	public static int getCurTime(int i) {
 		if (i == 1) {
 		  return java.util.Calendar.getInstance().get(Calendar.YEAR);
 		}
 		else if (i == 2) {
 		  return java.util.Calendar.getInstance().get(Calendar.MONTH) + 1;
 		}
 		else if (i == 3) {
 		  return java.util.Calendar.getInstance().get(Calendar.DATE);
 		}
 		else if (i == 4) {
 		  int temp =java.util.Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
 		  if(temp ==1)
 		  {
 			temp =7;
 		  }
 		  else
 		  {
 		    temp--;
 		  }
 		  return temp;
 		}
 		
 		return 0;

 	}
 	
 	
 	/**
 	 * Repeat string with multiplier times.
 	 * @param input
 	 * @param multiplier
 	 * @return
 	 */
 	public static String strRepeat(String input, int multiplier){
 		StringBuffer sb = new StringBuffer("");
 		if (isNull(input)) {
 			input = " ";
 		}
 		
 		for (int i=0; i<multiplier; i++) {
 			sb.append(input);
 		}
 		return sb.toString();
 	}
 	
 	/**
 	 * return string with padString and length . defualt padstring in left .
 	 * @param input
 	 * @param length
 	 * @param padString
 	 * @return
 	 */
 	public static String strPad(String input, int length, String padString) {
 		
 		return strPad(input, length, padString, STR_PAD_LEFT);
 	}
 	
 	/**
 	 * return string with padString using length .
 	 * @param input
 	 * @param length
 	 * @param padString
 	 * @param padType
 	 * @return
 	 */
 	public static String strPad(String input, int length, String padString, int padType) {
 		int multiplier = 0;
 		String tmpStr = "";
 		String outStr = "";
 		int pos = 0;
 		
 		input = null2Str(input, "");
 		padString = null2Str(padString, " ");
 		
 		if (input.length() >= length) return input;
 		
 		multiplier = (int)Math.ceil((double)length / (double)padString.length());
 		tmpStr = strRepeat(padString, multiplier);

 		if (padType == STR_PAD_RIGHT) {
 			pos = length - input.length() ;
 		} else if (padType == STR_PAD_BOTH) {
 			pos = ((length - input.length()) / 2);
 		} else {
 			pos = 0;
 		}

 		if (pos > 0) {
 			outStr = tmpStr.substring(0, pos);
 		}
 		outStr += input;
 		outStr += tmpStr.substring(pos + input.length(), length);

 		return outStr;
 	}
 	
 	/**
 	* If Java 1.4 is unavailable, the following technique may be used.
 	*
 	* @param aInput is the original String which may contain substring aOldPattern
 	* @param aOldPattern is the non-empty substring which is to be replaced
 	* @param aNewPattern is the replacement for aOldPattern
 	*/
 	public static String strReplace(
 	  final String aOldPattern,
 	  final String aNewPattern,
 	  final String aInput)
 	{
 	   if ( aOldPattern == null || aOldPattern.equals("") ) {
 		  throw new IllegalArgumentException("Old pattern must have content.");
 	   }
 	   
 	   if (aInput == null || aInput.equals("")) {
 	   	  return aInput ;
 	   }
 		
 	   if (aNewPattern == null){
 		  throw new IllegalArgumentException("New pattern must not null.");
 	   }
 	   
 	   if(aNewPattern.equals(aOldPattern))
 	   		return aInput;
 	   
 	   final StringBuffer result = new StringBuffer();
 	   //startIdx and idxOld delimit various chunks of aInput; these
 	   //chunks always end where aOldPattern begins
 	   int startIdx = 0;
 	   int idxOld = 0;
 	   while ((idxOld = aInput.indexOf(aOldPattern, startIdx)) >= 0) {
 		 //grab a part of aInput which does not include aOldPattern
 		 result.append( aInput.substring(startIdx, idxOld) );
 		 //add aNewPattern to take place of aOldPattern
 		 result.append( aNewPattern );
 		 //reset the startIdx to just after the current match, to see
 		 //if there are any further matches
 		 startIdx = idxOld + aOldPattern.length();
 	   }
 	   //the final chunk will go to the end of aInput
 	   result.append( aInput.substring(startIdx) );
 	   return result.toString();
 	}
 	/**
 	 * return string show file size .
 	 * @param size
 	 * @return
 	 */
 	public static String formatFileSize(long size)
 	{
 		if (size > 0 && size < 1024) {
 			return Long.toString(size) + " B";
 		} else if (size >= 1024 && size < 1024 * 1024) {
 			return StringUtil.double2Str((double)size/1024) + " KB";
 		} else if (size >= 1024 * 1024) {
 			return StringUtil.double2Str((double)size/1024/1024) + " MB";
 		} else {
 			return "0 B";
 		}
 	}
 	
 	/**
 	 * source like:  command1=value1;command2=value2 ...
 	 * get the value n from command n string.
 	 * @param source
 	 * @param command
 	 * @param delimi
 	 * @return valuen
 	 */
 	public static String getCommand(String source , String command , String delimi){
 		String result ="";
 		StringTokenizer st = new StringTokenizer(source , delimi , false);
 		while (st.hasMoreTokens()){
 			StringTokenizer stcomm = new StringTokenizer(null2Str(st.nextToken()),"=",false);
 			while (stcomm.hasMoreTokens()){
 				if (null2Str(stcomm.nextToken()).equalsIgnoreCase(command)) {
 					result = null2Str(stcomm.nextToken());
 					return result;
 				}
 			}
 		}
 		return result;
 	}
 	
 	/**
 	 * change the path string use the os native string.
 	 * @param path
 	 * @return right path string of system.
 	 */
 	public static String pathName(String path){
 		String pathName = null ;
 		String OSName = System.getProperty("os.name");
 		if (OSName.toLowerCase().indexOf("window") > 0){
 			pathName = strReplace("\\","/",path);
 		} else {
 			pathName = strReplace("/","\\",path);
 		}
 		return pathName ;
 	}
 	
 	/**
 	 * return string of year , using 'YY' format
 	 * @param year
 	 * @return
 	 */
 	public static String strYear(int year){
 		String str=String.valueOf(year);
 		if (str.length()==4)
 			str=str.substring(2);
 		return str;
 	}
 	
 	/**
 	 * return String of month , using 'MM' format . 
 	 * @param month
 	 * @return
 	 */
 	public static String strMon(int month){
 		return strPad(Integer.toString(month) ,2 , "0",STR_PAD_RIGHT);
 	}
 	
 	/**
 	 * return String Serial NO using left padding with '0'.  
 	 * @param serialno
 	 * @param length
 	 * @return
 	 */
 	public static String strSerial(int serialno , int length){
 		return strPad(Integer.toString(serialno),length,"0",STR_PAD_RIGHT);
 	}
 	
 	/**
 	 * 
 	 * @param str
 	 * @return
 	 */
 	public static String strRight(String str){
 		str="000"+str;
 		int len=str.length();
 		return str.substring(len-3);
 	}
 	
 	/**
 	 * Validate the input string which is digit or char. 
 	 * 
 	 * @param s, input string
 	 * @return true for digit, false for char
 	 * @version 1.0 shine new 
 	 */
 	public static boolean isDigit(String s) {
 		if(s == null) return false;
 		return Pattern.matches("^\\d+$", s);
 	}
 	
 	/**
 	 * Count the date2 from date1 with second depart.
 	 * @param date1
 	 * @param date2
 	 * @return
 	 */
 	public static long getQuotSecond(Date date1 , Date date2){ 
 		long quot = 0; 
 		quot = date1.getTime() - date2.getTime(); 
 		quot = quot / 1000; 
 		return quot;
 	}
 	
 	/**
 	 * Count the date2 from date1 with minute depart.
 	 * @param date1
 	 * @param date2
 	 * @return
 	 */
 	public static long getQuotMinute(Date date1 , Date date2){ 
 		long quot = 0; 
 		quot = date1.getTime() - date2.getTime(); 
 		quot = quot / 1000 / 60 ; 
 		return quot;
 	}
 	
 	/**
 	 * Count the date2 from date1 with hour depart.
 	 * @param date1
 	 * @param date2
 	 * @return
 	 */
 	public static long getQuotHour(Date date1 , Date date2){ 
 		long quot = 0; 
 		quot = date1.getTime() - date2.getTime(); 
 		quot = quot / 1000 / 60 / 60 ; 
 		return quot;
 	}
 	
 	/**
 	 * Count the date2 from date1 with days depart.
 	 * @param date1
 	 * @param date2
 	 * @return
 	 */
 	public static long getQuot(Date date1 , Date date2){ 
 		long quot = 0; 
 		quot = date1.getTime() - date2.getTime(); 
 		quot = quot / 1000 / 60 / 60 / 24; 
 		return quot;
 	} 
 	
 	/**
 	 * Count the datetime2 from datetime1 with days depart.
 	 * @param time1
 	 * @param time2
 	 * @return
 	 */
 	public static long getQuot(String time1, String time2){ 
 		long quot = 0; 
 		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd"); 
 		try { 
 			Date date1 = ft.parse( time1 ); 
 			Date date2 = ft.parse( time2 ); 
 			quot = date1.getTime() - date2.getTime(); 
 			quot = quot / 1000 / 60 / 60 / 24; 
 		} catch (ParseException e) { 
 			e.printStackTrace(); 
 		} 
 		return quot; 
 	} 
 	
 	/**
 	 * Count the datetime1 from now with days depart.
 	 * @param time1
 	 * @return
 	 */
 	public static long getQuot(String time1){ 
 		long quot = 0; 
 		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd"); 
 		try { 
 			Date date = ft.parse( time1 ); 			
 			quot = new Date().getTime() - date.getTime(); 
 			quot = quot / 1000 / 60 / 60 / 24; 
 		} catch (ParseException e) { 
 			e.printStackTrace(); 
 		} 
 		return quot; 
 	}
 	
 	/**
 	 * return the SQLCode from the SQL error messag.
 	 * When input null, it will return "".
 	 * e.g: input "[SQL0104] Token SELEC was not valid.",
 	 * 		returns "SQL0104".
 	 * 
 	 * @param errMsg, e.g: SQLException.getMessage().
 	 * @return String, e.g: "SQL0104"
 	 */
 	public static String getSQLErrCode(String errMsg) {
 		if(errMsg == null)	return "";
 		
 		int startIndex = errMsg.indexOf("[");
 		if(startIndex == -1)	return "";
 		
 		int endIndex = errMsg.indexOf("]");
 		if(endIndex == -1)	return "";
 		
 		if(++startIndex > endIndex)	return "";
 		return errMsg.substring(startIndex, endIndex).trim();
 	}
 	
 	/**
 	 * return the SQL Message from the SQL error messag.
 	 * When input null, it will return "".
 	 * e.g: input "[SQL0104] Token SELEC was not valid.",
 	 * 		returns "Token SELEC was not valid.".
 	 * 
 	 * @param errMsg, e.g: SQLException.getMessage().
 	 * @return String, e.g: "Token SELEC was not valid."
 	 */
 	public static String getSQLErrMessage(String errMsg) {
 		if(errMsg == null)	return "";
 		
 		Pattern pattern = Pattern.compile("\\[.*\\]");
 		Matcher matcher = pattern.matcher(errMsg);
 		return matcher.replaceFirst("").trim();	
 	}
 	
 	public static boolean getValidIdentityNo ( String xidentityno ){
 		if(xidentityno.length()!=18){
 			throw new IllegalArgumentException("身份证长度不符");
 		}
 		char A[] = ((xidentityno.toUpperCase()).trim()).toCharArray();
 		int W[]={7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
 		int s = 0;
 		int m = 0;
 		int b = 0;		
 		for(int i=1;i<=17;i++){
 			b = new Integer(A[i-1]).intValue();
 			if(b<48 || b>57) {
 				throw new IllegalArgumentException("身份证号码必需为数字");
 			}
 			m = str2Int(String.valueOf(A[i-1]));
 			s = s + m*W[i-1];
 		}
 		int y = s % 11;
 		String v = "";
 		if(y==0) v = "1";
 		if(y==1) v = "0";
 		if(y==2) v = "X";
 		if(y>=3 && y<=10) v = new Integer(12 - y).toString();
 		String idno = xidentityno.substring(0,17) + v;
 		if(!idno.equals(xidentityno)){
 			throw new IllegalArgumentException("身份证号码不对!");
 		}		
 		return true;
 	}
 	
 	/**
 	 * 验证身份证（抄getValidIdentityNo 方法），区别：根据错误类型返回不同的值，而不是直接抛出错误
 	 * @param xidentityno
 	 * @return
 	 * @version 1.1 habe
 	 */
 	public static int getValidIdentityNoReturnType ( String xidentityno ){
 		if(xidentityno.length()!=18){
 			return 1;//("身份证长度不符");
 		}
 		char A[] = ((xidentityno.toUpperCase()).trim()).toCharArray();
 		int W[]={7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
 		int s = 0;
 		int m = 0;
 		int b = 0;		
 		for(int i=1;i<=17;i++){
 			b = new Integer(A[i-1]).intValue();
 			if(b<48 || b>57) {
 				return  2;//("身份证号码必需为数字");
 			}
 			m = str2Int(String.valueOf(A[i-1]));
 			s = s + m*W[i-1];
 		}
 		int y = s % 11;
 		String v = "";
 		if(y==0) v = "1";
 		if(y==1) v = "0";
 		if(y==2) v = "X";
 		if(y>=3 && y<=10) v = new Integer(12 - y).toString();
 		String idno = xidentityno.substring(0,17) + v;
 		if(!idno.equals(xidentityno)){
 			return  3;//("身份证号码不对!");
 		}		
 		return 0;
 	}
 	
 	/**
 	 * 验证email格式
 	 * @param email
 	 * @return 是否符合格式
 	 */
 	public static boolean verifyEmail(String email) {
 		if (email == null || "".equals(email)) {
 			return false;
 		}
 		if (email.indexOf('@') < 1) {
 			return false;
 		}
 		return Pattern.matches(EMAIL_ADDRESS_PATTERN,email);
 	}
 	
 	
 	/**
 	 * 将java.sql.Date日期转化成java.util.Date类型
 	 * @param Date
 	 * @return 格式化后的java.sql.Date
 	 */
 	public static java.util.Date toUtilDate(java.sql.Date date) {
 		 	
 		if (date==null) {
 			return null;
 		}
 		return new java.util.Date(date.getTime());
 	}
 	
 	
 	/**
 	 * 转换字符编码
 	 * @param str
 	 * @param beginCharset
 	 * @param endCharset
 	 * @return
 	 */
 	public static String changeCharset(String str,String beginCharset,String endCharset) 
 	{
 		try {
 			return new String(str.getBytes(beginCharset),endCharset);
 		} catch (UnsupportedEncodingException e) {
 			return str;
 		}
 	}
 	
 	/**
 	 * 判断sourceStr 是否符合 patern规则.
 	 * 例如:  判断是否输入的是否只有数字和字母 pattern = /[A-Za-z0-9]/
 	 * @param pattern
 	 * @param sourceStr
 	 * @return
 	 */
 	public static boolean testStr(String pattern , String sourceStr ){
 		return Pattern.matches(pattern,sourceStr);
 	}
 	
 	/**
 	 * 字符串替换
 	 * @param regex
 	 * @param replacement
 	 * @param inputChars
 	 * @return
 	 */
 	public static String replaceAll(String regex,String replacement,String inputString){
 		if(inputString==null)
 			throw new RuntimeException(" 'inputString' can not be null! ");
 		return inputString.replaceAll(regex,replacement);
 	}
 	
 	/**
 	 * 获取特定的日期, 如无定义中的内容，则返回今天
 	 * LAST_MONTHN_FIRST_DATE = 上一个月首日
 	 * LAST_MONTHN_LAST_DATE = 上一个月最后一日
 	 * @param defined
 	 * @return
 	 */
 	public static java.util.Date getDateByDefine(String define){
 		// defined : LAST_MONTHN_FIRST_DATE
 		// defined : LAST_MONTHN_LAST_DATE
 		java.util.Date date = new java.util.Date();
 		Calendar c =
 				 new GregorianCalendar(
 					 TimeZone.getDefault(),Locale.getDefault());
 		c.setTime (date);
 		
 		
 		if (define.equalsIgnoreCase("LAST_MONTHN_FIRST_DATE")){
 			c.set(Calendar.DATE,1); // 设置这个月的第一天.
 			date = modifyDate(c.getTime(),0,-1,0,0,0,0); // 上一个月首日
 		} else if (define.equalsIgnoreCase("LAST_MONTHN_LAST_DATE")){
 			c.set(Calendar.DATE,1); // 设置这个月的第一天.
 			date = modifyDate(c.getTime(),0,0,-1,0,0,0); // 上一个月最后一日
 		} else if (define.equalsIgnoreCase("LAST_LAST_WEEK_SATURDAY_DATE")) {
 			int dayofweek = c.get(Calendar.DAY_OF_WEEK);
 			date = modifyDate(c.getTime(),0,0,- (dayofweek + 7)  ,0,0,0);//上上个星期六
 		} else if (define.equalsIgnoreCase("LAST_WEEK_FRIDAY_DATE")) {
 			int dayofweek = c.get(Calendar.DAY_OF_WEEK);
 			date = modifyDate(c.getTime(),0,0,- (dayofweek + 1)  ,0,0,0);//上个星期五
 		} else if (define.equalsIgnoreCase("LAST_DATE")){
 			date = modifyDate(c.getTime(),0,0,-1,0,0,0); //昨天
 		}

 		if (date==null) {
 			return null;
 		}
 		return date;
 	};
 	
 	
 	public static java.sql.Date getSqlDate(java.util.Date date){
 		return new java.sql.Date(date.getTime());
 	}
 	
 	/**
 	 * change String value to any type of sql input parameter
 	 * suport : date,timestamp ,string ,bigdecimal,int,double ...
 	 * 
 	 * @param type
 	 * @param value
 	 * @return
 	 */
 	public static Object str2Object(String type , String value) {
 		Object rtn = null ;
 		
 		type = null2Str(type) ;
 		
 		// date,timestamp,string,bigdecimal,int,double
 		if (type.equals("")) return null ;
 		if (value == null) return null ;
 		value = null2Str(value) ;
 		
 		if (type.equalsIgnoreCase("string")) {
 			return value;
 		} else if (type.equalsIgnoreCase("date")) {
 			return str2SqlDate(value);
 		}
 		return rtn;
 	} 
 	
 	/**
 	 * 判断文件存不存在
 	 * 文件要指定好相对路径
 	 * @version 1.1
 	 */
 	public static boolean isFileExists(String fileName){
 		if("".equals(fileName)) return false;
 		File currFile= new File(fileName);
 		return currFile.exists();
 	}
 	
 	/**
 	 * 获取当前项目的根目录
 	 * @return  系统目录名
 	 * @version 1.1
 	 */
 	public static String getPath(){
 	   if("".equals(sysRootPath)){
 	   	     StringUtil o = new StringUtil();
 		     String projectPath = o.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
 			 String path = projectPath.substring(0,projectPath.lastIndexOf("/")-1);
 		     sysRootPath = path.substring(0,path.lastIndexOf("/"));
 	   }
 	   return sysRootPath;
 	   
 	}
 	
 	/**
 	 * 判断是否表达式参数(拷贝SystemSettingDAO) 
 	 * @param value
 	 * @return
 	 * @version 1.1
 	 */
 	public static boolean isFormulaString(String value) {
 		if (value == null) return false;
 		int firstIndex = value.indexOf(beginFormula);
 		int lastIndex = value.lastIndexOf(endFormula);
 		if (lastIndex > firstIndex){
 			return true;
 		} else {
 			return false;
 		}
 	}

 	/**
 	 * 判断是否表达式参数(拷贝SystemSettingDAO) 
 	 * @return
 	 */
 	public static String getFormulaString(String value){
 		if (value == null) return "";
 		int firstIndex = value.indexOf(beginFormula);
 		int lastIndex = value.lastIndexOf(endFormula);
 		value = value.substring(firstIndex + 2 ,lastIndex);
 		return value;
 	}
 	
 	/**
 	 * 获取文件名的后缀
 	 * @param fileName
 	 * @return
 	 */
 	public static String getFileExtension(String fileName){
 		return getFileExtension(fileName,"xls");// default xls
 	}
 	
 	/**
 	 * 获取文件名的后缀
 	 * @param fileName
 	 * @param defaultExtension
 	 * @return
 	 */
 	public static String getFileExtension(String fileName,String defaultExtension){
 		if(fileName==null || fileName.equals("")){
 			return "";
 		}
 		int extensionIndex = fileName.lastIndexOf(".");
 		if(extensionIndex<0){
 			return "";
 		} else if ( extensionIndex == fileName.length()-1) {
 			return defaultExtension ; 
 		} else {
 			return fileName.substring(extensionIndex+1);
 		}
 	}
 	
 	/**
 	 * 根据文件路径获取文件名
 	 * @param filePath
 	 * @return
 	 */
 	public static String getFileNameByPath(String filePath){
 		return getFileNameByPath(filePath,true,File.separator);
 	}
 	
 	/**
 	 * 根据文件路径获取文件名
 	 * @param filePath
 	 * @param getExtension 是否获取扩展名
 	 * @return
 	 */
 	public static String getFileNameByPath(String filePath,boolean getExtension){
 		return getFileNameByPath(filePath,getExtension,File.separator);
 	}
 	
 	/**
 	 * 根据文件路径获取文件名
 	 * @param filePath
 	 * @param getExtension 是否获取扩展名
 	 * @param separator
 	 * @return
 	 */
 	public static String getFileNameByPath(String filePath,boolean getExtension,String separator){
 		if(filePath==null || filePath.equals("")){
 			return "";
 		}else{
 			int lastSeparatorIndex = filePath.lastIndexOf(separator);
 			if(lastSeparatorIndex<0){
 				if(getExtension){
 					return filePath;
 				}else{
 					int extensionIndex = filePath.lastIndexOf(".");
 					return filePath.substring(0,extensionIndex);
 				}
 				
 			}else if(lastSeparatorIndex == filePath.length()-1){
 				return "";
 			} else {
 				if(getExtension){
 					return filePath.substring(lastSeparatorIndex+1);
 				}else{
 					int extensionIndex = filePath.lastIndexOf(".");
 					return filePath.substring(lastSeparatorIndex+1,extensionIndex);
 				}
 			}
 		}
 	}
 	
 	/**
 	 * 获取文件夹路径
 	 * @param path
 	 * @return
 	 */
 	public static String getFilePath(String path){
 		return getFilePath(path,File.separator);
 	}
 	
 	/**
 	 * 获取文件夹路径
 	 * @param path
 	 * @param separator
 	 * @return
 	 */
 	public static String getFilePath(String path,String separator){
 		if(path==null || path.equals("")){
 			return "";
 		}else{
 			int lastSeparatorIndex = path.lastIndexOf(separator);
 			if(lastSeparatorIndex<0){
 				return "";
 			}else{
 				return path.substring(0,lastSeparatorIndex+1);
 			}
 		}
 	}
 	
 	/**
 	 * 判断最后的是否分隔符
 	 * @param path
 	 * @return
 	 */
 	public static boolean isLastSeparator(String path){
 		return isLastSeparator(path,File.separator);
 	}
 	
 	/**
 	 * 判断最后的是否分隔符
 	 * @param path
 	 * @param separator
 	 * @return
 	 */
 	public static boolean isLastSeparator(String path,String separator){
 		if(path==null || path.length()==0){
 			return false;
 		}
 		return path.substring(path.length()-1).equals(separator);
 	}
 	
 	/**
 	 * 在文件路径中增加文件名
 	 * @param path
 	 * @param fileName
 	 * @return
 	 */
 	public static String pathAddFileName(String path,String fileName){
 		if(null2Str(path).equals("")){
 			return "";
 		}
 		
 		if(null2Str(fileName).equals("")){
 			return path;
 		}
 		
 		if(isLastSeparator(path)){
 			return path + fileName;
 		}else{
 			return path + File.separator + fileName;
 		}
 	}
 	
 	/**
 	 * 返回两个字符串中间的内容
 	 * @param all
 	 * @param start
 	 * @param end
 	 * @return
 	 */
 	public static String getMiddleString(String all,String start,String end){
 		int beginIdx = all.indexOf(start) + start.length();
 	    int endIdx = all.indexOf(end);
 	    return all.substring(beginIdx, endIdx);
 	}
 	
 	// SubStringAfter/SubStringBefore
     //-----------------------------------------------------------------------
     /**
      * <p>Gets the substring before the first occurrence of a separator.
      * The separator is not returned.</p>
      *
      * <p>A <code>null</code> string input will return <code>null</code>.
      * An empty ("") string input will return the empty string.
      * A <code>null</code> separator will return the input string.</p>
      *
      * <pre>
      * StringUtils.substringBefore(null, *)      = null
      * StringUtils.substringBefore("", *)        = ""
      * StringUtils.substringBefore("abc", "a")   = ""
      * StringUtils.substringBefore("abcba", "b") = "a"
      * StringUtils.substringBefore("abc", "c")   = "ab"
      * StringUtils.substringBefore("abc", "d")   = "abc"
      * StringUtils.substringBefore("abc", "")    = ""
      * StringUtils.substringBefore("abc", null)  = "abc"
      * </pre>
      *
      * @param str  the String to get a substring from, may be null
      * @param separator  the String to search for, may be null
      * @return the substring before the first occurrence of the separator,
      *  <code>null</code> if null String input
      * @since 2.0
      */
     public static String substringBefore(String str, String separator) {
         if (isEmpty(str) || separator == null) {
             return str;
         }
         if (separator.length() == 0) {
             return "";
         }
         int pos = str.indexOf(separator);
         if (pos == -1) {
             return str;
         }
         return str.substring(0, pos);
     }

 	public static String trim(final String value) {
 		return StringUtil.isNull(value) ? "" : value.trim();
 	}

 	/**
      * <p>Checks if a String is whitespace, empty ("") or null.</p>
      *
      * <pre>
      * StringUtils.isBlank(null)      = true
      * StringUtils.isBlank("")        = true
      * StringUtils.isBlank(" ")       = true
      * StringUtils.isBlank("bob")     = false
      * StringUtils.isBlank("  bob  ") = false
      * </pre>
      *
      * @param str  the String to check, may be null
      * @return <code>true</code> if the String is null, empty or whitespace
      * @since 2.0
      */
     public static boolean isBlank(String str) {
         int strLen;
         if (str == null || (strLen = str.length()) == 0) {
             return true;
         }
         for (int i = 0; i < strLen; i++) {
             if ((Character.isWhitespace(str.charAt(i)) == false)) {
                 return false;
             }
         }
         return true;
     }
     
     public static boolean isNotBlank(String str) {
         return !StringUtil.isBlank(str);
     }
     
     /**
      * 根据segement获取 起飞城市
      * @param segmentTrip
      * @return
      */
     public static String getDeparture(String segmentTrip){
     	if(isEmpty(segmentTrip)){
     		return "";
     	}
     	String departure=segmentTrip.split("-")[0];
     	if("null".equalsIgnoreCase(departure)) return "";
     	return departure;
     }
     /**
      * 根据segement获取 到达城市
      * @param segmentTrip
      * @return
      */
     public static String getArrival(String segmentTrip){
     	if(isEmpty(segmentTrip)){
     		return "";
     	}
     	String arrival=segmentTrip.split("-")[1];
     	if("null".equalsIgnoreCase(arrival)) return "";
     	return arrival;
     }
}
