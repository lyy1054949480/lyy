package com.example.lyy.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitBeanConfig {

    public static final String QUEUE = "queue";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);
    }

}
