/*
 * Copyright (c) 2006, 东方口岸科技有限公司 
 * All rights reserved.
 * 
 * 文件名称：FormatExceptionStack.java
 * 摘    要：
 * 
 * 版    本：SuperPass_Version[2006-8-30-15:57:28]
 * 作    者：zhuyanchao
 * 创建日期：2006-8-30
 */
package com.example.lyy.util.auxiliary;

import com.example.lyy.util.system.PlatformOption;

/**
 * 格式化异常信息。

 * 
 * @author zhuyanchao
 * @version SuperPass_Version[2006-8-30]
 */
public class FormatExceptionStack {
	/**
	 * 获取堆栈的异常

	 * @param e 异常源

	 * @return 异常堆栈
	 */
	public String getStackException(Exception e) {
		if (e == null) {
			return "";
		}
		
		StackTraceElement[] stack = e.getStackTrace();
		return getExceptionDetail(stack,e.getClass().getName(),e.toString());
	}
	/**
	 * 获取异常信息 {@link #getStackException(Exception)}
	 * @param e 异常源

	 * @return 异常堆栈
	 */
	public String getStackException(Error e) {
		if (e == null) {
			return "";
		}
		StackTraceElement[] stack = e.getStackTrace();
		return getExceptionDetail(stack,e.getClass().getName(), e.toString());
	}
	/**
	 * 获取异常信息 {@link #getStackException(Exception)}
	 * @param e 异常源

	 * @return 异常堆栈
	 * @see #getStackException(Error)
	 */
	public static String formatException(Exception e) {
		if (e == null) {
			return "";
		}

		StackTraceElement[] stack = e.getStackTrace();
		return getExceptionDetail(stack,e.getClass().getName(),e.toString());
	}
	/**
	 * 获取异常信息 
	 * @param e 异常源

	 * @return 异常堆栈
	 * @see #getStackException(Exception)
	 */
	public static String formatException(Error e) {
		if (e == null) {
			return "";
		}
		StackTraceElement[] stack = e.getStackTrace();
		return getExceptionDetail(stack,e.getClass().getName(), e.toString());
	}
	/**
	 * 格式化异常信息

	 * @param stack 异常堆栈
	 * @param exceptionClass 异常类

	 * @param exception 异常信息
	 * @return 异常堆栈
	 */
	protected static String getExceptionDetail(StackTraceElement[] stack, String exceptionClass, String exception) {
		StringBuilder message = new StringBuilder();
		message.append(exception + PlatformOption.getLineSeparator()+ "exception type[" + exceptionClass +"], position:"+ PlatformOption.getLineSeparator());
		//String message = exception + PlatformOption.getLineSeparator()+ "exception type[" + exceptionClass +"], position:"+ PlatformOption.getLineSeparator();
		int stackLength = 10;
		if(stack.length < stackLength){
			stackLength = stack.length;
		}
		for (int i = 0;stack!=null && i<stackLength;i++) {
			String className = stack[i].getClassName();
			String fileName = stack[i].getFileName();
			int lineNumber = stack[i].getLineNumber();
			String methodName = stack[i].getMethodName();
			if (fileName == null) {
				message.append("	from ");
			} else {
				message.append("	from [" + fileName + "]");
			}
			
			message.append(className) ;
			message.append("." + methodName);
			message.append("[" + lineNumber + "]");
			message.append(PlatformOption.getLineSeparator());
		}
		if(stack.length > stackLength){
			message.append("......");
			message.append(PlatformOption.getLineSeparator());
		}
		return message.toString();
	}
}
