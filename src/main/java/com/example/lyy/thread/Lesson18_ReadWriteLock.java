package com.example.lyy.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 展示读写锁的基本应用
 * 读读之间不加锁
 * 读写和写写之间才加锁
 */
public class Lesson18_ReadWriteLock {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static Lock readLock = rwLock.readLock();
    private static Lock writeLock = rwLock.writeLock();
    private int value = 1;

    //模拟读操作
    public void handleRead(Lock lock) throws InterruptedException{
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "读");
            Thread.sleep(1000);
            System.out.println(value);
        } finally {
            lock.unlock();
        }

    }

    //模拟写操作
    public void handleWrite(Lock lock, int index) throws InterruptedException{
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "写");
            Thread.sleep(3000);
            value = index;
            System.out.println("写后value： "+value);
        } finally {
//            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Lesson18_ReadWriteLock demo = new Lesson18_ReadWriteLock();

        //给读线程加上读锁
        Runnable readTarget = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
//                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //给写线程加上写锁
        Runnable writeTarget = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock,new Random().nextInt());
//                    demo.handleWrite(lock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 8 ; i++) {
            Thread thread = new Thread(readTarget);
            thread.setName("线程" + i);
            thread.start();
        }

        for (int i = 8; i < 10; i++) {
            Thread thread = new Thread(writeTarget);
            thread.setName("线程" + i);
            thread.start();
        }
    }
}