package com.example.lyy.demo.jsonpath;

import ch.qos.logback.core.util.ContextUtil;
import com.alibaba.fastjson.JSON;
import com.example.lyy.demo.Image;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.cache.CacheProvider;
import com.jayway.jsonpath.spi.cache.LRUCache;

import javax.xml.bind.SchemaOutputResolver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MyTest {

    public static void main(String[] args) throws MalformedURLException {
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
                System.out.println(map.size() + " === >" + map.get(key));
                return map.get(key);
            }

            @Override
            public void put(String key, JsonPath jsonPath) {
                System.out.println("放入缓存");
                System.out.println(map.size() + " === >" + map.get(key));

                map.put(key, jsonPath);
            }
        });


        String json = "{\"body\":{\"applicationForms\":[{\"information\":{\"applicationFormCode\":\"T2006101018376542639\"},\"insureds\":[{\"no\":1,\"insuranceObjectCount\":1,\"infos\":{\"human\":{\"birthday\":1514736000000,\"zipCode\":\"\",\"cityCode\":\"\",\"linkTel\":\"\",\"occupationType\":\"\",\"licenceType\":\"cardType_11\",\"national\":\"CHN\",\"fax\":\"\",\"extendList\":[{\"value\":\"\",\"key\":\"S_I_000314\"}],\"name\":\"dsf\"}}}],\"risks\":[{\"actualPrem\":100,\"afterPrem\":180,\"amount\":0,\"beforePrem\":100,\"cooloffPeriod\":0,\"duties\":[{\"dutyCode\":\"19ZR379993\",\"dutyDesc\":\"\",\"dutyLimits\":[{\"code\":\"19XE451776\",\"name\":\"每部电梯每次事故及累计责任限额\",\"value\":\"5000000\",\"valueCode\":\"19XEZ518028\"},{\"code\":\"19XE754942\",\"name\":\"每次事故每人责任限额\",\"value\":\"1000000\",\"valueCode\":\"19XEZ931893\"},{\"code\":\"19XE127463\",\"name\":\"每次事故每人伤亡责任限额\",\"value\":\"1000000\",\"valueCode\":\"19XEZ891821\"},{\"code\":\"19XE326278\",\"name\":\"每次事故每人医疗费用责任限额\",\"value\":\"500000\",\"valueCode\":\"19XEZ378073\"},{\"code\":\"19XE989820\",\"name\":\"每次事故每人财产损失责任限额\",\"value\":\"200000\",\"valueCode\":\"19XEZ882776\"},{\"code\":\"19XE169505\",\"name\":\"每次事故每人精神损害抚慰金责任限额\",\"value\":\"20000\",\"valueCode\":\"19XEZ882745\"},{\"code\":\"19XE690112\",\"name\":\"每次事故法律费用责任限额\",\"value\":\"300000\",\"valueCode\":\"19XEZ652211\"},{\"code\":\"19XE330658\",\"name\":\"每次事故及累计救援费用责任限额\",\"value\":\"300000\",\"valueCode\":\"19XEZ971988\"}],\"dutyName\":\"基本险\"},{\"dutyCode\":\"19ZR762186\",\"dutyDesc\":\"\",\"dutyLimits\":[{\"code\":\"19XE675189\",\"name\":\"每次事故每人滞留责任限额（滞留30分钟至1小时（含））\",\"value\":\"200\",\"valueCode\":\"19XEZ462709\"},{\"code\":\"19XE267453\",\"name\":\"每次事故每人滞留责任限额（滞留1小时以上）\",\"value\":\"400\",\"valueCode\":\"19XEZ162602\"},{\"code\":\"19XE907236\",\"name\":\"每部电梯每次事故及累计滞留责任限额\",\"value\":\"20000\",\"valueCode\":\"19XEZ229127\"}],\"dutyName\":\"附加轿厢滞留责任\"},{\"dutyCode\":\"19ZR601317\",\"dutyDesc\":\"\",\"dutyLimits\":[{\"code\":\"19XE728983\",\"name\":\"每部电梯每次事故及累计责任限额\",\"value\":\"5000000\",\"valueCode\":\"19XEZ668916\"},{\"code\":\"19XE636691\",\"name\":\"每次事故每人责任限额\",\"value\":\"1000000\",\"valueCode\":\"19XEZ514953\"},{\"code\":\"19XE348655\",\"name\":\"每次事故每人伤亡责任限额\",\"value\":\"1000000\",\"valueCode\":\"19XEZ907190\"},{\"code\":\"19XE783312\",\"name\":\"每次事故每人医疗费用责任限额\",\"value\":\"500000\",\"valueCode\":\"19XEZ403043\"},{\"code\":\"19XE684390\",\"name\":\"每次事故每人财产损失责任限额\",\"value\":\"200000\",\"valueCode\":\"19XEZ390155\"},{\"code\":\"19XE725924\",\"name\":\"每次事故每人精神损害抚慰金责任限额\",\"value\":\"20000\",\"valueCode\":\"19XEZ342293\"},{\"code\":\"19XE454855\",\"name\":\"每次事故法律费用责任限额\",\"value\":\"300000\",\"valueCode\":\"19XEZ553460\"},{\"code\":\"19XE253550\",\"name\":\"每次事故及累计救援费用责任限额\",\"value\":\"300000\",\"valueCode\":\"19XEZ308935\"}],\"dutyName\":\"附加雇员伤害责任\"}],\"kindCode\":\"\",\"kindName\":\"南京市电梯责任险\",\"mainRiskCode\":\"\",\"objCode\":\"\",\"prem\":100,\"programCode\":\"19FA249908\",\"quantity\":1,\"reducePrem\":0,\"riskCode\":\"19XZ275339\",\"riskFlag1\":\"00\",\"riskFlag2\":\"M\",\"riskGrade\":\"\",\"riskTypeCode\":\"0207\",\"riskTypeName\":\"责任保险\"}]}]}}\n";

