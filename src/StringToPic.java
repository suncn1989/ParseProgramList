/*
 * Draw program list string to pics.
 */

import java.awt.image.BufferedImage;
import java.awt.*;

import com.sun.image.codec.jpeg.*;

import java.io.*;

public class StringToPic {
	
	public void drawString(Graphics2D g, java.awt.FontMetrics fm, String pname, String sname, String ename, int flag)
	{
		try
		{
			/*
			FileOutputStream outputStream = new FileOutputStream("aa.jpg");
			BufferedImage bi = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.GRAY);
			java.awt.FontMetrics fm=g.getFontMetrics();
			g.clearRect(0, 0, 400, 400);
			*/
			//String pname = "节目名称";
			//String sname = "开始时间";
			//String ename = "结束时间";
			
			g.drawString(pname, setNameWidth(fm, pname), setTextHeight(fm, flag));
			g.drawString(sname, setStartWidth(fm, sname), setTextHeight(fm, flag));
			g.drawString(ename,setEndWidth(fm, ename),setTextHeight(fm, flag));
			/*
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
			param.setQuality(1.0f, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(bi);
			outputStream.close();
			System.out.println("OK!");
			*/
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR!!");
		}
	
	}

//Set Text x-position.	
	public static int setNameWidth(java.awt.FontMetrics fm, String s)
	{
		int width = 0;
		width = (140-fm.stringWidth(s))/2;
		return width;
	}
	public static int setStartWidth(java.awt.FontMetrics fm, String s)
	{
		int width = 0;
		width = 140 + (130 - fm.stringWidth(s))/2;
		return width;
	}
	public static int setEndWidth(java.awt.FontMetrics fm, String s)
	{
		int width = 0;
		width = 270 + (130 - fm.stringWidth(s))/2;
		return width;
	}

//Set Text y-position
	public static int setTextHeight(java.awt.FontMetrics fm, int flag)
	{
		int height = 0;
		height = 15*flag + fm.getHeight();
		return height;
	}
}
