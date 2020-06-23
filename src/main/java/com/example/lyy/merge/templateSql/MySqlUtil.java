package com.example.lyy.merge.templateSql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.example.lyy.merge.MyMapper;
import org.apache.ibatis.binding.MapperMethod.MethodSignature;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.util.CollectionUtils;

/**
 * @author zwxbest - 19-4-25
 */
public class MySqlUtil {

    public static String showSql(SqlSession sqlSession, MyMapper myMapper, String methodName,
                                 Object[] params) {
        if (!Proxy.isProxyClass(myMapper.getClass())){
            throw new RuntimeException("mapper必须是代理对象");
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(myMapper);
        if (null==invocationHandler){
            throw new RuntimeException("mapper必须是有处理器的代理对象");
        }
        Field fieldMapper;
        try {
            fieldMapper = invocationHandler.getClass().getDeclaredField("mapperInterface");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("从mapper代理对象中获取不到mapperInterface", e);
        }
        fieldMapper.setAccessible(true);
        Class<?> mapperInterface;
        try {
            mapperInterface = (Class<?>) fieldMapper.get(invocationHandler);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("从mapper代理对象中获取mapperInterface失败，不应该出现此异常", e);
        }
        Configuration configuration = sqlSession.getConfiguration();
        MappedStatement ms = configuration.getMappedStatement(
                mapperInterface.getName() + "." + methodName);

        Method sqlMethod = null;

        //find method equals methodName
        for (Method method : mapperInterface.getMethods()) {
            if (method.getName().equals(methodName)) {
                sqlMethod = method;
                break;
            }
        }
        if (sqlMethod == null) {
            throw new RuntimeException("mapper method is not found");
        }

        MethodSignature method = new MethodSignature(configuration, mapperInterface, sqlMethod);
        Object paramObject = method.convertArgsToSqlCommandParam(params);
        BoundSql boundSql = ms.getBoundSql(paramObject);
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql
                .getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration
                    .getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?",
                        Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                MetaObject metaObject = configuration.newMetaObject(
                        parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql
                                .replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql
                                .replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        sql = sql.replaceFirst("\\?", "missing");
                    }
                }
            }
        }
        return sql;
    }

    /**
     * if param's type is `String`,add single quotation<br>
     * <p>
     * if param's type is `datetime`,convert to string and quote <br>
     */
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
//            DateFormat formatter = DateFormat
//                    .getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//            value = "'" + formatter.format(new Date()) + "'";
            value = "'" + sdf.format(obj) + "'";
        } else if (obj instanceof LocalDateTime) {
            value = "\'" + ((LocalDateTime) obj)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }
}