package com.example.lyy.service;


import com.alibaba.fastjson.JSONObject;
import com.example.lyy.domain.ResponseData;
import com.example.lyy.entity.AreaCode;
import com.example.lyy.entity.Json;
import com.example.lyy.entity.Mapping;
import com.example.lyy.entity.TFullpathElementDef;
import com.example.lyy.thread.MyRejectedPolicy;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public interface TestService {

    /**
     *查询产品编码是否投保
     * @param data
     * @return
     */
    public void checkProduct(String data, CountDownLatch begin,CountDownLatch excute) throws Exception;

    public List<AreaCode> packAreaJson(String json);

    public List<Date> searchDate();

    List<Json> cacheTest() throws Exception;

    String configProduct(String json,String entryCode,String exitCode);


    public JSONObject searchProByDate(List<Date> dates);

    public JSONObject insertItemJson(JSONObject jsonObject,StringBuilder stringBuilder);

    public List<TFullpathElementDef> testProvider();

    void testMapperProvider(String column);

    void diff() throws IOException;

    List<Json> exportMedData();

    public void htmlToPdf() throws UnsupportedEncodingException, Exception;

    ResponseData checkData() throws IOException, Exception;

    ResponseData selectUser(String anme);

}
