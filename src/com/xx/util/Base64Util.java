package com.xx.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Base64Util {
	
/**编码格式；默认使用uft-8*/
    public String charset = "utf-8";

    public static Base64Util instance = new Base64Util();
    
/**
 * 	使用Base64进行加密
 */
    public String Base64Encode(String res) {
        return Base64.encode(res.getBytes());
    }

/**
 * 	使用Base64进行解密
 */
    public String Base64Decode(String res) {
        return new String(Base64.decode(res));
    }
}