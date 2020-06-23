package com.example.lyy.util;


import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CheckParamUtil {
    final static String[] ingoreFields = {"pn", "ps", "pageNum", "pageSize"};
    final static List<String> ingoreList = Arrays.asList(ingoreFields);

    public static Boolean checkFieldIsNull(List list){
        int nullCount = 0;
        int paramCount = 0;
        int ignoreCount = 0;
        for (Object object : list) {
            Class clazz = object.getClass();
            Field fields[] = clazz.getDeclaredFields();
            List<Field> fieldList = Arrays.asList(fields);
            if (!CollectionUtils.isEmpty(fieldList)){
                paramCount += fieldList.size();
                for(Field field : fieldList){
                    field.setAccessible(true);
                    Object fieldValue = null;
                    try {
                        String fieldName = field.getName();
                        if (!ingoreList.contains(fieldName)) {
                            fieldValue = field.get(object);
                            if (StringUtils.isEmpty(fieldValue)) {
                                nullCount++;
                            }
                        }else {
                            ignoreCount++;
                            nullCount++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ((nullCount==paramCount)&&(ignoreCount!=2*list.size()));
    }

    public static Boolean checkFieldIsNull(Object object) {
        int nullCount = 0;
        int paramCount = 0;
        int ignoreCount = 0;
        Class clazz = object.getClass();
        Field fields[] = clazz.getDeclaredFields();
        List<Field> fieldList = Arrays.asList(fields);
        if (!CollectionUtils.isEmpty(fieldList)) {
            paramCount += fieldList.size();
            for (Field field : fieldList) {
                field.setAccessible(true);
                Object fieldValue = null;
                try {
                    String fieldName = field.getName();
                    if (!ingoreList.contains(fieldName)) {
                        fieldValue = field.get(object);
                        if (ObjectUtils.isEmpty(fieldValue)) {
                            nullCount++;
                        }
                    } else {
                        ignoreCount++;
                        nullCount++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ((nullCount==paramCount)&&(ignoreCount!=2));
    }
}
