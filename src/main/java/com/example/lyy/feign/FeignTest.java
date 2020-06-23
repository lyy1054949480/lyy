package com.example.lyy.feign;
import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.lyy.entity.*;
import com.example.lyy.util.auxiliary.FileUtil;
import feign.Feign;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

public class FeignTest {

    public static void main(String[] args) throws IOException {
        FeignService service = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(FeignService.class, "http://gw.jiangtai.com/ins-svc");

//        App app = new App();
//        app.setOrderCode("aa");

        Set<String> set = FileUtil.readFileByLines("D:\\888.txt");
        UpdateData updateData = new UpdateData();
        updateData.setAccount("17613726582");
        updateData.setName("lyy");
        updateData.setUserCode("19UC1655757051349176320");
        for (String appCode : set) {
            updateData.setAppCode(appCode);
            List<UpdateDetailQoListBean> list = new ArrayList<>();
            list.add(UpdateDetailQoListBean.builder().type("02").newValue(100).build());
            list.add(UpdateDetailQoListBean.builder().type("03").newValue(100).build());
            list.add(UpdateDetailQoListBean.builder().type("05").newValue(100).build());
            updateData.setUpdateDetailQoList(list);
            System.out.println(JSON.toJSONString(updateData));
            JSONObject order = service.getOrder(updateData, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhIjoiODk1NkY4ODZDNzk0QTUyMzdFNjA5MEY0M0UyNkM5QjMiLCJiIjoiMTlVQzE3MzI4OTI2MDI0MjcyNjE3MjEiLCJjIjoiMThSTDk3ODYxMCIsImQiOiJzeXNfdXNlcl9tYW4iLCJlIjoianRwZiIsImYiOiIxIiwiZXhwIjoxNTkzMDY0NzIwfQ.3dMwOWWzBe4KB3cuZvWxhbNFp5dz_qCwjmvSFKWYoEQ");
            System.out.println(JSON.toJSONString(order));
        }


//        int i = 1;
//        for (String s : set) {
//            System.out.println("---------------------"+s);
//            JSONObject order = service.getOrder(s, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhIjoiODk1NkY4ODZDNzk0QTUyMzdFNjA5MEY0M0UyNkM5QjMiLCJiIjoiMjBVQzE5Mjk1NTA3NzExMTEzMzM4NTQiLCJjIjoiMTlSTDE2MzcwMjEzMjE2NzIyNjE2MzIsMTlSTDIwMTk5OTcwMDA1LDE5UkwxNjM3MDIxMzIxNjcyMjYxNjMyLDE5UkwxNzI1OTcxODQ2NzE5MDc4Njg0LDE5UkwxNjQ4OTMxMzg2MjQzMTUzOTIwLDE5UkwxNjc2ODE4MTk2NTc5MjI1NjEyLDE5UkwyMDE5OTk3MDAwMiwxOVJMMTYzNzAyMTMyMTY3MjI2MTYzMiwxOVJMMTcyNTk3MTg0NjcxOTA3ODY4NCIsImQiOiJzeXNfdG91cl9tYW4iLCJlIjoidG91ciIsImYiOiIxIiwiZXhwIjoxNTkyMjkzODI0fQ.lf4NHz9J_rtHKnLPu3G_o6dmgcWs80nyqPUaLbZEl8k");
//            System.out.println(order);
//            Map parse = (Map) JSON.parse(JSON.toJSONString(order));
//            String dd = (String) parse.get("data");
//            if (StringUtils.isEmpty(dd)){
//                System.out.println("error" + s);
//            }else{
//                if (dd.contains("ExtendMessage")){
//                    String substring = dd.substring(dd.indexOf("ExtendMessage"), dd.indexOf("ExtendMessage") + 30);
//                    if (!substring.contains("orderExt")){
//                        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+s);
//                    }
//                }else{
//                    System.out.println(s + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//                }
//            }
//
//
//        }




        /*Set<String> set = FileUtil.readFileByLines("D:\\888.txt");
        List<String> list = new ArrayList<>();
        for (String s : set) {
            String[] split = s.split(",");
            JSONObject calculatePremiumReq = service.getCalculatePremiumReq("00",split[0]);
            Map map = (Map) JSON.parse(calculatePremiumReq.toJSONString());
            Object data = map.get("data");
            String temp = split[1] + data;
            list.add(temp);
        }
        System.out.println(list.size());
        FileUtil.write2LocalFold(list, "D:\\999.txt");*/


    }


}

