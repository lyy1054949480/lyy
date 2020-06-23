package com.example.lyy.interceptor.mybaits;

import com.alibaba.fastjson.JSON;
import com.example.lyy.util.encryption.Base64Util;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

@Intercepts(
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
)
//@Component
public class MyDecodeIntercepter implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Executor executor = (Executor) invocation.getTarget();
        MappedStatement ms = (MappedStatement) args[0];
        String id = ms.getId();
        SqlCommandType sqlCommandType = ms.getSqlCommandType();
        Object parameter = args[1];
        Object pageParameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = boundSql.getSql();
        if ("SELECT LAST_INSERT_ID()".equals(sql)){
            return invocation.proceed();
        }
        List resultList = executor.query(ms, parameter, rowBounds, resultHandler);
        for (Object o2 : resultList) {
            System.out.println(JSON.toJSON(o2).toString());
        }
        System.out.println(JSON.toJSON(resultList));
        return resultList;
    }
 
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
 
    @Override
    public void setProperties(Properties properties) {
 
    }

    private void encodeFeild(Field[] declaredFields,Object parameter,SqlCommandType sqlCommandType) throws Exception {
        for (Field field: declaredFields) {
            if (SqlCommandType.SELECT.equals(sqlCommandType)) {
                if (field.getName().equals("productCode")) {
                    field.setAccessible(true);
                    field.set(parameter, Base64Util.decode((String)field.get(parameter)));
                }
            }
        }
    }

}
