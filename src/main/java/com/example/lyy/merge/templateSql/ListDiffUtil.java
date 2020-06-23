package com.example.lyy.merge.templateSql;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListDiffUtil {

    public static <T>Map<String,List<T>> getListDiffDetail(List<T> oldList,List<T> newList){
        Map<String,List<Object>> diffMap = new HashMap<>();
        Map<Object, Integer> map = new HashMap<Object, Integer>(oldList.size() + newList.size());
        List<T> maxList = oldList;
        List<T> minList = newList;
        if (newList.size() > oldList.size()) {
            maxList = newList;
            minList = oldList;
        }
        for (T t : maxList) {
            map.put(t,1);
        }
        for (T t : minList) {
            Integer count = map.get(t);
            if (count != null) {
                map.put(t, ++count);
                continue;
            }
            map.put(t, 1);
        }
        for (Object o : map.keySet()) {
            if (map.get(o) > 1){

            }
        }
        return null;
    }
}
