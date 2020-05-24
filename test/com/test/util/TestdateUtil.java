package com.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestdateUtil {
	/*
	 * 输出时间戳
	 */
	@Test
	public void newDatestring() {
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "MM" + "dd" + "HH" + "mm" + "ss");
		String str1 = sdf.format(date1);
		System.out.println(str1);
	}

	/*
	 * 输出当前时间
	 */
	@Test
	public void nowtime() {
		Date date2 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str2 = sdf.format(date2);
		System.out.println(str2);
	}
}
