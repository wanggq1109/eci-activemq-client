package com.eci.mq.customer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by wgq on 16/1/7.
 */
@Component("orderHandler")
public class OrderMsgHandler implements MessageHandler {

    private static final Logger logger = Logger.getLogger(OrderMsgHandler.class);


    @Override
    public void setMessage(TextMessage message, String msg) {

        try {
            message.setText("order handler 测试 SUCCESS");
        } catch (JMSException e) {

            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }
}
