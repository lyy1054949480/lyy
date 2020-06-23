package com.example.lyy.demo;

import java.util.ArrayList;
import java.util.List;

public class TDemo<T> {
    public static void main(String[] args){

        // 获取String类型
        TDemo<String> tDemo = new TDemo<>();  // 限制"T"为String类型
        List<String> array = new ArrayList<>();
        array.add("aaa");
        array.add("bbb");
        String str =  tDemo.getListFirst(array);
        System.out.println(str);

        // 获取Number类型
        TDemo<Integer> tDemo1 = new TDemo<>();  // 限制"T"为Integer类型
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        Integer num = tDemo1.getListFirst(nums);
        System.out.println(num);
    }

    private T getListFirst(List<T> data){
        if(data == null || data.size() == 0){
            return null;
        }
        return data.get(0);
    }
}