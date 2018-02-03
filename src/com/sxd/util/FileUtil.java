package com.sxd.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileUtil {
	
	//���ذ����ļ��Լ��ļ�����������
	public static String[] getFileList(String path) {
		return new File(path).list();	
	}
	
	public static long getLastModifiedTime(File file) {
		return file.lastModified();
	}
	
	public static String getLastModifiedString(File file) {
		//Ч������
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.setTimeInMillis(getLastModifiedTime(file));
		return formatter.format(cal.getTime());
	}
	
	//����ָ��·����ָ�����ļ�������
	public static File createFile(String path, String name) {
		File _path = new File(path);
		if(!_path.exists()) {
			_path.mkdirs();
		}
		File file = new File(path + "\\" + name);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static boolean deleteFile(String path, String name) {
		File file = new File(path + "\\" + name);
		return deleteFile(file);
	}
	
	public static boolean deleteFile(File file) {
		if(file.exists() && file.isAbsolute()) {
			return file.delete();
		} else {
			return false;
		}
	}
	
	//д�ļ�
 	public static boolean writeLine(File file, boolean isAppend, String... info) {
		FileWriter fw = null;
		BufferedWriter writer = null;
		StringBuilder sb = new StringBuilder();
		String line = "";
		
		//ƴ��
		for(int i = 0; i < info.length; i++) {
			sb.append(info[i] + " ");
		}
		line = sb.toString();
		try {
			fw = new FileWriter(file, isAppend);
			writer = new BufferedWriter(fw);
			writer.write(line);
			writer.newLine();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			//�ر���
			try {
				writer.close();
				fw.close();		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
 	
 	public static void main(String[] args) {
 		File f = new File("F:\\Share\\����ͨ�����������磨1��.pdf");
 		String s = getLastModifiedString(f);
 		System.out.println(s);
 	}
}
