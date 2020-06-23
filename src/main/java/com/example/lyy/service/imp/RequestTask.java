package com.example.lyy.service.imp;


import com.example.lyy.util.HttpClientUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTask implements Callable<String> {

    private String code;


    @Override
    public String call() throws Exception {
        Map<String,String> httpMap = new HashMap<>();
        httpMap.put("param",code);
        httpMap.put("url","http://prod-svc-dev.jiangtai.com/productdetailsvc");
        return HttpClientUtil.doHttpGet(httpMap);
    }
}
