package com.example.lyy.demo;

import com.example.lyy.util.invoke.IntrospectorUtil;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import org.apache.commons.io.FileUtils;
import org.springframework.aop.IntroductionInfo;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Test4 {
    public static void main(String[] args) throws IOException {
        System.out.println(2218325903732378824L >> 10);
        System.out.println(2166333890363651l << 10);

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
