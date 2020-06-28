package com.example.lyy.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试 读写锁
 * @author ConstXiong
 * @date 2019-12-19 10:40:45
 */
public class TestReadWriteLock {

    final static ReadWriteLock rwLock = new ReentrantReadWriteLock();
    
    final static Lock readLock = rwLock.readLock();//读锁
    
    final static Lock writeLock = rwLock.writeLock();//写锁
    
    static int count = 0;
    
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + get());
            }).start();
        }
        
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " add");
                add();
            }).start();
        }
    }
    
    private static int get() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }
    
    private static void add() {
        writeLock.lock();
        try {
            count++;
        } finally {
            writeLock.unlock();
        }
    }
    
}