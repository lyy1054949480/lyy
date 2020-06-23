package com.example.lyy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Title: Spring 上下文工具类
 * @Description: 获取Spring上下文工具
 * @Date: 2019/3/26 14:29
 * @Auther: jinzhy
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * 取得 Spring IOC 上下文
     * @Return
     */
    public static ApplicationContext getSpringContext() {
        return applicationContext;
    }


    /**
     * 从 Spring IOC 的上下文中，用 bean id 取得 bean
     * @param id
     * @return Object
     */
    public static Object getBean(String id) {
        return applicationContext.getBean(id);
    }

    /**
     * 按照Bean类型取得所有相关bean的名称
     * @param clazz
     * @return String[]
     */
    public static String[] getBeanNamesForType(Class clazz){
        return applicationContext.getBeanNamesForType(clazz);
    }

    /**
     * 当前的bean id是否在上下文中
     * @param id
     * @return boolean
     */
    public static boolean contains(String id){
        return applicationContext.containsBean(id);
    }
}
