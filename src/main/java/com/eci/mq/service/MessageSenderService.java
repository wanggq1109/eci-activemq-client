package com.eci.mq.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by wgq on 16/1/6.
 */

@Component("activemqMessageSender")
public class MessageSenderService implements MessageSender {

    private static final Logger logger = Logger.getLogger(MessageSenderService.class);

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public String sendMessage(Destination destination, final String message) {

        if (null == destination) {
            destination = jmsTemplate.getDefaultDestination();
        }

        Message replyMsg = jmsTemplate.sendAndReceive(destination, new MessageCreator() {

            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                String correlationID = UUID.randomUUID().toString();
                logger.info("客户端发送ID: " + correlationID);
                textMessage.setJMSCorrelationID(correlationID);
                return textMessage;
            }
        });

        TextMessage msg = (TextMessage) replyMsg;

        try {
            if (null != msg) {
                logger.info("product接收回执--------" + msg.getJMSCorrelationID() + "------------->" + msg.getText());
            } else {
                logger.info("Jms don't reponse message.....");
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(msg);
    }


}
