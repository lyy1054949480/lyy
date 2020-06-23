package com.example.lyy.demo.thread;

import com.example.lyy.demo.thread.CyclicBarrierTest2;

import java.util.concurrent.*;


/**
 * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
 */
public class ScheduledThread {

    private static ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(100);


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /**
         * 自定义线程池长度
         */
//        ((ThreadPoolExecutor)EXECUTOR_SERVICE).setCorePoolSize(20);
//        System.out.println(((ThreadPoolExecutor) EXECUTOR_SERVICE).getCorePoolSize());

        for (int i = 0; i < 100; i++) {
            ScheduledFuture<?> scheduledFuture = EXECUTOR_SERVICE.scheduleAtFixedRate(() -> createCode("1"), 0, 3, TimeUnit.MILLISECONDS);//// 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间

        }
        //ScheduledFuture<?> scheduledFuture = EXECUTOR_SERVICE.scheduleAtFixedRate(() -> createCode("1"), 0, 3, TimeUnit.SECONDS);//// 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间
//        Object o = scheduledFuture.get();
//        System.out.println(o);
//        EXECUTOR_SERVICE.shutdown();
//        EXECUTOR_SERVICE.scheduleWithFixedDelay(() -> create(),10,1,TimeUnit.MILLISECONDS);// // 循环任务，以上一次任务的结束时间计算下一次任务的开始时间

        /**
         * callable
         */
//        ScheduledFuture<String> schedule = EXECUTOR_SERVICE.schedule(() -> createCode("1"),1, TimeUnit.SECONDS);


        /**
         * runnable
         */
//        EXECUTOR_SERVICE.schedule(() -> create(),1, TimeUnit.SECONDS);
//        EXECUTOR_SERVICE.shutdown();
//        System.out.println(EXECUTOR_SERVICE.isShutdown());
//        while (!EXECUTOR_SERVICE.awaitTermination(1, TimeUnit.SECONDS)) {
//            System.out.println("线程池没有关闭");
//            System.out.println("isTerminated:" + EXECUTOR_SERVICE.isTerminated());
//        }
//        System.out.println(EXECUTOR_SERVICE.isTerminated());

    }

    private static void create(){
//        CyclicBarrierTest2.excute(null);
    }

    private static String createCode(String code){
        System.out.println("----"+Thread.currentThread().getName());
        return "code";
    }
}

