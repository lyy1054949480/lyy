package com.example.lyy.mq;


import com.alibaba.fastjson.JSONObject;
import com.example.lyy.entity.User;
import com.example.lyy.util.auxiliary.FormatExceptionStack;
import com.example.lyy.util.xml.XmlUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

/**
 * 消息队列, 消费各渠道消息, 进行消费
 */
@Slf4j
@Component
public class PolicyIssueResultReceiver {
    /**
     *
     * 监听队列, 消费消息
     * @param message 消息实体
     * @param deliveryTag 用于ack的tag
     * @param channel 用于ack
     * @param consumerQueue 由于, 该receiver监听的队列比较多, 确定当前消息, 是从哪个队列获取的消息
     * @throws IOException
     */
    @RabbitListener(queues = {"queue"}, containerFactory = "simpleMessageListenerContainer")
    public void processOrder(Message message,
                             @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                             @Header(AmqpHeaders.CONSUMER_QUEUE) String consumerQueue,
                             Channel channel) throws Exception {
        log.info("消费出单结果队列, 队列名称:{}, 消息内容:{}", consumerQueue, new String(message.getBody()));
        Boolean flag = handleBusiness(message);
        //如果不手工ACK消息，则当前Consumer不会再得到RabbitMQ推送的消息，除非手工终止当前Consumer。
        log.info("消息消费结果:{}", flag);
        if (flag) {
            //确认消息成功消费
            channel.basicAck(deliveryTag, false);
            byte[] body = message.getBody();
            String s = new String(body, "UTF-8");
            /*Map map = XmlUtil.Dom2Map(XmlUtil.string2Element(new String(message.getBody())));
            String user = (String)map.get("Body");
            User user1 = JSONObject.parseObject(user, User.class);*/

        } else {
            // 消息重新入队, 重新入队列也是不会成功, 就不重新入队,
            // TODO 消费失败的可以记录失败日志, 或者入库, 等待手动补偿.
            channel.basicNack(deliveryTag,false,false);
        }
    }

    private Boolean handleBusiness(Message message){
        try {
            Map<String, Object> headers = message.getMessageProperties().getHeaders();
            if(checkHeader(message)) {
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error("消费出单返回报文, 出现异常, 异常信息:" + FormatExceptionStack.formatException(e));
            // 为了防止出现consumer不能用, 出现异常也返回true
            return true;
        }
    }

    private Boolean checkHeader(Message message){
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        Object serialNo = headers.get("SerialNo");
        return serialNo == null;
    }

}
