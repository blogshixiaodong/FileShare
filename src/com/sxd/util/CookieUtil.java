package com.sxd.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

public class CookieUtil {
	
	
	/**
	 * ������������Ӧ������ֵ���������cookiesΪnull���������в����ڸ������򷵻�null
	 * @param cookies Cookis����
	 * @param name ������
	 * @return ����ֵ
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
	 * @param cookies ����
	 * @return ����Cookie���������
	 */
	public static List<String> getKeyList(Cookie[] cookies) {
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < cookies.length; i++) {
			list.add(cookies[i].getName());
		}
		return list;
	} 
	
	/**
	 * @param cookies ����
	 * @return ����Cookie����ֵ����
	 */
	public static List<String> getValueList(Cookie[] cookies) {
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < cookies.length; i++) {
			list.add(cookies[i].getValue());
		}
		return list;
	}
	
}
