package com.example.lyy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @title EnumUtil
 * @description 获取枚举值
 * @date 2019/6/6 12:14
 * @author yindq
 */
public class EnumUtil {
    private static final Logger logger = LoggerFactory.getLogger(EnumUtil.class);

    /**
     * 根据枚举编码获取枚举内容
     * 限定为 (int)code (String)value 组成的枚举
     *
     * @param c
     * @param enumCode
     */
    public static String getValue(Class c, Byte enumCode) {
        if (c.isEnum()) {
            try {
                Object[] enumConstants = c.getEnumConstants();
                Method m = enumConstants[0].getClass().getDeclaredMethod("values");
                Object[] objects = (Object[]) m.invoke(enumConstants[0]);
                for (Object objOne : objects) {
                    Field code = objOne.getClass().getDeclaredField("code");
                    Field value = objOne.getClass().getDeclaredField("value");
                    code.setAccessible(true);
                    value.setAccessible(true);
                    if(code.get(objOne).equals(enumCode.intValue())){
                        return value.get(objOne).toString();
                    }
                }
            } catch (Exception e) {
                logger.error("根据枚举编码获取枚举内容出错：{}",e.getMessage());
            }
        }
        return "";
    }

}
