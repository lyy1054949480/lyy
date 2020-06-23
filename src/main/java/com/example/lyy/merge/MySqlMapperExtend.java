package com.example.lyy.merge;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;


@RegisterMapper
public interface MySqlMapperExtend<T> {
    @Options(
            useGeneratedKeys = true
    )
    @InsertProvider(
            type = SpecialProviderExtend.class,
            method = "dynamicSQL"
    )
    int insertListExtend(List<? extends T> var1);
}
