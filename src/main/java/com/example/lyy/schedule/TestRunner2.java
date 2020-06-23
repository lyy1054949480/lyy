package com.example.lyy.schedule;


import com.example.lyy.entity.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TestRunner2 {


    @Value("${lyy.switchConfig}")
    private String switchConfig;

    @Scheduled(fixedDelay = 10000)
    public void runner(){
        if(switchConfig.contains("test")){
            for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(new TestRunner2.RecoreThread(null));
                thread.start();
            }
        }

    }

    private class RecoreThread implements Runnable{

        private List<Json> dataList;

        public RecoreThread(List<Json> dataList) {
            this.dataList = dataList;
        }

        @Override
        public void run() {
            long childId = Thread.currentThread().getId();
            log.info("---thread id:[" + childId + "],record id:[] begin ---");
            System.out.println("--------------------");

        }
    }
}
