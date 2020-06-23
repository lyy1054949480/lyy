package com.example.lyy.interceptor.mybaits;

import com.alibaba.fastjson.JSON;
import com.example.lyy.annotation.ClassEncryptionAnnotation;
import com.example.lyy.annotation.FieldEncryptionAnnotation;
import com.example.lyy.util.encryption.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
        Object.class}))
//@Component
public class MyUpdateIntercepter implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("----------------update---------------");
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        String id = mappedStatement.getId();
        System.out.println(id);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        System.out.println("parameter"+parameter);
        if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)){
            if (id.contains("insertListExtend")){
                Map<Integer,Object> insertMap = (Map)parameter;
                System.out.println(JSON.toJSON(insertMap));
                List lists = (List)insertMap.get("list");
                if (!CollectionUtils.isEmpty(lists)){
                    for (Object o : lists) {
                        if (!checkNeedEncrypt(o)){
                            System.out.println("-=-=-=-=-=-=-list"+o.getClass().getName());
                            updateFeild(o);
                        }
                    }
                }
            }else{
                if (!checkNeedEncrypt(parameter)){
                    System.out.println("-=-=-=-=-=-=-object"+parameter.getClass().getName());
                    updateFeild(parameter);
                }
            }
        }
        return invocation.proceed();
    }
 
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
 
    @Override
    public void setProperties(Properties properties) {
 
    }

    private boolean checkNeedEncrypt(Object o){
        ClassEncryptionAnnotation annotation = o.getClass().getAnnotation(ClassEncryptionAnnotation.class);
        return annotation == null;
    }
 
    private void updateFeild(Object parameter) throws Exception {
        try {
            Field[] declaredFields = parameter.getClass().getDeclaredFields();
            String name = parameter.getClass().getName();
            System.out.println(name);
            for (Field field: declaredFields){
                FieldEncryptionAnnotation annotation = field.getAnnotation(FieldEncryptionAnnotation.class);
                if (annotation != null){
                    if (StringUtils.isEmpty(annotation.path())){
                        field.setAccessible(true);
                        field.set(parameter, Base64Util.encode((String) field.get(parameter)));
                    }else{
                        Class encryptService = Class.forName(annotation.path());
                        Method method = encryptService.getMethod("getFieldEncryption", Object.class,String.class,String.class);
                        parameter = method.invoke(encryptService.newInstance(),parameter,field.getName(),annotation.colType());
                    }
                }
            }
        }catch (Exception e){
            log.error("");
            throw new Exception("加密失败");
        }
    }
}
