package com.example.lyy.util.invoke;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReflectionFieldUtil {

    public static Object getGetMethod(Object ob,String name) throws ClassCastException, InvocationTargetException, IllegalAccessException {
        if(ob.getClass().getSimpleName().contains("List")){
            List list = (List)ob;
            if(!list.isEmpty()){
                String value = "";
                for(Object object:list){
                    value = value + "," + getGetMethod(object,name);
                }
                value = value.substring(1);
                return value;
            }
        }else {
            Method[] m = ob.getClass().getMethods();
            if(m.length>0){
                for(int i = 0;i < m.length;i++){
                    if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                        return m[i].invoke(ob);
                    }
                }
            }
        }
        return null;
    }

    public static Object getMethod(Object object,Object name){
        Class<?> aClass = object.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if(method.getName().toLowerCase().equals(("get"+name).toLowerCase())){
                try {
                    Object invoke = method.invoke(object);
                    return invoke;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Object setMethod(Object object,Object name,Object parameter){
        Class<?> aClass = object.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if(method.getName().toLowerCase().equals(("set"+name).toLowerCase())){
                try {
                    return method.invoke(object,parameter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    public static String[] getAnnotation2(Object o) {
        Class<?> aClass = o.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        String key = null;
        List<String> annotations = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            List<Annotation> annotationList = Arrays.asList(declaredAnnotations).stream().filter(item -> "ApiModelProperty".equals(item.annotationType().getSimpleName())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(annotationList)) {
                ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
                key = annotation.value();
                annotations.add(key);

            }
        }
        return annotations.toArray(new String[annotations.size()]);
    }


    public static StringBuilder fieldCheckIsNull(Object object,String ... ingoreName){
        Class clazz = object.getClass();
        Field fields[] = clazz.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for(Field field : fields){
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                String fieldName = field.getName();
                for (String name : ingoreName) {
                    if (!Objects.equals(fieldName,name)){
                        fieldValue = field.get(object);
                        if(StringUtils.isEmpty(fieldValue)){
                            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                            String value = annotation.value();
                            stringBuilder.append(","+value+"不能为空");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder;
    }
}
