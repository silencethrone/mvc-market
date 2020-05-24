package com.xx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateUtil {
/* 实例化对象 */	
	public static dateUtil instance = new dateUtil();
/*
 * 	以时间生成文件名
 */
	public String newDatestring() {
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+"MM"+"dd"+"HH"+"mm"+"ss");
		String str1 = sdf.format(date1);
		//System.out.println(str);
		return str1;
	}
/*
 * 	当前时间
 */
	public String nowtime() {
		Date date2 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str2 = sdf.format(date2);
		return str2;
	}
}
