package com.example.lyy.demo;



import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileChannelTest {

    private static final int count = 5;
    public static void main(String[] args) throws Exception {
        File in = new File("D:\\source.txt");
//        List<String> list = new ArrayList<>(10000000);
//        for (int i = 0; i < 1000000; i++) {
//            list.add("阿斯顿发放是广东省委社会关怀温热");
//        }
//        FileUtils.writeLines(in, Charsets.UTF_8.name(),list,true);
        long length = in.length();
        long l = length / count;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < count - 1; i++) {
            new FileChannelThread("D:\\source.txt","D:\\target.txt",l*i,l*(i+1)).start();
        }
        new FileChannelThread("D:\\source.txt","D:\\target.txt",l*(count - 1),length).start();
        long end = System.currentTimeMillis();
        System.out.println(end - begin);

    }
}
