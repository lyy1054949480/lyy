package com.example.lyy.demo.thread;

import com.example.lyy.demo.thread.LockFreeQueue;

import java.util.stream.IntStream;

public class LockFreeDemo {
    public static void main(String[] args) {
        LockFreeQueue queue = new LockFreeQueue(1);
        IntStream.rangeClosed(1, 20).parallel().forEach(
                i -> {
                    if (i % 2 == 0) {
                        queue.add(i);
                    } else {
                        queue.poll();

                    }
                }
        );
        queue.print();
    }
}