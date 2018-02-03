package com.sxd.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @author shixiaodong
 *
 */
public class PropertiesUtil {
	
	public static Properties getProperties(String path) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			props.load(in);
		} catch (IOException e) {
			//cann't load properties file, please check file 
			e.printStackTrace();
		}
		return props;
	}
	
	public static Map<String, String> getKeyValue(String path) {
		Properties props = getProperties(path);
		return getKeyValue(props);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getKeyValue(Properties props) {
		Enumeration<String> en = (Enumeration<String>) props.propertyNames();
		Map<String, String> map = new HashMap<String, String>();
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			String value = props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}
	
	public static void setProperties(String path, Map<String, String> map) {
		setProperties(path, map, "");
	}
	
	public static void setProperties(String path, Map<String, String> map, String comments) {
		if(map.isEmpty() || StringUtil.isNull(map)) {
			return;
		}
		Properties props = getProperties(path);
		OutputStream out = null;
		
		//更新/插入 新的键值对
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String value = entry.getValue();
			props.setProperty(key, value);
		}
		try {
			out = new FileOutputStream(path);
			props.store(out, comments);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void setProperties(Properties props, Map<String, String> map) {
//		//how to get properties file path
//	}

}
