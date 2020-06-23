package com.example.lyy.demo;



import com.alibaba.fastjson.JSON;
import com.example.lyy.entity.InfoList;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class ListUtil {

    public static void main(String[] args) throws Exception {
        List<InfoList> list = new ArrayList<>();
        list.add(InfoList.builder().id("2").parentId("1").name("111").build());
        list.add(InfoList.builder().id("3").parentId("1").name("222").build());
        list.add(InfoList.builder().id("1").name("test").build());
        list.add(InfoList.builder().id("4").parentId("1").name("333").build());
        list.add(InfoList.builder().id("5").parentId("2").name("333").build());
        list.add(InfoList.builder().id("6").parentId("5").name("333").build());
        list.add(InfoList.builder().id("7").parentId("5").name("333").build());
        list.add(InfoList.builder().id("8").parentId("4").name("333").build());

        List<InfoList> list1 = getList(list);
        System.out.println(JSON.toJSON(list1));
        List list2 = groupListByQuantity(list, 2);
        System.out.println(JSON.toJSON(list2));
        Map map = spiltList(list,2);
        System.out.println(JSON.toJSON(map));

    }



    public static <K> List<K> getList(List<K> objectList) throws Exception{
        List<K> resultList = new LinkedList<>();
        for (K o : objectList) {
            if (StringUtils.isEmpty(getValueByField(o,"parentId"))){
                resultList.add(o);
            }
        }
        if (!CollectionUtils.isEmpty(resultList)){
            resultList.forEach(item -> {
                try {
                    List<K> childrenList = getChildrenList(getValueByField(item,"id"),objectList);
                    if (!CollectionUtils.isEmpty(childrenList)){
                        setValueByField(item,"childrenList",childrenList);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            });
        }

        return resultList;

    }


    public static <K> List<K> getChildrenList(Object parentId,List<K> objectList){
        List<K> childrenResultList = new ArrayList<>();
        objectList.forEach(item -> {
            Object pid = null;
            try {
                pid = getValueByField(item, "parentId");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (pid != null && parentId.equals(pid)){
                childrenResultList.add(item);
            }
        });

        if (!CollectionUtils.isEmpty(childrenResultList)){
            childrenResultList.forEach(item -> {
                try {
                    setValueByField(item,"childrenList",getChildrenList(getValueByField(item, "id"), objectList));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }

        if (CollectionUtils.isEmpty(childrenResultList)){
            return null;
        }

        return childrenResultList;
    }


    public static Object getValueByField(Object o,String name) throws IllegalAccessException {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0){
            for (Field declaredField : declaredFields) {
                if (name.equals(declaredField.getName())){
                    declaredField.setAccessible(true);
                    return declaredField.get(o);
                }
            }
        }
        return null;
    }


    public static void setValueByField(Object o,String name,Object value) throws IllegalAccessException {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0){
            for (Field declaredField : declaredFields) {
                if (name.equals(declaredField.getName())){
                    declaredField.setAccessible(true);
                    declaredField.set(o,value);
                }
            }
        }
    }


    public static List groupListByQuantity(List list, int quantity) {
        if (list == null || list.size() == 0) {
            return list;
        }

        if (quantity <= 0) {
            new IllegalArgumentException("Wrong quantity.");
        }

        List wrapList = new ArrayList();
        int count = 0;
        while (count < list.size()) {
            wrapList.add(list.subList(count, (count + quantity) > list.size() ? list.size() : count + quantity));
            count += quantity;
        }

        return wrapList;
    }

    public static Map spiltList(List historyList, int quantity) {
        int listSize = historyList.size();
        Map map = new HashMap();     //用map存起来新的分组后数据
        int keyToken = 0;
        for (int i = 0; i < historyList.size(); i += quantity) {
            if (i + quantity > listSize) {        //作用为toIndex最后没有100条数据则剩余几条newList中就装几条
                quantity = listSize - i;
            }
            List newList = historyList.subList(i, i + quantity);
            map.put(keyToken, newList);
            keyToken++;
        }
        System.out.println("一共切分了" + map.size() + "个list");
        return map;
    }

}
