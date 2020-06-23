package com.example.lyy.demo.jsonpath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.cache.Cache;
import com.jayway.jsonpath.spi.cache.CacheProvider;
import com.jayway.jsonpath.spi.cache.LRUCache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {

    public static void main(String[] args) {
        /*ALWAYS_RETURN_LIST  总是返回list，即便是一个确定的非list类型，也会被包装成list。
        SUPPRESS_EXCEPTIONS 不抛出异常，需要判断如下：
        {
        ALWAYS_RETURN_LIST开启，则返回空list
        ALWAYS_RETURN_LIST关闭，则返回null
        }
        AS_PATH_LIST 返回path
        REQUIRE_PROPERTIES
        如果设置，则不允许使用通配符，比如$[*].b，会抛出PathNotFoundException异常。
        */
        Configuration conf = Configuration.builder()
                .options(Option.SUPPRESS_EXCEPTIONS).options(Option.ALWAYS_RETURN_LIST).build();

        CacheProvider.setCache(new LRUCache(10) {
            private Map<String, JsonPath> map = new HashMap<String, JsonPath>();

            @Override
            public JsonPath get(String key) {
                System.out.println("读取缓存");
                System.out.println(map.size() + " === >"+map.get(key));
                return map.get(key);
            }

            @Override
            public void put(String key, JsonPath jsonPath) {
                System.out.println("放入缓存");
                System.out.println(map.size() + " === >"+map.get(key));

                map.put(key, jsonPath);
            }
        });

        String json = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";

        String  jsonPath = "$.store.book.[*].['title', 'price']";
        jsonPath = "$.store.book[1].author";
//        jsonPath = "$..book[?(@.isbn)]";//所有具有isbn属性的书
//        jsonPath = "$.store.book[?(@.price < 10)]";//所有价格小于10的书
//        jsonPath = "$..book[?(@.price <= $['expensive'])]";//所有价格低于expensive字段的书
        ReadContext context = JsonPath.using(conf).parse(json);
        System.out.println("---=----"+JSON.toJSON(context.read(jsonPath)));
//        JsonPath compile = JsonPath.compile("$.store.book[1]");
//        ((DocumentContext) context).delete(compile);
        JsonPath compile = JsonPath.compile("$.store");
        ((DocumentContext) context).put(compile,"expensive","100");
        Object read = context.read("$.store");
        System.out.println(JSON.toJSON(read));
    }
}
