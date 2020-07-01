package com.example.lyy.demo;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * 操作文件
 */
public class Test5 {

    public static void main(String[] args) {
        // java 8
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\888.txt"))){
            LinkedList<String> linkedList = bufferedReader.lines().collect(Collectors.toCollection(LinkedList::new));
        }catch (Exception e){
            e.printStackTrace();
        }

        //解压缩

    }
}
