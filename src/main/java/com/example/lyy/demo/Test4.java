package com.example.lyy.demo;

import com.google.common.base.Converter;
import org.apache.commons.io.FileUtils;

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
}
