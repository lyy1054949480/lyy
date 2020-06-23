package com.example.lyy.demo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Test4 {
    public static void main(String[] args) throws IOException {
        List<String> list = FileUtils.readLines(new File("D:\\888.txt"));
        int i = 0;
        for (String url : list) {
            FileUtils.copyURLToFile(new URL(url),new File("E:\\image\\"+ i + ".jpg"));
            i++;
        }
    }
}
