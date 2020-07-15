//package com.example.lyy.oauth2.token;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2RefreshToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class JwtTokenEnhancer implements TokenEnhancer {
//    @Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
//        String userName = oAuth2Authentication.getUserAuthentication().getName();
//        // 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
//        User user = (User) oAuth2Authentication.getUserAuthentication().getPrincipal();
//        //自定义一些token属性
//
//        final Map<String, Object> additionalInformation = new HashMap<>();
//        additionalInformation.put("userName", userName);
//        additionalInformation.put("roles", user.getAuthorities());
//        //设置附加信息
//        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInformation);
//        OAuth2RefreshToken refreshToken = oAuth2AccessToken.getRefreshToken();
//        String accessToken = oAuth2AccessToken.getValue();
//        System.out.println(accessToken);
//        System.out.println(refreshToken);
//        return oAuth2AccessToken;
//    }
//}
