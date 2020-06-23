package com.example.lyy.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @描述： mq发送
 * @类名称： AmqpSend
 * @作者： lux
 * @版本： 1.0
 * @修改：
 * @创建日期： 2018-6-7 17:39
 * @版权： 江泰保险经纪股份有限公司
 */
@Component
public class AmqpSend {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
     */
    @Autowired
    public AmqpSend(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
//        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 发送消息
     * @param exchange 交换机名
     * @param rkey 路由id
     * @param serialNo  流水号
     * @param ifCode  接口号
     * @param ifVersion 接口版本
     * @param multiTarget 是否多出口 "false" "true"
     * @param content 发送内容
     */
    public void sendMsg(String exchange, String rkey, String serialNo, String ifCode, String ifVersion, String multiTarget, String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        MessageProperties props = new MessageProperties();
        props.setContentType(MessageProperties.CONTENT_TYPE_XML);
        Map<String, Object> headers = props.getHeaders();
        headers.put("SerialNo", serialNo);
        headers.put("IfCode", ifCode);
        headers.put("IfVersion", ifVersion);
        headers.put("MultiTarget", multiTarget);
        byte[] bs = new byte[0];
        try {
            bs = content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Message message = new Message(bs, props);
        rabbitTemplate.send(exchange, rkey, message, correlationId);
    }

    /**
     * 发送短信消息入队列
     *
     * @author guohuibin
     * @date 2018/10/26 16:45
     * @param channel 渠道
     * @param code 渠道编码
     * @param phone 电话号码
     * @param content 短信内容
     * @return void
     */
    public void sendSMS(String channel, String code, String phone, String content){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("channel",channel);
        hashMap.put("code",code);
        hashMap.put("phone",phone);
        hashMap.put("content",content);
        rabbitTemplate.convertAndSend("exchangeSMS", "queue_one_key1", hashMap);
    }

/*    *//**
     * 回调
     * 消息发送之后，异步回调。
     *//*
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息成功消费，id：{}", correlationData.getId());
        } else {
            log.info("消息消费失败：{}", cause);
        }
    }*/
}
