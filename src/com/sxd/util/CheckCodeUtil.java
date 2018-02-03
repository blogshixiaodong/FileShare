package com.sxd.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @filename  CheckCodeUtil.java
 * @author    ShiXiaodong
 * @date      2017年12月30日 下午10:09:25
 * @describe  生成验证码
 * @version   v1.1
 */

public class CheckCodeUtil {
	private BufferedImage image = null;
	private final String CHECK_CODE = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
	private final int CODE_COUNT;
	private char[] rands;
	private final int width;
	private final int height;
	
	public CheckCodeUtil(int width, int height, int CODE_COUNT) {
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, this.height, BufferedImage.TYPE_INT_RGB);
		rands = new char[CODE_COUNT];
		this.CODE_COUNT = CODE_COUNT;
	}

	//绘制图片
	public void drawImage() {
		Graphics g = image.getGraphics();
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		drawDisturbPoint(g, 120);
		setRandomCode();
		drawCheckCode(g);
		dispose(g);
		
	}
	
	
	//产生指定位数随机验证码
	private void setRandomCode() {
		for(int i = 0; i < CODE_COUNT; i++) {
			int index = (int)(Math.random() * 62);
			rands[i] = CHECK_CODE.charAt(index);
		}
	}
	
	
	//绘制干扰点
	private void drawDisturbPoint(Graphics g, int pointCount) {
		for(int i = 0; i < pointCount; i++) {
			int x = (int)(Math.random() * width);
			int y = (int)(Math.random() * height);
			int red = (int)(Math.random() * 255);
			int green = (int)(Math.random() * 255);
			int blue = (int)(Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}
	}
	
	//绘制验证码
	private void drawCheckCode(Graphics g) {
		//设置字体颜色
		g.setColor(Color.black);
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 16));
		
		//宽度均等，在不同高度输出验证码的不同字符
		for(int i = 0; i < CODE_COUNT; i++) {
			//int x = i * 16 + ((int)(Math.random() * 10)) % 2;
			int x = ((2 * i + 1) * width) / (2 * CODE_COUNT) - 5;
			int y = height / 2 + ((int)(Math.random() * 10)) % 10;
			g.drawString("" + rands[i], x, y);
		}
	}
	
	//关闭资源
	private void dispose(Graphics g) {
		g.dispose();
	}
	
	//返回验证码对应的字符串
	public String getCheckCode() {
		return new String(rands);
	}
	
	public byte[] getImageBuffer() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "JPEG", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
}
