package com.example.lyy.aspect;


import com.example.lyy.domain.PromptingMessageEnum;
import com.example.lyy.domain.ResponseCode;
import com.example.lyy.util.CheckParamUtil;
import com.example.lyy.util.auxiliary.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Aspect
@Component
/**
 * 拦截CheckParam注解
 */
public class CheckParamPoint {

    @Pointcut("@annotation(com.example.lyy.aspect.CheckParam)")
    public void methodPointcut() {
    }

    @Around("methodPointcut()&&@annotation(checkParam)")
    public void around(ProceedingJoinPoint point,CheckParam checkParam) throws Throwable {
        Object[] args = point.getArgs();
        log.info("方法--" + point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName() + "被访问,参数为" + Arrays.toString(args));
        List<Object> objects = Arrays.asList(args);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String lyy = requestAttributes.getRequest().getHeader("lyy");
        Boolean fieldIsNull = CheckParamUtil.checkFieldIsNull(objects);
        /*Set<String> set = new HashSet<>();
        Set<Class<?>> classes = ClassTools.getClasses("jtpf.ins.svc.controller");
        for (Class<?> aClass : classes) {
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                Class<?> returnType = method.getReturnType();
                set.add(returnType.getName());
            }
        }
        System.out.println(set);*/
        if (fieldIsNull) {
            if ("00".equals(checkParam.fieldType())){
                System.out.println("00");
            }else if ("01".equals(checkParam.fieldType())){
                System.out.println("01");
            }
        }
        log.info(point.getSignature().getName() + "参数校验成功,开始执行方法");
        Object proceed = point.proceed(point.getArgs());
        log.info("方法执行结束，结果"+proceed);

    }
}
