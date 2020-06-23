package com.example.lyy.demo;

import com.example.lyy.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Factory {

    public static Map<String, User> map = new HashMap<>();

    static {
        map.put("1",User.builder().username("222").build());

    }

    public static  User getUser(String name){
        return map.get(name);
    }

}
