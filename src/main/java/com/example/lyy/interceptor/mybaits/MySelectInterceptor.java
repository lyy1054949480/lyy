package com.example.lyy.interceptor.mybaits;

import com.example.lyy.aspect.CheckParam;
import com.example.lyy.entity.User;
import com.example.lyy.mapper.TUserMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        //select
       @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})
})
/**
 * 开启查询拦截器
 */
//@Component
public class MySelectInterceptor implements Interceptor {
    private final CheckParam checkParam = null;

    //拦截目标对象
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("-----------------prepare------------------");
        System.out.println("拦截的对象是：" + invocation.getTarget());
        System.out.println("拦截方法是：" + invocation.getMethod().toString());
        System.out.println("拦截参数长度是：" + invocation.getArgs().length);
        // 方法一
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();
        System.out.println(id);
        String substring = id.substring(id.lastIndexOf(".") + 1);
        System.out.println(substring);


        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        System.out.println(sqlCommandType);
        BoundSql boundSql = statementHandler.getBoundSql();
        //sql入参
        Object parameterObject = boundSql.getParameterObject();
        Object productCode = boundSql.getAdditionalParameter("productCode");
        System.out.println(productCode);
        //获取到原始sql语句
        String sql = boundSql.getSql();
        System.out.println(sql);
        System.out.println(parameterObject);
        /*if ("SELECT".equals(sqlCommandType)){
            BoundSql boundSql = statementHandler.getBoundSql();
            //sql入参
            Object parameterObject = boundSql.getParameterObject();
            //获取到原始sql语句
            String sql = boundSql.getSql();
            String mSql = sql;

            Field[] declaredFields = parameterObject.getClass().getDeclaredFields();

            Field field = boundSql.getClass().getDeclaredField("sql");
            field.setAccessible(true);
            field.set(boundSql, mSql);
            System.out.println(boundSql.getSql());
        }*/


        return invocation.proceed();
    }

    //包装目标对象 为目标对象创建动态代理
    public Object plugin(Object target) {
        // TODO Auto-generated method stub
        System.out.println("插件方法1--将要包装的目标对象1：" + target);
        //为当前对象创建代理对象
        Object o = Plugin.wrap(target, this);
        return o;
    }

    //获取插件初始化参数
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
		/*String value1 = (String) properties.get("shuxing1");
		String value2 = (String) properties.get("shuxing2");
		System.out.println("插件初始化参数1：" + value1 + ":" + value2);*/
    }

    private Boolean checkMethod(String abMethod) throws Exception {
        String substring = abMethod.substring(abMethod.lastIndexOf(".") + 1);
        if ("selectOne".equals(substring)){
            Class<?> aClass = Class.forName(abMethod.substring(0, abMethod.lastIndexOf(".")));
            Object o = aClass.newInstance();
            if (o instanceof TUserMapper){
                System.out.println(true);
            }
        }
        return true;
    }

}