//        String  jsonPath = "$.store.book.[*].['title', 'price']";
//        jsonPath = "$..book[?(@.isbn)]";//所有具有isbn属性的书
//        jsonPath = "$.store.book[?(@.price < 10)]";//所有价格小于10的书
//        jsonPath = "$..book[?(@.price < $['expensive'])]";//所有价格低于expensive字段的书
        ReadContext context = JsonPath.using(conf).parse(json);
//        System.out.println(JSON.toJSON(context.read(jsonPath)));
//        jsonPath = "$.body.applicationForms[?(@.information.applicationFormCode == 'T2006101018376542639')].information.extendList[?(@.fieldCode == 'name')]";
//        System.out.println(JSON.toJSON(context.read(jsonPath)));
//        jsonPath = "$.body.applicationForms[?(@.information.applicationFormCode == 'T2006101018376542639')].information.extendList";
//        System.out.println(JSON.toJSON(context.read(jsonPath)));
//
//        JsonPath compile = JsonPath.compile(jsonPath);
////        修改fieldCode为name的fieldValue的值
////        ((DocumentContext) context).set(compile,"lyy2");
//        Image lyy = Image.builder().name("lyy").data("111").build();
//        ((DocumentContext) context).add(compile, lyy);
////        ((DocumentContext) context).delete(compile);
//        System.out.println(JSON.toJSON(context.read("$.body")));
//
////        JsonPath compile = JsonPath.compile("$.store.book[1]");
////        ((DocumentContext) context).delete(compile);
////        JsonPath compile = JsonPath.compile("$.store");
////        ((DocumentContext) context).put(compile,"expensive","100");
////        Object read = context.read("$.store");
////        System.out.println(JSON.toJSON(read));
//        System.out.println(generateJsonPath("body.applicationForms.T2006101018376542639.risks"));
        String path = generateJsonPath("body.applicationForms.T2006101018376542639.insureds.1.infos.extendList.S_I_000314.value");
        System.out.println("path:----------------"+generateJsonPath("body.applicationForms.T2006101018376542639.insureds.1.infos.extendList.S_I_000314.value"));//risks.19XZ275339.duties.19ZR379993.dutyLimits.19XE451776.code
        JsonPath temp = JsonPath.compile(path);
        ((DocumentContext) context).set(temp,"qweeeeee");
        List read = context.read("$");
        System.out.println("result================="+JSON.toJSONString(read.get(0)));
        try {
            URL u = new URL("http://www.123.com");
            System.out.println("------" + u);
        } catch (Exception e) {

        }

    }

    public static String generateJsonPath(String fulPath) {
        StringBuilder jsonPath = new StringBuilder();
        String[] split = fulPath.split("\\.");
        for (int i = 0; i < split.length; i++) {
            System.out.println(i);
            if ("body".equals(split[i])) {
                jsonPath.append("$.body");
            } else if ("insureds".equals(split[i])) {
                jsonPath.append(".insureds" + (i + 1 < split.length ? "[?(@.no == '" + (split[i + 1]) + "')]" : ""));
                i++;
            } else if ("applicationForms".equals(split[i])) {
                jsonPath.append(".applicationForms" + (i + 1 < split.length ? "[?(@.information.applicationFormCode == '" + (split[i + 1]) + "')]" : ""));
                i++;
            } else if ("information".equals(split[i])) {
                jsonPath.append(".information" + (i + 1 < split.length ? "." + split[i + 1] : ""));
                if (i + 1 < split.length) {
                    if (!"extendList".equals(split[i + 1])) {
                        i++;
                    }
                }
            } else if ("benificiaries".equals(split[i])) {
                jsonPath.append(".benificiaries" + (i + 1 < split.length ? "[?(@.no == '" + (split[i + 1]) + "')]" : ""));
                i++;
            } else if ("insuranceObjects".equals(split[i])) {
                jsonPath.append(".insuranceObjects" + (i + 1 < split.length ? "[?(@.no == '" + (split[i + 1]) + "')]" : "") + (i + 2 < split.length ? "." + split[i + 2] : ""));
                if (i + 2 < split.length) {
                    if (!"extendList".equals(split[i + 2])) {
                        i++;
                    }
                }
            } else if ("policy".equals(split[i])) {
                jsonPath.append(".policy" + (i + 1 < split.length ? "." + split[i + 1] : ""));
                i++;
            } else if ("insuranceCompanies".equals(split[i])) {
                jsonPath.append(".insuranceCompanies" + (i + 1 < split.length ? "[?(@.code == '" + (split[i + 1]) + "')]" : "") + (i + 2 < split.length ? "." + split[i + 2] : ""));
                i++;
            } else if ("infos".equals(split[i])) {
                jsonPath.append(".infos.*" + (i + 1 < split.length ? "." + split[i + 1] : ""));
                if (i + 1 < split.length) {
                    if (!"extendList".equals(split[i + 1])) {
                        i++;
                    }
                }
            } else if ("extendList".equals(split[i])) {
                jsonPath.append((i + 1 < split.length ? "[?(@.key == '" + split[i + 1] + "')]" : "") + (i + 2 < split.length ? "." + split[i + 2] : ""));
                i++;
            } else if ("risks".equals(split[i])) {
                jsonPath.append(".risks" + (i + 1 < split.length ? "[?(@.riskCode == '" + split[i + 1] + "')]" : "") + (i + 2 < split.length ? "." + split[i + 2] : ""));
                i++;
            } else if ("duties".equals(split[i])) {
                jsonPath.append((i + 1 < split.length ? "[?(@.dutyCode == '" + split[i + 1] + "')]" : "") + (i + 2 < split.length ? "." + split[i + 2] : ""));
                i++;
            } else if ("dutyLimits".equals(split[i])) {
                jsonPath.append((i + 1 < split.length ? "[?(@.code == '" + split[i + 1] + "')]" : "")
                        + (i + 2 < split.length ? "." + split[i + 2] : ""));
                i = split.length - 1;
            } else if ("attachments".equals(split[i])) {
                jsonPath.append((i + 1 < split.length ? ".[?(@.id == '" + split[i + 1] + "')]" : ""));
            } else {
                System.out.println(split[i]);
            }
        }
        return jsonPath.toString();
    }
}
