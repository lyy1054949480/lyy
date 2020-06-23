package com.example.lyy.feign;

import com.alibaba.fastjson.JSONObject;
import com.example.lyy.entity.App;
import com.example.lyy.entity.CommonUpdateQo;
import com.example.lyy.entity.UpdateData;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


public interface FeignService {

    @RequestLine("GET /productdetailsvc?id={id}")
    public String toProduct(@Param("id") String id);


    @Headers({"Content-Type: application/json"})
    @RequestLine("POST /applicationforms/med/manager")
    public JSONObject searchAppDetailPro(@RequestBody App app);


    @Headers({"jtpf.userId: {userId}"})
    @RequestLine("GET /order/getCalculatePremiumReq/{type}/{bizCode}")
    public JSONObject getCalculatePremiumReq(@Param("type") String type,@Param("bizCode") String bizCode,@Param("userId") String userId);


    @RequestLine("POST /test/api/mq")
    @Headers("name: {token}")
    void testHeader(@Param("token") String token);

    @RequestLine("POST /restructure/applicationform/{appCode}")
    @Headers("authorization: {authorization}")
    JSONObject restructure(@Param("appCode") String appCode,@Param("authorization") String authorization);

    @RequestLine("POST /common/update/01")
    @Headers({"Content-Type: application/json","authorization: {authorization}"})
    JSONObject update(@RequestBody UpdateData updateData,@Param("authorization")String authorization);

    @RequestLine("GET /common/update/01")
    @Headers({"Content-Type: application/json","authorization: {authorization}"})
    JSONObject getOrder(@RequestBody UpdateData updateData,@Param("authorization")String authorization);


}



