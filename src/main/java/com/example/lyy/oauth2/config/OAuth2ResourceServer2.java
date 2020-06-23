//package com.example.lyy.oauth2.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
////资源服务配置
//@Configuration
//@EnableResourceServer
//public class OAuth2ResourceServer2 extends ResourceServerConfigurerAdapter {
//
//    @Override//http访问的安全配置
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/v2/api-docs","/swagger-resources/configuration/ui",
//                        "/swagger-resources", "/swagger-resources/configuration/security",
//                        "/swagger-ui.html","/webjars/**").permitAll()
//                .anyRequest()
//                .authenticated()
//        ;
//    }
//
//
//}