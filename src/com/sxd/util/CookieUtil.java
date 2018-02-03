package com.sxd.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

public class CookieUtil {
	
	
	/**
	 * 返回属性名对应的属性值，如果参数cookies为null，或数组中不存在该属性则返回null
	 * @param cookies Cookis数组
	 * @param name 属性名
	 * @return 属性值
	 */
	public static String getValue(Cookie[] cookies, String name) {
		if(cookies == null) {
			return null;
		}
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}
	
	public static Cookie createCookie(String name, String value) {
		return new Cookie(name, value);
	}
	
	public static Cookie createCookie(String name, String value, int maxAge, String path) {
		Cookie cookie = createCookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		return cookie;
	}
	
	public static Cookie getCook(Cookie[] cookies, String name) {
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(name)) {
				return cookies[i];
			}
		}
		return null;
	}
	
	/**
	 * @param cookies 数组
	 * @return 返回Cookie数组键集合
	 */
	public static List<String> getKeyList(Cookie[] cookies) {
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < cookies.length; i++) {
			list.add(cookies[i].getName());
		}
		return list;
	} 
	
	/**
	 * @param cookies 数组
	 * @return 返回Cookie数组值集合
	 */
	public static List<String> getValueList(Cookie[] cookies) {
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < cookies.length; i++) {
			list.add(cookies[i].getValue());
		}
		return list;
	}
	
}
