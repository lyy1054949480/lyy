//package com.example.lyy.oauth2.config;
//
//import com.example.lyy.oauth2.access.CustomAccessDecisionManager;
//import com.example.lyy.oauth2.access.CustomSecurityMetadataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
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
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
//                        fsi.setAccessDecisionManager(accessDecisionManager());
//                        fsi.setSecurityMetadataSource(securityMetadataSource());
//                        return fsi;
//                    }
//                });
//    }
//
//    @Bean
//    public AccessDecisionManager accessDecisionManager() {
//        return new CustomAccessDecisionManager();
//    }
//
//    @Bean
//    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
//        return new CustomSecurityMetadataSource();
//    }
//
//
//}