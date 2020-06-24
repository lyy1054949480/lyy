package com.example.lyy.demo;

import com.example.lyy.util.invoke.IntrospectorUtil;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import org.apache.commons.io.FileUtils;
import org.springframework.aop.IntroductionInfo;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

public class Test4 {
    public static void main(String[] args) throws Exception {
        Image image = Image.builder().name("1111").build();
        PropertyDescriptor descriptor = new PropertyDescriptor("name", image.getClass());
        //字段名
        System.out.println(descriptor.getName());
        //字段类型
        System.out.println(descriptor.getPropertyType().getName());
        //get
        Method readMethod = descriptor.getReadMethod();
        Object invoke = readMethod.invoke(image);
        System.out.println(invoke);
        //set
        Method writeMethod = descriptor.getWriteMethod();
        Object invoke1 = writeMethod.invoke(image, "2222");
        System.out.println(image.getName());
//        List<String> list = FileUtils.readLines(new File("D:\\888.txt"));
//        int i = 0;
//        for (String url : list) {
//            FileUtils.copyURLToFile(new URL(url),new File("E:\\image\\"+ i + ".jpg"));
//            i++;
//        }
    }

    public void testSpringUtils(){
        AnnotationUtils.findAnnotation(this.getClass(),ApiModel.class);
        ReflectionUtils.findField(this.getClass(),"name");
    }
}
