package com.example.lyy.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtil {
	public static final String UTF8 = "UTF-8";

	public static String bytes2Str(byte[] bytes, String charset) {
		if (bytes == null || bytes.length == 0) {
			return "";
		}
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String bytes2Str(byte[] bytes) {
		return bytes2Str(bytes, UTF8);
	}

	public static byte[] str2Bytes(String str, String charset) {
		if (str == null || str.length() == 0) {
			return null;
		}
		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] str2Bytes(String str) {
		return str2Bytes(str, UTF8);
	}

	public static String urlEncode(String str) {
		if (str == null) {
			str = "";
		}
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String urlDecode(String str) {
		if (str == null) {
			str = "";
		}
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String urlEncode(String str, String encoding) {
		if (str == null) {
			str = "";
		}
		try {
			return URLEncoder.encode(str, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String urlDecode(String str, String encoding) {
		if (str == null) {
			str = "";
		}
		try {
			return URLDecoder.decode(str, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void appendSignPara(StringBuffer buf, String string, String productName) {
		if (productName != null && productName.trim().length() > 0) {
			buf.append(string).append("=").append(productName + "&");
		}
	}

	public static void appendLastSignPara(StringBuffer buf, String string, String md5key) {
		buf.append(string).append("=").append(md5key);
	}
}
