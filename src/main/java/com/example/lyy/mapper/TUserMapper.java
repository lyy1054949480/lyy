package com.example.lyy.mapper;

import com.example.lyy.entity.TUser;
import com.example.lyy.merge.MyMapper;

public interface TUserMapper extends MyMapper<TUser> {

    TUser selectById();
}