package com.example.lyy.baidu.face;
 

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
 
import java.util.HashMap;
import java.util.Map;



public class FaceAPITest {
 
    public static void main(String[] args) {
        //  getToKenTest() ;
        String toKenTest = getToKenTest();
        faceDetecttest(toKenTest);
    }
 
    //获取token
    public static String getToKenTest(){
 
        //使用其测试百度云API---获取token
        //url: http://console.bce.baidu.com/ai
 
        String APPID ="19108999"; //管理中心获得
 
        //百度人脸识别应用apikey
        String API_KEY = "YrkSliqxd8ufflbkUejw5Ttp"; //管理中心获得
 
        //百度人脸识别应用sercetkey
        String SERCET_KEY = "U0nIG2iKVOyzPcFRcB9lvVTGYK5kGZ2X"; //管理中心获得
 
        //百度人脸识别token 有效期一个月
        String TOKEN = null;
 
 
        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials"
                +"&client_id="+API_KEY +"&client_secret="+SERCET_KEY;
 
        CloseableHttpResponse response =  HttpClientUtils.doHttpsGet(access_token_url,null);
        return HttpClientUtils.toString(response);
 
    }
 
    //使用token调用API
    public static void faceDetecttest(String token){
        Map parse = (Map) JSON.parse(token);
        System.out.println(parse.get("access_token"));
        String Filepath = "E:/1.jpg";
        String image = Base64ImageUtils.GetImageStrFromPath(Filepath);
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect?access_token="+parse.get("access_token");
 
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
 
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", image);
        bodys.put("image_type","BASE64");
        bodys.put("face_field", "age,beauty,expression,gender,glasses,race,qualities");
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            String s = HttpClientUtils.toString(response);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
 
 
    }
 
}
