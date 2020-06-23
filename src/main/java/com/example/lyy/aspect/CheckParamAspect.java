/*
package com.example.lyy.aspect;


import jtpf.ins.svc.domain.ResponseCode;
import jtpf.ins.svc.domain.applicationEnum.PromptingMessageEnum;
import jtpf.ins.svc.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Aspect
//@Component
//指定类拦截
public class CheckParamAspect {

    final String[] ingoreFields = {"pn", "ps", "pageNum", "pageSize","org.springframework.cloud.sleuth.instrument.web.TraceHttpServletResponse","io.undertow.servlet.spec.HttpServletRequestImpl"};
    final List<String> ingoreList = Arrays.asList(ingoreFields);

    @Around("execution(* jtpf.ins.svc.controller..* .*(..)) && !execution(* jtpf.ins.svc.controller.MyExceptionHandler .*(..))")
    public Object checkParamNotNull(ProceedingJoinPointgJoinPoint point) throws Throwable {
        Object[] pointArgs = point.getArgs();
        log.info("接口方法--" + point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName() + "被访问,参数为" + Arrays.toString(pointArgs));
        if (fieldCheckIsNull(pointArgs)) {
            log.info(point.getSignature().getName() + "参数校验失败");
            return ResponseUtil.packErrorResponseDataObject(ResponseCode.SystemError.getCode(), PromptingMessageEnum.MESSAGE_7.getMessage());
        }

        log.info(point.getSignature().getName() + "参数校验成功,开始执行方法");
        return point.proceed(point.getArgs());
    }


    private Boolean fieldCheckIsNull(Object[] args) {
        Integer paramCount = calculateParamCount(args);
        //空参数数量
        int count = 0;
        List<Object> objects = Arrays.asList(args);
        if (!CollectionUtils.isEmpty(objects)) {
            List<Object> strObject = objects.stream().filter(item -> "java.lang.String".equals(item.getClass().getName())).collect(Collectors.toList());
            if (strObject.size() == objects.size()) {
                List<Object> notnullStr = objects.stream().filter(str -> !StringUtils.isEmpty(str)).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(notnullStr)) {
                    return false;
                }
            }else{
                for (Object arg : objects) {
                    Class<?> aClass = arg.getClass();
                    String name = aClass.getName();
                    if ("java.lang.String".equals(name)) {
                        if (StringUtils.isEmpty(arg)) {
                            count++;
                        }
                    } else {
                        if (!ingoreList.contains(name)){
                            Field[] declaredFields = aClass.getDeclaredFields();
                            List<Field> fieldList = Arrays.asList(declaredFields);
                            if (!CollectionUtils.isEmpty(fieldList)) {
                                for (Field declaredField : fieldList) {
                                    declaredField.setAccessible(true);
                                    Object fieldValue = null;
                                    try {
                                        if (!ingoreList.contains(declaredField.getName())) {
                                            fieldValue = declaredField.get(arg);
                                            if (StringUtils.isEmpty(fieldValue)) {
                                                count++;
                                            }
                                        } else {
                                            count++;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else {
            return true;
        }
        return count == paramCount;
    }

    private Integer calculateParamCount(Object[] args){
        Integer paramCount = 0;
        List<Object> objects = Arrays.asList(args);
        if (!CollectionUtils.isEmpty(objects)){
            for (Object object : objects) {
                Class<?> aClass = object.getClass();
                String name = aClass.getName();
                if ("java.lang.String".equals(name)) {
                    paramCount++;
                }else{
                    if (!ingoreList.contains(name)){
                        Field[] declaredFields = aClass.getDeclaredFields();
                        List<Field> fieldList = Arrays.asList(declaredFields);
                        List<Field> collect = fieldList.stream().filter(item -> ingoreList.contains(item)).collect(Collectors.toList());
                        paramCount += declaredFields.length - collect.size();
                    }
                }
            }
        }else {
            return 0;
        }
        return paramCount;
    }
}
*/
