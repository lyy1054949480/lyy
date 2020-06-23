package com.example.lyy.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDown {

    private static final int PLAYER_AMOUNT = 5;

    public static void main(String[] args) {
        /* 对于每位运动员，CountDownLatch减1后即开始比赛 */
        CountDownLatch begin = new CountDownLatch(1);
        /* 对于整个比赛，所有运动员结束后才算结束 */
        CountDownLatch end = new CountDownLatch(PLAYER_AMOUNT);
        Player[] players = new Player[PLAYER_AMOUNT];
        for (int i = 0; i < PLAYER_AMOUNT; i++) {
            players[i] = new Player(i + 1, begin, end);
        }
        /* 设置特定的线程池，大小为5 */
        ExecutorService executorService = Executors.newFixedThreadPool(PLAYER_AMOUNT);
        for (Player player : players) {
            /* 分配线程 */
            executorService.execute(player);
        }
        System.out.println("Race begins!");
        /* 所有Player等待 比赛信号的开始 */
        begin.countDown();
        try {
            /* 等待end状态变为0，即为比赛结束 */
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Race ends!");
        }
        executorService.shutdown();
    }
}

class Player implements Runnable {

    private final int            id;
    private final CountDownLatch begin;
    private final CountDownLatch end;

    public Player(int id, CountDownLatch begin, CountDownLatch end){
        super();
        this.id = id;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            /* 等待begin的状态为0 */
            begin.await();
            /* 随机分配时间，即运动员完成时间 */
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println("Play" + id + "arrived.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /* 使end状态减1，当前线程的运动员完成比赛 */
            end.countDown();
        }
    }

}