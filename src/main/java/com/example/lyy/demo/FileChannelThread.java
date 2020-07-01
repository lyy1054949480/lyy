package com.example.lyy.demo;

import lombok.SneakyThrows;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileChannelThread extends Thread {

    public FileChannelThread(String source, String target, Long start, Long end) {
        this.source = source;
        this.target = target;
        this.start = start;
        this.end = end;
    }

    private String source;
    private String target;
    private Long start,end;

    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        try {
            RandomAccessFile in = new RandomAccessFile(source, "r");
            RandomAccessFile out = new RandomAccessFile(target, "rw");
            in.seek(start);
            out.seek(start);
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();
            FileLock lock = outChannel.lock(start, (end - start), false);
            inChannel.transferTo(start, end - start, outChannel);
            lock.release();
            inChannel.close();
            outChannel.close();
            long end = System.currentTimeMillis();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(end - begin);
    }
}
