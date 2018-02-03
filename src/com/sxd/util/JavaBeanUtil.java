package com.sxd.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author shixiaodong
 *
 */

public class JavaBeanUtil {
	
	/**
	 * @param dest 目的对象
	 * @param fieldName 属性名
	 * @return 对应属性值
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Object getFieldValue(Object dest, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field fs = dest.getClass().getDeclaredField(fieldName);
		fs.setAccessible(true);
		return fs.get(dest);
	}
	
	public static int getFieldCount(Object dest) {
		return dest.getClass().getDeclaredFields().length;
	}
	
	/**
	 * @param dest 目的对象
	 * @param fieldName 属性名
	 * @return 对应属性类型
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static Type getFieldType(Object dest, String fieldName) throws NoSuchFieldException, SecurityException {
		Field fs = dest.getClass().getDeclaredField(fieldName);
		return fs.getGenericType();
	}
	
	
	/**
	 * @param className 类名
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Object getJavaBeanByClassName(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return Class.forName(className).newInstance();
	}
	
	
	
	public static PropertyDescriptor getPropertyDescriptor(Class clazz, String propertyName) {
		StringBuffer sb = new StringBuffer();
		Method setMethod = null;
		Method getMethod = null;
		PropertyDescriptor pd = null;
		try {
			Field f = clazz.getDeclaredField(propertyName);
			if(f != null) {
				String methodEnd = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1).toUpperCase();
				sb.append("set" + methodEnd);
				setMethod = clazz.getDeclaredMethod(sb.toString(), new Class[] { f.getType() });
				
				sb.delete(0, sb.length());
				sb.append("get" + methodEnd);
				getMethod = clazz.getDeclaredMethod(sb.toString(), new Class[] {});
				pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pd;
	}
	
	public static void setProperty(Object obj, String propertyName, Object value) {
		Class<? extends Object> clazz = obj.getClass();
		PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
		Method setMethod = pd.getWriteMethod();
		try {
			setMethod.invoke(obj, new Object[] {value});	//return null
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object getProperty(Object obj, String propertyName) {
		Class<? extends Object> clazz = obj.getClass();
		PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
		Method getMethod = pd.getReadMethod();
		Object value = null;
		try {
			value = getMethod.invoke(clazz, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void setValue(Object obj, String fieldName, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(obj, value);
	}
	
}
