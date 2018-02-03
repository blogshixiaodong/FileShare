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
		//���Դ�����ֽ�����
		byte[] srcByte = src.getBytes();
		//���ܺ��ֽ�����
		byte[] destByte = md5.digest(srcByte);
		
		//ͨ��תΪ16���ƣ�������̫��
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
