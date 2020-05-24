package com.test.util;

import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class TestBase64Util {
	String res = "123abc";
	/*
	 * 	使用Base64进行加密
	 */
	@Test
    public void Base64Encode() {
    	System.out.println(Base64.encode(res.getBytes()));
    }

	/*
	 * 	使用Base64进行解密
	 */
	@Test
    public void Base64Decode() {
		System.out.println(new String(Base64.decode("MTIzYWJj")));
    }
}