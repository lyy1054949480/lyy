package com.example.lyy.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.lyy.entity.User;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class Test3 {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("aaaaaaa");
        list.add("asdasdasdasd");
        List<String> list1 = IOUtils.readLines(new FileInputStream("D:\\888.txt"));
        HashSet<String> strings = new HashSet<>(list1);
        for (String string : strings) {
            System.out.println(string);
        }
        File file = new File("D:\\123456.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        IOUtils.writeLines(list,IOUtils.LINE_SEPARATOR,bufferedOutputStream);
        bufferedOutputStream.flush();
//        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lyy?characterEncoding=utf8&allowMultiQueries=true","root","root");
//        List<Object> t_user = getTableInsertSql(conn, "t_user", "");
//        System.out.println(JSON.toJSONString(t_user));
//        Object o = Class.forName("java.lang.String").newInstance();
//        System.out.println(o);
//        User user = new User();
//        Map<String, Object> stringObjectMap = javaBeanToMap(user,new HashMap());
//        System.out.println(JSON.toJSONString(stringObjectMap, SerializerFeature.WriteMapNullValue));
    }


    public static List<Object> getTableInsertSql(Connection conn, String tableName, String where) throws Exception {
        ResultSet rs = null;
        Statement statement = null;
        List<Object> list = null;
        try {
            DatabaseMetaData metadata = conn.getMetaData();
            rs = metadata.getColumns(null, null, tableName, "%"); //得到表的字段列表
            String sql = "select 'insert into " + tableName + " values ( '";
            int count = 0;
            int counts = 0;
//获得列的总数
            while (rs.next()) {
                count++;
            }
//重新获得列数据 整理成sql
            rs = metadata.getColumns(null, null, tableName, "%"); //得到表的字段列表
            while (rs.next()) {
                counts++;
                if (counts <= count) {
                    Object colName = rs.getObject("column_name");
                    sql += " ||'''' ||" + colName + "|| ''','";
                }
            }
            sql = sql.substring(0, sql.length() - 2) + "'";
            sql += " || ' );' from " + tableName + where;
            rs.close();

// System.out.println("DEBUG: SQL="+sql);
//执行
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

//将SQL语句放到List中
            list = new ArrayList<Object>();
            while (rs.next())
                list.add(rs.getObject(1));
            rs.close();

//System.out.println(list.size());
        } finally {
            if (rs != null)
                rs.close();
            if (statement != null)
                statement.close();
            conn.close();
        }
        return list;
    }



    /**
     * javaBean转map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> javaBeanToMap(Object obj, Map map) throws Exception {
        // 获取javaBean的BeanInfo对象
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        // 获取属性描述器
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 获取属性名
            String key = propertyDescriptor.getName();
            String name = propertyDescriptor.getPropertyType().getName();
            Field declaredField = obj.getClass().getDeclaredField(toLowerCaseFirstOne(key));
            ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
            if ("java.util.List".equals(name)) {
                ParameterizedType genericType = (ParameterizedType) declaredField.getGenericType();
                Type[] actualTypeArguments = genericType.getActualTypeArguments();
                map.put(key + (annotation == null ? "" : annotation.value()), javaBeanToMap(Class.forName(actualTypeArguments[0].getTypeName()).newInstance(), new HashMap()));
                continue;
            }
            // 获取该属性的值
            Method readMethod = propertyDescriptor.getReadMethod();
            // 通过反射来调用javaBean定义的getName()方法
            Object value = readMethod.invoke(obj);
            map.put(toLowerCaseFirstOne(key) + (annotation == null ? "" : annotation.value()), value);
        }
        return map;
    }


    public static Map<String, Object> javaBeanToMap2(Object obj,Map map) throws Exception {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0){
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                declaredField.setAccessible(true);
                ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
                String type = declaredField.getType().getName();
                if ("java.util.List".equals(type)){
                    ParameterizedType genericType = (ParameterizedType) declaredField.getGenericType();
                    Type[] actualTypeArguments = genericType.getActualTypeArguments();
                    map.put(name + (annotation == null ? "" : annotation.value()), javaBeanToMap2(Class.forName(actualTypeArguments[0].getTypeName()).newInstance(), new HashMap()));
                    continue;
                }
                String property = BeanUtils.getProperty(obj,toLowerCaseFirstOne(name));
                map.put(name + (annotation == null ? "" : annotation.value()), property);
            }
        }
        return map;
    }

    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }




}
