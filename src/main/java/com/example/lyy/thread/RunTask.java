package com.example.lyy.thread;

import com.example.lyy.entity.TLog;
import com.example.lyy.mapper.TLogMapper;
import com.example.lyy.util.id.SnowflakeIdWorker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Data
@Component
public class RunTask {
//    private CountDownLatch switchLatch;
//
//    private CountDownLatch executeLatch;


    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    @Resource
    private TLogMapper tLogMapper;



    @Async("asyncServiceExecutor")
    public Future<String> runTest(int i, CountDownLatch begin, CountDownLatch excute) throws Exception {
        begin.await();
        try {
            execute(i,begin,excute);
            Thread.sleep(3000);
            return new AsyncResult<>(String.valueOf(i));
        }catch (Exception e){
            return new AsyncResult<>(String.valueOf(0));
        }finally {
            System.out.println("countDown"+i);
            excute.countDown();
        }

    }

    @Async("asyncServiceExecutor")
    public Future<String> runTest2(int i, CountDownLatch begin,CountDownLatch excute) throws Exception {
        begin.await();
        try {
            execute(i,begin,excute);
            Thread.sleep(500);
            return new AsyncResult<>(String.valueOf(i));
        }catch (Exception e){
            return new AsyncResult<>(String.valueOf(0));
        }finally {
            excute.countDown();
        }

    }

    @Async("asyncServiceExecutor")
    public Future<String> runTest3(int i, CountDownLatch begin,CountDownLatch excute) throws Exception {
        begin.await();
        try {
            execute(i,begin,excute);
            Thread.sleep(800);
            return new AsyncResult<>(String.valueOf(i));
        }catch (Exception e){
            return new AsyncResult<>(String.valueOf(0));
        }finally {
            excute.countDown();
        }

    }

    @Async("asyncServiceExecutor")
    public Future<String> runTest4(int i, CountDownLatch begin,CountDownLatch excute) throws Exception {
        begin.await();
        try {
            execute(i,begin,excute);
            return new AsyncResult<>(String.valueOf(i));
        }catch (Exception e){
            return new AsyncResult<>(String.valueOf(0));
        }finally {
            excute.countDown();
        }

    }

    @Async("asyncServiceExecutor")
    public void insertLog(int i) throws Exception {
        System.out.println("insert"+1);
        tLogMapper.insertSelective(TLog.builder().productCode("test"+i).insertDate(new Date()).id(snowflakeIdWorker.nextId("")).build());
    }


    public void execute(int i, CountDownLatch begin,CountDownLatch excute) throws Exception {
        System.out.println("execute"+i);
        if (i ==4){
            throw new Exception("error"+i);
        }
    }
}
