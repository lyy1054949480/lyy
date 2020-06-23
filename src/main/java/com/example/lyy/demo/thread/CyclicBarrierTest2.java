package com.example.lyy.demo.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程间相互等待
 */
public class CyclicBarrierTest2 {
    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    private final static CyclicBarrier BARRIER = new CyclicBarrier(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final String name = "玩家" + i;
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        System.out.println(name + "已准备");
                        BARRIER.await();
                        Thread.sleep(1000);
                        System.out.println(name + "已加入游戏");
                    } catch (InterruptedException e) {
                        System.out.println(name + "离开游戏");
                    } catch (BrokenBarrierException e) {
                        System.out.println(name + "离开游戏");
                    }
                }
            });

        }
        EXECUTOR_SERVICE.shutdown();

    }
}
