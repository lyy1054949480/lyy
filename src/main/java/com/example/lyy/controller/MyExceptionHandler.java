package com.example.lyy.controller;

import com.alibaba.fastjson.JSON;
import com.example.lyy.domain.ResponseCode;
import com.example.lyy.util.auxiliary.FormatExceptionStack;
import com.example.lyy.util.auxiliary.ResponseUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:
 * @Description: 异常处理切面
 * @Date: 9:25 2019/3/1
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        StackTraceElement stack = e.getStackTrace()[0];
        String className = stack.getClassName();
        String methodName = stack.getMethodName();
        int lineNumber = stack.getLineNumber();
        String exceptionMsg = "异常发生在"+className+"类中的第"+lineNumber+"行   "+methodName+"方法中   " +FormatExceptionStack.formatException(e);
        return ResponseUtil.packResponseData(ResponseCode.SystemError.getCode(), exceptionMsg,null,null,null);
    }
}
