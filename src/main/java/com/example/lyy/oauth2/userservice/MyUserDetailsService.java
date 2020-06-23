/*
package com.example.lyy.oauth2.userservice;

import com.example.lyy.entity.TUser;
import com.example.lyy.mapper.TUserMapper;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
            }
            return null;
        }
        String pwd = "";
        System.out.println("收到的账号"+username);

        TUser tUser = new TUser();
        tUser.setUid(1);
        tUser = tUserMapper.selectOne(tUser);
        if (tUser == null){
            throw new RuntimeException("登录账号不存在");
        }else{
            pwd = tUser.getName();
        }
        System.out.println("查到的密码"+tUser.getName());
        return new User(username, pwd, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));

    }
}
*/
