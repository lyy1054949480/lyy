package com.example.lyy;

import com.example.lyy.netty.server.NettyServer;
import com.example.lyy.util.MyAllEncompassingFormHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import java.nio.charset.Charset;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.example.lyy.mapper"})
//@EnableAuthorizationServer
@EnableCaching//开启缓存
//@EnableTransactionManagement(proxyTargetClass = true)

public class LyyApplication {

    public static void main(String[] args) {

        SpringApplication.run(LyyApplication.class, args);
        try {
            new NettyServer(12345).start();
            System.out.println("http://127.0.0.1:9393/netty-websocket/index");
        }catch(Exception e) {
            System.out.println("NettyServerError:"+e.getMessage());
        }

    }


    @Bean(name = "restTemplate")
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
//
//    @Bean
//    @LoadBalanced
//    protected OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource,
//                                                    OAuth2ClientContext context) {
//        return new OAuth2RestTemplate(resource, context);
//    }

    @Bean(name = "resRestTemplate")
    @LoadBalanced
    public RestTemplate resRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        int i = 0;
        for (HttpMessageConverter<?> messageConvert : converterList) {
            if (messageConvert instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) messageConvert).setDefaultCharset(Charset.forName("UTF-8"));
            } else if (messageConvert instanceof AllEncompassingFormHttpMessageConverter) {
                //解決FormHttpMessageConverter 中getAsciiBytes(String name) 方法造成上传文件名乱码
                converterList.set(i, new MyAllEncompassingFormHttpMessageConverter());
            }
            i++;
        }
        return restTemplate;
    }


}
