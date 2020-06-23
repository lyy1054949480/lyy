package com.example.lyy.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.lyy.entity.Student;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class ListDiifTest {

    public static void main(String[] args) throws Exception {
        List<Image> list1 = new ArrayList<>();
        list1.add(Image.builder().name("11").build());
        list1.add(Image.builder().name("22").size("123").build());
        list1.add(Image.builder().name("12").size("7").build());
        list1.add(Image.builder().name("111").size("7").build());


        List<Image> list2 = new ArrayList<>();
        list2.add(Image.builder().name("11").build());
        list2.add(Image.builder().name("22").size("345").build());
        list1.add(Image.builder().name("12").data("ssssssssssss").build());

        Sets.SetView<Image> difference = Sets.symmetricDifference(new HashSet<>(list1), new HashSet<>(list2));
        System.out.println(JSON.toJSONString("---"+difference));

        Map<String, List<Object>> diff = getDiff(list1, list2);
        System.out.println(JSON.toJSON(diff));
        List<Map<String, Object>> resultList = new ArrayList<>();
        diff.forEach((k,v) -> {
            if (v.size() > 1){
                Map<String, Object> stringObjectMap = compareFields(v.get(0), v.get(1));
                resultList.add(stringObjectMap);
            }else {
                System.out.println(k+JSON.toJSONString(v,SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat));
            }
        });
        System.out.println(JSON.toJSON(resultList));
//        Map<String, List<Object>> map = list1.stream().collect(Collectors.toMap(
//                field -> {
//                    String name = null;
//                    try {
//                        name = getByFieldName("name", field);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                    return name;
//                },
//                o -> {
//                    List<Object> list = new ArrayList<>(diff.size());
//                    list.add(o);
//                    return list;
//                },
//                (List<Object> value1, List<Object> value2) -> {
//                    if (!Objects.equals(value1,value2)){
//                        value1.addAll(value2);
//                    }else {
//                        value1 = null;
//                    }
//                    return value1;
//                }
//        ));

    }

    public static Map<String,List<Object>> getDiff(List list1,List list2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Object> insertList = new ArrayList<>();
        List<Object> deleteList = new ArrayList<>();
        List<Object> updateList = new ArrayList<>();

        List<Object> diff = new ArrayList<>();
        Map<Object, Integer> map = new HashMap<Object, Integer>(list1.size() + list2.size());
        List<Object> maxList = list1;
        List<Object> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        for (Object o : maxList) {
            map.put(o, 1);
        }
        for (Object o : minList) {
            Integer count = map.get(o);
            if (count != null) {
                map.put(o, ++count);
                continue;
            }
            deleteList.add(o);
        }

        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1){
                if (list1.contains(entry.getKey()) && list2.contains(entry.getKey())){
                }
            }
        }
        return null;
    }


    public static String getByFieldName(String fieldName,Object o) throws IllegalAccessException {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().equals(fieldName)){
                declaredField.setAccessible(true);
                return (String)declaredField.get(o);
            }
        }
        return null;
    }

    public static Map<String, Object> compareFields(Object oldObject, Object newObject) {
        Map<String, Object> map = null;

        try{
            /**
             * 只有两个对象都是同一类型的才有可比性
             */
            if (oldObject.getClass() == newObject.getClass()) {
                map = new HashMap<String, Object>();

                Class clazz = oldObject.getClass();
                //获取object的所有属性
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,Object.class).getPropertyDescriptors();

                for (PropertyDescriptor pd : pds) {
                    //遍历获取属性名
                    String name = pd.getName();

                    //获取属性的get方法
                    Method readMethod = pd.getReadMethod();

                    // 在oldObject上调用get方法等同于获得oldObject的属性值
                    Object oldValue = readMethod.invoke(oldObject);
                    // 在newObject上调用get方法等同于获得newObject的属性值
                    Object newValue = readMethod.invoke(newObject);

                    if(oldValue instanceof Timestamp){
                        oldValue = new Date(((Timestamp) oldValue).getTime());
                    }

                    if(newValue instanceof Timestamp){
                        newValue = new Date(((Timestamp) newValue).getTime());
                    }

                    if(oldValue == null && newValue == null){
                        continue;
                    }else if(oldValue == null && newValue != null){
                        Map<String,Object> valueMap = new HashMap<String,Object>();
                        valueMap.put("oldValue",oldValue);
                        valueMap.put("newValue",newValue);
                        valueMap.put("type","insert");
                        map.put(name, valueMap);
                        continue;
                    }else if (oldValue != null && newValue == null){
                        Map<String,Object> valueMap = new HashMap<String,Object>();
                        valueMap.put("oldValue",oldValue);
                        valueMap.put("newValue",newValue);
                        valueMap.put("type","delete");
                        map.put(name, valueMap);
                        continue;
                    }else if (!oldValue.equals(newValue)) {// 比较这两个值是否相等,不等就可以放入map了
                        Map<String,Object> valueMap = new HashMap<String,Object>();
                        valueMap.put("oldValue",oldValue);
                        valueMap.put("newValue",newValue);
                        valueMap.put("type", "update");
                        map.put(name, valueMap);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return map;
    }
}
