package com.example.lyy.util;

import com.example.lyy.entity.TOrder;
import com.example.lyy.mapper.TOrderMapper;
import com.example.lyy.util.id.IDMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by cjw on 2019/6/11.
 * Description
 */
@Slf4j
@Component
public class TestTask {

    @Autowired
    private TOrderMapper tOrderMapper;

    @Async("taskExecutor")
    public Future<String> doTaskOne(){
        try {
            log.info("开始做任务一");
            tOrderMapper.insert(TOrder.builder().operator("LYY").contextMsg("11").orderId(IDMaker.getOrderCode()).build());
            log.info("完成任务一，耗时：" + 123 + "毫秒");
            return new AsyncResult<>("success");
        }catch (Exception e){
            return new AsyncResult<>("error");
        }
    }

    @Async("taskExecutor")
    public Future<String> doTaskTwo() throws Exception {
        try {
            log.info("开始做任务二");
            tOrderMapper.insert(TOrder.builder().operator("LYY").contextMsg("22").orderId(IDMaker.getOrderCode()).build());
            log.info("完成任务二，耗时：" + 456 + "毫秒");
            return new AsyncResult<>("success");
        }catch (Exception e){
            return new AsyncResult<>("error");
        }
    }

    @Async("taskExecutor")
    public Future<String> doTaskThree() throws Exception {
        try {
            log.info("开始做任务三");
            tOrderMapper.insert(TOrder.builder().operator("LYY").contextMsg("333").orderId(IDMaker.getOrderCode()).build());
            log.info("完成任务三，耗时：" + 789 + "毫秒");
            return new AsyncResult<>("success");
        }catch (Exception e){
            return new AsyncResult<>("error");
        }
    }
}
