package com.example.lyy.demo;

import com.alibaba.fastjson.JSON;
import com.example.lyy.util.auxiliary.FileUtil;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 操作文件
 */
public class Test5 {

    public static void main(String[] args) throws IOException {
        // java 8
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\888.txt"))){
            LinkedList<String> linkedList = bufferedReader.lines().collect(Collectors.toCollection(LinkedList::new));
        }catch (Exception e){
            e.printStackTrace();
        }

        //递归删除文件
//        FileUtils.deleteDirectory(new File("D:\\zhilang22"));//递归删除其包含文件及子目录文件
//        FileUtils.cleanDirectory(new File("D:\\testDel"));//清除该目录下的文件及子目录文件而不删除该目录文件夹。该目录不存在会报错

        //桌面
        System.out.println(FileUtils.getUserDirectoryPath());

        //创建文件，如果文件存在则更新时间；如果不存在，创建一个空文件
        FileUtils.touch(new File("D:\\testDel"));

        //文件内容对比
//        FileUtils.contentEquals(new File("D://test/test1"), new File("D://test/test2"));
//        FileUtils.contentEqualsIgnoreEOL(new File("D://test/test1"), new File("D://test/test2"), null);//忽略换行符，第三个参数是字符集

        //文件地址转为URL
        URL[] urls = FileUtils.toURLs(new File[]{new File("D://testDel")});
        Arrays.stream(urls).forEach(System.out::println);

        //返回文件的列表 [ 目录 ] [ 过滤器 ] [ 递归 ]
        List<File> files = (List<File>) FileUtils.listFiles(new File("D://testDel"), new String[]{"txt"}, true);// 列出该目录下的所有doc文件，递归（扩展名不必带.doc）
        System.out.println(JSON.toJSON(files).toString());
        List<File> fileList = (List<File>)FileUtils.listFiles(new File("D://testDel"),null,true);//列出该目录下的所有文件，不递归        System.out.println(JSON.toJSON(files).toString());
        System.out.println(JSON.toJSON(files).toString());

        //查找给定目录中的文件(以及可选的目录子目录)。所有找到的文件都由IOFileFilter过滤。
        //FileUtils.listFilesAndDirs();


        //java8  bug
//        List<String> list = Arrays.asList("111");
//        Object[] objects = list.toArray();
//        objects[0] = new Object();


        List<String> list2 = new ArrayList<>();
        list2.add("111");
        Object[] objects2 = list2.toArray();
        objects2[0] = 1;

        AtomicInteger atomicInteger = new AtomicInteger(10);
        for (int i = 0; i < 5; i++) {
            try {
                if(atomicInteger.incrementAndGet() > 10) {
                    System.out.println("--------------limit");
                    //熔断逻辑    } else {
                    //处理逻辑
                }
            } finally {
                atomicInteger.decrementAndGet();
            }

        }


        //解压缩

    }



}
