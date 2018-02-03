package com.sxd.util;

import java.lang.reflect.Field;

import javax.servlet.ServletRequest;

public class FormUtil {
	public static void setBeanValue(Object obj, ServletRequest request) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field f : fields) {
			String str = request.getParameter(f.getName());
			if(str != null) {
				try {
					f.setAccessible(true);
					String type = "";
					try {
						type = JavaBeanUtil.getFieldType(obj, f.getName()).toString();
					} catch (NoSuchFieldException | SecurityException e) {
						e.printStackTrace();
					}
					if(type.indexOf("String") != -1 || type.indexOf("string") != -1) {
						f.set(obj, str);
					} else if(type.indexOf("Integer") != -1 || type.indexOf("int") != -1) {
						f.set(obj, Integer.parseInt(str));
					} else if(type.indexOf("Double") != -1 || type.indexOf("double") != -1) {
						f.set(obj, Double.parseDouble(str));
					} else if(type.indexOf("Float") != -1 || type.indexOf("float") != -1) {
						f.set(obj, Float.parseFloat(str));
					} else if(type.indexOf("Boolean") != -1 || type.indexOf("boolean") != -1) {
						f.set(obj, Boolean.parseBoolean(str));
					} else if(type.indexOf("Long") != -1 || type.indexOf("long") != -1) {
						f.set(obj, Long.parseLong(str));
					} else if(type.indexOf("Short") != -1 || type.indexOf("short") != -1) {
						f.set(obj, Short.parseShort(str));
					} 
				} catch (IllegalArgumentException | IllegalAccessException e) {	
					e.printStackTrace();
				}
			}
		}
	}
}
