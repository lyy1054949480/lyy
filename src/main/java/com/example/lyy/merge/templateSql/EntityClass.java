package com.example.lyy.merge.templateSql;

import com.example.lyy.entity.TLog;
import com.example.lyy.merge.MyMapper;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.util.MsUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class EntityClass {
    @Autowired
    SqlSession sqlSession;

    public Class<?> getEntityClass() {
        List<TLog> list = new ArrayList<>();
        list.add(TLog.builder().productCode("11111").id(123124124124l).build());
        list.add(TLog.builder().productCode("22222").id(34324234234l).build());
        MappedStatement mappedStatement = sqlSession.getConfiguration().getMappedStatement("com.example.lyy.mapper.TLogMapper.insert");
        return null;
    }
}
