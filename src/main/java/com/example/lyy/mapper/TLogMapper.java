package com.example.lyy.mapper;

import com.example.lyy.entity.TLog;
import com.example.lyy.merge.MyMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//@CacheConfig()
public interface TLogMapper extends MyMapper<TLog>{

//    @Cacheable(cacheNames = "users",key = "#root.method.name+'.'+#root.methodName+':'+#root.args[0].getProductCode()",sync = true)
//    @Cacheable(cacheNames = "test",keyGenerator = "myKeyGenerator")
    TLog selectLog(TLog tlog);

    LinkedList<String> selectFullColumnsByTableName(String tableName);
}