/*
package com.example.lyy.listener;

import com.example.lyy.annotation.ControllerAnnotation;
import com.example.lyy.annotation.MethodAnnotation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

*/
/**
 * @author xiaojian
 * @description 系统启动监听
 * @date 2019/4/29
 **//*

@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
public class SysStartListener implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        this.loadAnnotation();
    }

    public void loadAnnotation() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(ControllerAnnotation.class);
        if (map == null) {
            return;
        }
        List<MethodObj> cache = new ArrayList<>();
        for (Object obj : map.values()) {
            Method[] methods = obj.getClass().getMethods();
            for (Method method : obj.getClass().getMethods()) {
                System.out.println(method.getName());
                if (method.isAnnotationPresent(MethodAnnotation.class)) {
                    String cmd = method.getAnnotation(MethodAnnotation.class).value();
                    MethodObj ref = new MethodObj();
                    ref.setMethod(method);
                    ref.setObj(obj);
                    cache.add(ref);
                }
            }
        }
        for (MethodObj methodObj : cache) {
            System.out.println(methodObj.getMethod().getName());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

*/
