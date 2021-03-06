package com.example.lyy.redis.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 读锁可以被多个线程获取，同一个线程可以重入读锁，
 * 如果获取读锁的时候存在写锁，则需要等待写锁被释放才能获取；
 * 写锁的获取的时候，若存在读锁，则需要等待所有的读锁释放之后才能被获取，
 * 如果有线程正在获取写锁，其他获取读锁的线程将等待写锁被获取并释放之后才能获取读锁，
 * 写锁只能被一个线程持有
 */
public class RedisReadWriteLock {
    //读锁
    private static volatile RedisReadLock redisReadLock;
    //写锁
    private static volatile RedisWriteLock redisWriteLock;

    //双重检查锁实现单例
    public static RedisReadLock readLock(){
        if(redisReadLock == null){
            synchronized (RedisReadLock.class){
                if (redisReadLock == null){
                    redisReadLock = new RedisReadLock();
                }
            }
        }
        return redisReadLock;
    }

    public static RedisWriteLock writeLock(){
        if(redisWriteLock == null){
            synchronized (RedisWriteLock.class){
                if (redisWriteLock == null){
                    redisWriteLock = new RedisWriteLock();
                }
            }
        }
        return redisWriteLock;
    }

    // 构建锁的key
    public static String getReadLockKey(String name){
        return RedisLockConf.READ_LOCK_PREFIX + name;
    }

    public static String getWriteLockKey(String name){
        return RedisLockConf.WRITE_LOCK_PREFIX + name;
    }

    public static String getReentrantWriteLockKey(String name){
        return RedisLockConf.REENTRANT_WRITE_LOCK_PREFIX + name;
    }

    //由连接池id+获取锁的线程id，来区分分布式中的不同线程
    public static String getThreadUid(){
        return String.valueOf(Thread.currentThread().getId());
    }

    public static class RedisLockConf {
        public static final String READ_LOCK_PREFIX = "read_lock_";
        public static final String WRITE_LOCK_PREFIX = "write_lock_";
        public static final String REENTRANT_WRITE_LOCK_PREFIX = "reentrant_write_lock_";
    }

    public static void main(String[] args) {
        final int[] num = {0};
        for (int i = 0; i < 1 ; i++) {
            Thread thread = new Thread(() -> {
                RedisReadWriteLock.writeLock().tryLock("ccc", 30,300, TimeUnit.SECONDS);
                num[0]++;
                System.out.println("【写】：" + num[0]);
                RedisReadWriteLock.writeLock().unlock("ccc");
            });
            thread.start();
        }
//        final int[] num = {0};
//        for (int i = 0; i < 1 ; i++) {
//            Thread thread = new Thread(() -> {
//                RedisReadWriteLock.writeLock().tryLock("ccc", 30,300, TimeUnit.SECONDS);
//                num[0]++;
//                System.out.println("【写】：" + num[0]);
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                RedisReadWriteLock.writeLock().unlock("ccc");
//            });
//            thread.start();
//        }
//        for (int i = 0; i < 10 ; i++) {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Thread thread = new Thread(() -> {
//                RedisReadWriteLock.readLock().tryLock("ccc", 30,300, TimeUnit.SECONDS);
//
//                System.out.println("读：" + num[0]);
//
//                RedisReadWriteLock.readLock().unlock("ccc");
//            });
//            thread.start();
//            if(i % 3 == 0){
//                try {
//                    TimeUnit.MILLISECONDS.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}






