package com.sxd.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static final String DEFAULT_SPLIT = "/";
	
	private TimeUtil(Date date) {
		
	}
	
	public static int getYear() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return Integer.parseInt(sdf.format(date));
	}
	
	public static int getMonth() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return Integer.parseInt(sdf.format(date));
	}
	
	public static int getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return Integer.parseInt(sdf.format(date));
	}
	
	public static int getHour() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return Integer.parseInt(sdf.format(date));
	}
	
	public static int getMinutes() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("mm");
		return Integer.parseInt(sdf.format(date));
	}
	
	public static int getSecond() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ss");
		return Integer.parseInt(sdf.format(date));
	}
	
	public static String getDay() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		return sdf.format(date);
	}
	
	public static String getTime() {
		return getTime(DEFAULT_SPLIT);
	}
	
	public static String getTime(String split) {
		StringBuilder builder = new StringBuilder();
		builder.append(getYear() + split);
		builder.append(getMonth() + split);
		builder.append(getDate() + "  ");
		builder.append(getHour() + split);
		builder.append(getMinutes() + split);
		builder.append(getSecond() + " ");
		builder.append(getDay());
		return builder.toString();
	}

	public static String getYMD() {
		return getYMD(DEFAULT_SPLIT);
	}
	
	public static String getYMD(String split) {
		StringBuilder builder = new StringBuilder();
		builder.append(getYear() + split);
		builder.append(getMonth() + split);
		builder.append(getDate());
		return builder.toString();
	}

	public static String getHMS() {
		return getHMS(DEFAULT_SPLIT);
	}
	
	public static String getHMS(String split) {
		StringBuilder builder = new StringBuilder();
		builder.append(getHour() + split);
		builder.append(getMinutes() + split);
		builder.append(getSecond());
		return builder.toString();
	}

}
