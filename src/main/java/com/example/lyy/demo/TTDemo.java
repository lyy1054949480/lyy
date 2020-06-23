package com.example.lyy.demo;

import java.util.ArrayList;
import java.util.List;

public class TTDemo {
    public static void main(String[] args){
        TTDemo ttDemo = new TTDemo();

        // 获取String类型
        List<String> array = new ArrayList<>();
        array.add("aaa");
        array.add("bbb");
        String str =  ttDemo.getListFirst(array);
        System.out.println(str);

        // 获取Number类型
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        Integer num = ttDemo.getListFirst(nums);
        System.out.println(num);
    }

    private <T> T getListFirst(List<T> data){
        if(data == null || data.size() == 0){
            return null;
        }
        return data.get(0);
    }
}