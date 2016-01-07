package com.eci.mq.listener;

import com.eci.mq.customer.MessageHandler;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by wgq on 16/1/6.
 */
@Component
public class MessageReceiverListener implements SessionAwareMessageListener<TextMessage>{

    private static final Logger logger = Logger.getLogger(MessageReceiverListener.class);

    @Autowired
    @Qualifier("orderHandler") //注入当前MessageHandler接口的实现类
    private MessageHandler msgHandler;

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {

        // This is the received message
        String msg = message.getText();

        logger.info("Receive: "+msg);

        ActiveMQTextMessage textMessage = new ActiveMQTextMessage();

        textMessage.setJMSCorrelationID(message.getJMSCorrelationID());

        msgHandler.setMessage(textMessage,msg);

        MessageProducer producer = session.createProducer(message.getJMSReplyTo());
        producer.send(textMessage);

    }


}
