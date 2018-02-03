package com.sxd.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String encode(String src) {
		
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		//获得源串的字节数组
		byte[] srcByte = src.getBytes();
		//加密后字节数组
		byte[] destByte = md5.digest(srcByte);
		
		//通常转为16进制，不至于太长
		StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < destByte.length; i++){  
            int val = (destByte[i]) & 0xff;  
            
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
	}
}
