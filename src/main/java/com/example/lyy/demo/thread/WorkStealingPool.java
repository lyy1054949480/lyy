package com.example.lyy.demo.thread;

import java.util.concurrent.*;

/**
 * 工作窃取线程池
 *
 * 假设共有三个线程同时执行, A, B, C
 * 当A,B线程池尚未处理任务结束,而C已经处理完毕,则C线程会从A或者B中窃取任务执行,这就叫工作窃取
 * 假如A线程中的队列里面分配了5个任务，而B线程的队列中分配了1个任务，当B线程执行完任务后，它会主动的去A线程中窃取其他的任务进行执行
 * WorkStealingPool 背后是使用 ForkJoinPool实现的
 * newWorkStealingPool适合使用在很耗时的操作，但是newWorkStealingPool不是ThreadPoolExecutor的扩展，
 * 它是新的线程池类ForkJoinPool的扩展，但是都是在统一的一个Executors类中实现，
 * 由于能够合理的使用CPU进行对任务操作（并行操作），所以适合使用在很耗时的任务中
 */
public class WorkStealingPool {
    public static void main(String[] args) throws Exception {
        // 用于计数线程是否执行完成
        CountDownLatch countDownLatch = new CountDownLatch(threads);

        test3(countDownLatch);
    }

    // 线程数
    private static final int threads = 10;

    /**
     * newFixedThreadPool execute
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test1(CountDownLatch countDownLatch) throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * newFixedThreadPool submit submit
     */
    public static void test2(CountDownLatch countDownLatch) throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//            Callable 带返回值
            executorService.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }));
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }
    /**
     * newFixedThreadPool submit Callable
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test3(CountDownLatch countDownLatch) throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//          Runnable 带返回值
            FutureTask<?> futureTask = new FutureTask<>(new Callable<String>() {
                /**
                 * call
                 * @return currentThreadName
                 */
                @Override
                public String call() {
                    return Thread.currentThread().getName();
                }
            });
            executorService.submit(new Thread(futureTask));
            System.out.println("--" + futureTask.get());
        }
        System.out.println("---- end ----");
    }
}

