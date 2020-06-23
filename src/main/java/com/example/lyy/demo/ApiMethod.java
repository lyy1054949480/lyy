package com.example.lyy.demo;

import com.example.lyy.util.ClassTools;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

public class ApiMethod {

    public static void main(String[] args) {
        String[] value = new String[]{};
        Set<Class<?>> classes = ClassTools.getClasses("jtpf.ins.svc.controller");
        for (Class<?> aClass : classes) {
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                //获取到该方法的参数们
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (Class<?> parameterType : parameterTypes) {
                    Constructor<?>[] constructors = parameterType.getConstructors();
                    for (Constructor<?> constructor : constructors) {
                        String[] parameterNames = u.getParameterNames(constructor);
                        if (parameterNames != null && parameterNames.length > 0) {
                            for (String parameterName : parameterNames) {
                                RequestMapping annotation3 = aClass.getAnnotation(RequestMapping.class);
                                String[] value1 = annotation3.value();

                                if ("userCode".equals(parameterName) | "holderCode".equals(parameterName)) {
                                    PostMapping annotation = method.getAnnotation(PostMapping.class);
                                    GetMapping annotation2 = method.getAnnotation(GetMapping.class);
                                    RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
                                    if (annotation != null) {
                                        value = annotation.value();
                                    } else if (annotation1 != null) {
                                        value = annotation1.value();
                                    } else if (annotation2 != null) {
                                        value = annotation2.value();
                                    }
                                    for (String s : value) {
                                        System.out.println(value1[0] + s);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
