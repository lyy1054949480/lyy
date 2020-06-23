package com.example.lyy.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池自定义拒绝策略
 * @Date: 2018/12/26 11:25
 **/
public class MyRejectedPolicyHandler implements RejectedExecutionHandler {

    private static Logger logger = LoggerFactory.getLogger(MyRejectedPolicyHandler.class);
    
    /**
     * @Description:  将拒绝的线程放到全局变量中
     * @Date: 2018/12/26 11:25
     * @Param: [r, executor]
     * @Return: void
     **/
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        FutureTask task = (FutureTask)r;
        // 获取调用的方法名称(反射获取私有属性)
        long taskCount = executor.getTaskCount();
        System.out.println("taskCount"+taskCount);
        try {
            //new Thread(r,"新线程"+new Random().nextInt(10)).start();
            Object callable = getFiled(task,"callable");
            Method userDeclaredMethod = (Method) getFiled(callable,"val$userDeclaredMethod");
            String methodName = userDeclaredMethod.getName();

            logger.info("Add task to Map, methodName: [ "  + methodName + " ]");

            //在内存中维护一个全局Map, 将策略拒绝的task放置到map中
            LinkedHashMap<String, CopyOnWriteArrayList<FutureTask>> rejectTaskMap = ExecutorConfig.getRejectTaskMap();
            if(!rejectTaskMap.containsKey(methodName)){
                CopyOnWriteArrayList<FutureTask> taskList = new CopyOnWriteArrayList<>();
                taskList.add(task);
                rejectTaskMap.put(methodName, taskList);
                logger.info("MethodName : [ " + methodName + " ] taskList size : [" + taskList.size() + " ]" );
            }else{
                CopyOnWriteArrayList<FutureTask> taskList = (CopyOnWriteArrayList)rejectTaskMap.get(methodName);
                taskList.add(task);
                logger.info("MethodName : [ " + methodName + " ] taskList size : [" + taskList.size() + " ]" );
            }
//            List<FutureTask> taskList = rejectTaskMap.get(methodName);
//            taskList.add(task);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    /**
     * @Description: 反射，通过循环父类获取field值（含private）
     * @Date: 2018/12/26 15:28
     * @Param: [c, name]
     * @Return: java.lang.Object
     **/
    private static Object getFiled(Object c, String name) throws IllegalAccessException {
        while (c != null && !c.getClass().getName().toLowerCase().equals("java.lang.object")) {
            try {
                Field field = c.getClass().getDeclaredField(name);
                field.setAccessible(true);
                return field.get(c);
            } catch (NoSuchFieldException e) {
                c = c.getClass().getSuperclass();
            }
        }
        return null;
    }
}
