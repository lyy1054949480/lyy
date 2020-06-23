package com.example.lyy.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableAsync
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    private static LinkedHashMap<String, CopyOnWriteArrayList<FutureTask>> rejectTaskMap;

    @Value("${thread.CORE_POOL_SIZE}")
    private int corePoolSize;

    @Value("${thread.MAX_POOL_SIZE}")
    private int maxPoolSize;

    @Value("${thread.QUEUE_CAPACITY}")
    private int queueCapacity;

    @Bean
    public Executor asyncServiceExecutor() {
        logger.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //String name = executor.getThreadPoolExecutor().getQueue().getClass().getName(); LinkedBlockingQueue
        //线程前缀
        executor.setThreadNamePrefix("asyncServiceExecutor");

        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);

        //配置队列最大长度
        executor.setQueueCapacity(queueCapacity);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行

        //自定义拒绝策略
        executor.setRejectedExecutionHandler(new MyRejectedPolicyHandler());
        rejectTaskMap = new LinkedHashMap<>();
        //执行初始化
        executor.initialize();
        String name = executor.getThreadPoolExecutor().getQueue().getClass().getName();
        System.out.println("======"+name);
        return executor;
    }

    public static LinkedHashMap<String, CopyOnWriteArrayList<FutureTask>> getRejectTaskMap() {
        return rejectTaskMap;
    }

}
