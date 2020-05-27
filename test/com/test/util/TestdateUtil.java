package com.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestdateUtil {
	/*
	 * 输出时间戳
	 */
	@Test
	public void TestNewDateString() {
		NewDateString();
	}

	/*
	 * 输出当前时间
	 */
	@Test
	public void TestNowTime() {
		NowTime();
	}
	
	
	public void NewDateString() {
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+"MM"+"dd"+"HH"+"mm"+"ss");
		String str1 = sdf.format(date1);
		System.out.println(str1);
	}
	public void NowTime() {
		Date date2 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str2 = sdf.format(date2);
		System.out.println(str2);
	}
}
