package com.example.lyy.demo.thread;

import com.example.lyy.util.CPUUtils;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义
 */
public class MyThreadPoolExecutor {
    private static final Integer CPU_CORE_COUNT = CPUUtils.calcCpuCoreCount();
    static final CountDownLatch latch = new CountDownLatch(2);


    public static void main(String[] args) throws Exception {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        String name = executor.getThreadPoolExecutor().getQueue().getClass().getName();
        System.out.println(name);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(2), new DefaultThreadFactory(), new MyIgnorePolicy());
        threadPoolExecutor.prestartAllCoreThreads(); // 预启动所有核心线程
        System.out.println(threadPoolExecutor.getCorePoolSize());
        for (int i = 0; i < 20; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            threadPoolExecutor.execute(task);
            latch.countDown();
        }


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
    }


    /**
     * 拒绝策略
     */
    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println( r.toString() + " rejected");
//            System.out.println("填充线程池");
//            e.setCorePoolSize(e.getPoolSize() * 2);
            System.out.println(e.getCorePoolSize());
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }


    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            System.out.println(t.getName() + " has been created");
            if (t.isDaemon()) t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(this.name + " is running!");
            try {
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
