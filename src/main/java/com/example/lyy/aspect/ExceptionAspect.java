/*
package com.example.lyy.aspect;*/
package com.example.lyy.aspect;


import com.alibaba.fastjson.JSON;
import com.example.lyy.domain.PromptingMessageEnum;
import com.example.lyy.domain.ResponseCode;
import com.example.lyy.util.auxiliary.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
//指定类拦截
public class ExceptionAspect {


    @AfterThrowing(throwing = "ex",pointcut = "execution(* com.example.lyy.controller..* .*(..))")
    public void exceptionHandle(JoinPoint point, Throwable ex) throws Throwable {
        Object[] pointArgs = point.getArgs();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String[] argNames = ((MethodSignature) point.getSignature()).getParameterNames();
        for (int i = 0; i < pointArgs.length; i++) {
            if (!(pointArgs[i] instanceof HttpServletRequestWrapper) && !(pointArgs[i] instanceof ExtendedServletRequestDataBinder) && !(pointArgs[i] instanceof HttpServletResponseWrapper)) {
                paramMap.put(argNames[i], pointArgs[i]);
            }
        }
        log.info("接口方法--" + point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName() + "被访问,参数为" + JSON.toJSON(paramMap));

    }


}

