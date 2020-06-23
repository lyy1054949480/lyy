/*
 * Copyright (c) 2006, 东方口岸科技有限公司 
 * All rights reserved.
 * 
 * 文件名称：PlatformOption.java
 * 摘    要：
 * 
 * 版    本：SuperPass_Version[2006-8-2-19:03:56]
 * 作    者：zhuyanchao
 * 创建日期：2006-8-2
 */
package com.example.lyy.util.system;


import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * 本机平台相关的功能

 * 
 * @author zhuyanchao
 * @version 1.0
 */
public class PlatformOption {


	public static String getHostNameAndIp(){
        InetAddress inetAddress = null;
        try{
            inetAddress = InetAddress.getLocalHost();
            String hostName = inetAddress.getHostName();
            String hostAddress = inetAddress.getHostAddress();
            return "  主机"+hostName+"  ip"+hostAddress;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
	}

	/**
	 * 获取行分隔符
	 * @return
	 */
	public static String getLineSeparator() {
		String lineSeparator = null;
		if (lineSeparator != null)
			return lineSeparator;
		else {
			lineSeparator = System.getProperty("line.separator");
			if (lineSeparator == null) {

				String os = System.getProperty("os.name").toLowerCase();
				if (os.indexOf("mac") > -1) {/* mac */
					lineSeparator = "\r";
				} else if ((os.indexOf("windows 9") > -1)
						|| (os.indexOf("nt") > -1)
						|| (os.indexOf("windows 2000") > -1)
						|| (os.indexOf("windows xp") > -1)
						|| (os.indexOf("windows 2003") > -1)) {
					lineSeparator = "\r\n";
				} else {/* linx, unix */
					lineSeparator = "\n";
				}

			}
		}
		return lineSeparator;
	}

	/**
	 * 获取操作系统名称
	 * @param
	 * @return
	 */
	public static String getOsName() {
		String os = System.getProperty("os.name").toLowerCase();

		return os;
	}

	public static byte[] getUTF8Bytes(String inputString) {
		String encoding = System.getProperty("file.encoding");
		if (encoding == null) {
			encoding = "ISO8859_1";
		}
		byte[] outputByte = null;
		try {
			if (encoding.equalsIgnoreCase("UTF-8")) {
				outputByte = inputString.getBytes();
			} else {
				String tmp = new String(inputString.getBytes(), "UTF-8");
				outputByte = tmp.getBytes("UTF-8");
			}
		} catch(Exception e) {
			outputByte = new byte[0];
		}
		return outputByte;
		
	}
}
