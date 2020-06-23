package com.example.lyy.mq;

import com.example.lyy.entity.Address;
import com.example.lyy.entity.User;
import com.example.lyy.util.xml.JavaBeanAndXmlUtil;
import com.example.lyy.util.xml.Xml;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RabbitSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpSend amqpSend;



    public void send(String msg){
        /*amqpTemplate.convertAndSend(RabbitBeanConfig.QUEUE, msg);*/
        List<Address> addressList = new ArrayList<>();
        addressList.add(Address.builder().addressName("beijing").addressId("33333333333").build());
        User user = User.builder().username("sss").password("11111").addressList(addressList).build();
//        String message = XmlUtil.ObjectToXml("Message", "UTF-8", user);
        Xml xml = new Xml();
        xml.setHead(new Xml.Head("200","ahhaha"));
        xml.setBody(new Xml.Body(user));
        String s = JavaBeanAndXmlUtil.convertToXml(xml);
        Map<String,String> map = new HashMap<>();
        map.put("test",s);
        amqpSend.sendMsg("lyy1","test","123456789",null,null,"false",s);
        //rabbitTemplate.convertAndSend("lyy1","test",map,new CorrelationData(UUID.randomUUID().toString()));
        System.out.println("生产者生产了一个消息： " + msg + "  " + new Date().getTime());
    }
}
