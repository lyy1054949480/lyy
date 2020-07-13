package com.example.lyy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * ConvertUtil 转换工具类
 * </p>
 *
 * @author lut
 * @since 2019.5.02
 */
public class ConvertUtil {

    private static Pattern linePattern = Pattern.compile("_([a-z])");

    private static Pattern humpPattern = Pattern.compile("\\B(\\p{Upper})(\\p{Lower}*)");

    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        @SuppressWarnings("rawtypes")
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map转成实体对象
     *
     * @param map   map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 驼峰转下划线(Map)
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Map<String, Object> map = JSONObject.parseObject(str);
        Map<String, Object> newMap = Maps.newHashMap();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            StringBuffer sb = new StringBuffer();
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Matcher matcher = humpPattern.matcher(key);
            while (matcher.find()) {
                matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            }
            matcher.appendTail(sb);
            newMap.put(sb.toString(), entry.getValue());
        }
        return JSON.toJSONString(newMap);

    }

    /**
     * 驼峰转下划线(List)
     *
     * @param str
     * @return
     */
    @SuppressWarnings("all")
    public static String humpToLineList(String str) {
        List<Map> list = JSONObject.parseArray(str, Map.class);
        List<Map<String, Object>> res = new ArrayList<>();
        list.stream().forEach(p -> {
            Map<String, Object> newMap = Maps.newHashMap();
            Iterator<Map.Entry<String, Object>> it = p.entrySet().iterator();
            while (it.hasNext()) {
                StringBuffer sb = new StringBuffer();
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                Matcher matcher = humpPattern.matcher(key);
                while (matcher.find()) {
                    matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
                }
                matcher.appendTail(sb);
                newMap.put(sb.toString(), entry.getValue());
            }
            res.add(newMap);
        });
        return JSON.toJSONString(res);
    }
}