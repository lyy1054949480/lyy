package com.example.lyy.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * mq相关的配置文件.
 */
@Slf4j
@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.username}")
    private String addresses;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        //从配置文件中获取
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        // 如果要进行消息回调，则这里必须要设置为true
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleMessageListenerContainer(){
        //SimpleRabbitListenerContainerFactory发现消息中有content_type有text就会默认将其转换成string类型的
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        // 设置使用的并发显示数
        container.setConcurrentConsumers(1);
        // 设置最大的并发线程数
        container.setMaxConcurrentConsumers(80);
        // 设置连接Factory
        container.setConnectionFactory(connectionFactory());
        // 设置是否自动ack, 默认是AUTO,
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }


    /**
     * demo 队列1,  勿删
     * @return
     */
    @Bean
    public Queue jtpfInsTest(){
        return new Queue("jtpf.ins.test", true, false, false);
    }

    /**
     * demo 队列2,  勿删
     * @return
     */
    @Bean
    public Queue jtpfInsTest2(){
        return new Queue("jtpf.ins.test2", true, false, false);
    }


    /**
     * 出单结果, 通知各渠道.
     * @return
     */
    @Bean
    public Exchange policyIssueResultExchange(){
        return new TopicExchange("lyy1", true, false);
    }

    /**
     * 保单结果通知队列, 特设渠道,
     * 该通知采用topic模式的exchange方式.
     * 该队列仅为特设渠道.
     * @return
     */
    @Bean
    public Queue sequipPolicyIssueResultQueue(){
        Map<String, Object> args = new HashMap<String, Object>();
        // 设置队列的消息过期时间TTL, 单位是毫秒, 1800s
//        args.put("x-expires", 1800000);
        //
        args.put("amqp_deliveryTag", 10);
        // 设置死信交换机, 如果死信交换机, 需要有多消费, 需要各消费自己绑定死信交换
        args.put("x-dead-letter-exchange", "jtpf_ins_policy_issue_dead");
        // 设置死信队列
//        args.put("x-dead-letter-exchange", "jtpf_ins_sequip_policy_issue_dead");
        // 最大队列长度, 积累消息个数
        args.put("x-max-length", 100);
        return new Queue("jtpf_ins_sequip_policy_issue", true, false, false, args);
    }


  /*  *//**
     * 绑定特设渠道的出单结果队列以及exchange
     * @param policyIssueResultExchange
     * @param sequipPolicyIssueResultQueue
     * @return
     *//*
    @Bean
    public Binding payExchangeBindingQueue(Exchange policyIssueResultExchange, Queue sequipPolicyIssueResultQueue){
        return new Binding(sequipPolicyIssueResultQueue.getName(),
                Binding.DestinationType.QUEUE,
                policyIssueResultExchange.getName(),
                "sequip",
                null);
    }*/


    /**
     * 定义特设渠道, 出单结果, 死信队列.
     * @return
     */
    @Bean
    public Exchange sequipPolicyIssueResultDeadExchange(){
        return new TopicExchange("lyy2", true, false);
    }

    /**
     * 定义保单出单结果通知队列特设渠道的死信队列
     * @return
     */
    @Bean
    public Queue sequipPolicyIssueResultDeadQueue(){
        return new Queue("jtpf_ins_sequip_policy_issue_dead", true, false, false);
    }

    /**
     * 绑定特设渠道的出单结果死信队列以及exchange
     * @param sequipPolicyIssueResultDeadExchange
     * @param sequipPolicyIssueResultDeadQueue
     * @return
     */
    @Bean
    public Binding payExchangeBindingDeadQueue(Exchange sequipPolicyIssueResultDeadExchange, Queue sequipPolicyIssueResultDeadQueue){
        return new Binding(sequipPolicyIssueResultDeadQueue.getName(),
                Binding.DestinationType.QUEUE,
                sequipPolicyIssueResultDeadExchange.getName(),
                "sequip",
                null);
    }

    @Bean
    public RabbitTemplate newRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    // 定义:每一次发送的时候, 都需要在correlationdata中附加业务字段ID
                    log.info("消息成功发送，id：{}", correlationData.getId());
                } else {
                    log.info("消息消费失败：{}", cause);
                }
            }
        });
        return template;
    }

}
