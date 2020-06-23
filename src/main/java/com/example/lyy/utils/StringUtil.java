package com.example.lyy.utils;

import java.lang.reflect.Field;

/**
 * @Title: TODO
 * @Description: TODO
 * @Date: 2019/7/4 17:26
 * @Auther: zhanglei34
 */
public class StringUtil {

    /**
     * 用來解決后台提交form表单字符串的问题
     * @param obj
     * @param mark
     * @return
     * @throws IllegalAccessException
     */
    public static     String  splitMark(Object obj , String mark) throws IllegalAccessException {
        Class cls=obj.getClass();
        Field[] fields =cls.getDeclaredFields();
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            sb.append(f.getName()+"=").append(f.get(obj));
              if(i!=fields.length-1)
                    sb.append("&");
        }
        return sb.toString();
    }
}
