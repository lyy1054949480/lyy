package com.example.lyy.thread;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Description: 判断线程类任务是否有积留，处理
 * @Date: 2018/12/26 16:41
 **/
@Aspect
@Component
public class MyRejectedPolicyAspect {

    private static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();


    private static Logger logger = LoggerFactory.getLogger(MyRejectedPolicyAspect.class);

    @Resource
    private Executor asyncServiceExecutor;

    @Around("@annotation(MyRejectedPolicy)")
    public void doAroundMethod(ProceedingJoinPoint pjd) throws Throwable {

        pjd.proceed();

        System.out.println("执行完毕");
        //取得 PermissionContext 注解属性（值）信息
        MethodSignature methodSignature = (MethodSignature)pjd.getSignature();
        MyRejectedPolicy myRejectPolicy =  methodSignature.getMethod().getAnnotation(MyRejectedPolicy.class);
        String methodName = myRejectPolicy.value();

        // 判断内存维护的列表中是否有此方法产生的task
        LinkedHashMap<String, CopyOnWriteArrayList<FutureTask>> rejectTaskMap = ExecutorConfig.getRejectTaskMap();
        if(rejectTaskMap.containsKey(methodName)){
            CopyOnWriteArrayList<FutureTask> taskList = rejectTaskMap.get(methodName);
            // 如果有此方法对应的缓存task，不再往线程池中添加新的task，执行缓存中未执行的task，
            int taskListSize = taskList.size();
            if(taskListSize > 0){
                logger.info("[ " + methodName + " ] method blocked, list size: [" + taskListSize + "]");
                for (FutureTask futureTask : taskList) {
                    System.out.println("EXECUTOR_SERVICE");
                    EXECUTOR_SERVICE.execute(futureTask);
                    taskList.remove(futureTask);
                }

//                Iterator<FutureTask> it = taskList.iterator();
//                while (it.hasNext()){
//                    asyncServiceExecutor.execute(it.next());
//                    it.remove();
//                }
                // return 是为了打断后续执行， 不再往线程池中添加新的task
            }
        }
    }
}
