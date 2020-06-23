//package com.example.lyy.thread;
//
//import com.example.lyy.util.TestTask;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
//@Component
//@Order(1)
//public class MyRunner implements ApplicationRunner {
//
//    @PostConstruct
//    public void init(){
//        myRunner = this;
//        myRunner.runTask = this.runTask;
//    }
//
//    @Resource
//    MyRunner myRunner;
//
//    @Resource
//    RunTask runTask;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        myRunner.test();
//    }
//
//    @MyRejectedPolicy("runTask")
//    private void test() {
//        for (int i = 0; i < 100; i++) {
//            runTask.runTest(i);
//        }
//    }
//}
