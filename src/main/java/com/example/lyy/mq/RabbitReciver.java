package com.example.lyy.mq;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class RabbitReciver {

    public void receive(String msg) {
        String s = null;
        try {
            s = new String(msg.getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {


        }
        System.out.println("消费者收到了一个消息: " + s + "  " + new Date().getTime());
    }
}
