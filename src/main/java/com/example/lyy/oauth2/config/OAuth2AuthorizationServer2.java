//package com.example.lyy.oauth2.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
////授权服务器配置
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2AuthorizationServer2 extends
//        AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private JwtAccessTokenConverter jwtAccessTokenConverter;
//    @Autowired
//    private TokenEnhancer jwtTokenEnhancer;
//
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;
//
//
//
//    @Autowired
//    DataSource dataSource;
//
//
////    @Bean
////    public TokenStore jwtTokenStore() {
////        return new JdbcTokenStore(dataSource);
////    }
////    @Bean
////    public TokenStore tokenStore() {
////        return new RedisTokenStore(redisConnectionFactory);
////    }
//
//    @Bean
//    public TokenStore jwtTokenStore(){
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients)
//            throws Exception {
////        clients.inMemory()
////            .withClient("clientapp")
////            .secret("112233")
////            .redirectUris("http://localhost:9393/test/callback")
////            // 授权码模式
////            .authorizedGrantTypes("authorization_code")
////            .scopes("read_userinfo", "read_contacts");
//        clients.withClientDetails(clientDetails());
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenStore(jwtTokenStore());
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        List<TokenEnhancer> enhancerList = new ArrayList<>();
//        enhancerList.add(jwtTokenEnhancer);
//        enhancerList.add(jwtAccessTokenConverter);
//        enhancerChain.setTokenEnhancers(enhancerList);
//        endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
//    }
//
//    @Bean
//    public ClientDetailsService clientDetails() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//        oauthServer
//                .passwordEncoder(new BCryptPasswordEncoder())
//                // 开启/oauth/token_key验证端口无权限访问
//                .tokenKeyAccess("permitAll()")
//                // 开启/oauth/check_token验证端口认证权限访问
//                .checkTokenAccess("isAuthenticated()");
//    }
//
//
//
//}