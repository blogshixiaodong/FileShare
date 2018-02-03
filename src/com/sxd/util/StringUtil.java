package com.sxd.util;

import java.lang.reflect.Type;

/**
 * @author shixiaodong
 * @version 1.1
 */

public class StringUtil {
	
	/**
	 * @param str 待处理字符串
	 * @return string --> 'string'
	 */
	public static String getSingleQuoteMark(String str) {
		return "'" + str + "'";
	}
	
	/**
	 * @param str 待处理字符串
	 * @return sting --> "string"
	 */
	public static String getDoubleQuoteMark(String str) {
		return "\"" + str + "\""; 
	}
	
	/**
	 * @param str 待处理字符串
	 * @return sting --> {string}
	 */
	public static String getBrace(String str) {
		return "{" + str + "}";
	}
	
	/**
	 * @param str 待处理字符串
	 * @return sting --> (string)
	 */
	public static String getParenthesis(String str) {
		return "(" + str + ")";
	}
	
	/**
	 * @param str 待处理字符串
	 * @return sting --> [string]
	 */
	public static String getBracket(String str) {
		return "[" + str + "]";
	}
	
	
	/**
	 * 基本数据类型转为字符串
	 * @param bool 布尔值
	 * @return 字符串
	 */
	public static String getBaseTypeString(Boolean bool) {
		return Boolean.toString(bool);
	}
	
	/**
	 * 基本数据类型转为字符串
	 * @param integer 整型值
	 * @return 字符串
	 */
	public static String getBaseTypeString(int integer) {
		return Integer.toString(integer);
	}
	
	/**
	 * 基本数据类型转为字符串
	 * @param _float 单精度浮点数
	 * @return 字符串
	 */
	public static String getBaseTypeString(float _float) {
		return Float.toString(_float);
	}
	
	/**
	 * 基本数据类型转为字符串
	 * @param _double 双精度浮点数
	 * @return 字符串
	 */
	public static String getBaseTypeString(double _double) {
		return Double.toString(_double);
	}
	
	
	/**
	 * 实现类似C#中String.Format函数功能， String.Format("{0} + {1} = {2}", 1, 2, 1+2)   -->   "1+2=3"
	 * @param Source 含示例参数的字符串
	 * @param strings 匹配参数
	 * @return 匹配后的字符串
	 */
	public static String Format(String Source, String... strings) {
		int replaceNum = strings.length;
		for(int i = 0; i < replaceNum; i++) {	
			Source = Source.replace(getBrace(getBaseTypeString(i)), strings[i]);		
		}
		return Source;
	}
	
	
	/**
	 * 判断是否为null
	 * @param obj 判断对象
	 * @return 是否为null
	 */
	public static Boolean isNull(Object obj) {
		return null == obj;
	}
	
	
	/**
	 * 判断字符串是否为null或为""
	 * @param str 判断的字符串
	 * @return 是否为null或为""
	 */
	public static Boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	
	/**
	 * 判断是否初始化，若已初始化返回原值source，否之返回value
	 * @param source 原值
	 * @param value  初始化值
	 * @return
	 */
	public static String setInitValue(String source, String value) {
		if(isNullOrEmpty(source)) {
			return value;
		}
		return source;
	}
	
	
	/**
	 * 将万用参数组成数组形式返回
	 * @param strings 万用参数
	 * @return 数组
	 */
	public static String[] getArray(String... strings) {
		return (String[])getArray((Object[])strings);
	}
	
	public static Object[] getArray(Object... obj) {
		Object[] array = new String[obj.length];
		for(int i = 0; i < obj.length; i++) {
			array[i] = obj[i];
		}
		return array;
	}
	
	/**
	 * @param obj 调用System.out.print(obj);
	 */
	public static void print(Object obj) {
		System.out.print(obj);
	}
	
	/**
	 * @param obj 调用System.out.println(obj);
	 */
	public static void println(Object obj) {

		System.out.println(obj);
	}
	
	//根据Type类型，将Object转成相应类型，仅提供几个基本数据类型的转化{String, Integer, Float, Double}
	public static Object convert(Type type, Object obj) {
		String typeString = type.toString().toLowerCase();
		String value = obj.toString();
		if(typeString.contains("String")) {
			return value;
		} else if(typeString.contains("int") || typeString.contains("Integer")) {
			return new Integer(value);
		} else if(typeString.contains("float") || typeString.contains("Float")) {
			return new Float(value);
		} else if(typeString.contains("double") || typeString.contains("Double")) {
			return new Double(value);
		} else if(typeString.contains("boolean") || typeString.contains("Boolean")) {
			return new Integer(value);
		}
		return value;
	}
}
