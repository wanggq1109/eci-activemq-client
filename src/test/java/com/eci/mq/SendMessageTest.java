package com.eci.mq;

import com.alibaba.fastjson.JSONObject;
import com.eci.mq.service.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import javax.jms.JMSException;

/**
 * Created by wgq on 16/1/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class SendMessageTest {

    @Autowired
    private MessageSender messageSender;


    @Autowired
    @Qualifier("sessionAwareQueue")
    private Destination queueDestination;

    @Test
    public void testSessionAwareMessageListener() {

        String msg = messageSender.sendMessage(queueDestination, "测试 ActiveMQ 的Requst and reply model");

        System.out.println("请求响应信息------->" + msg);
    }
}
