package com.eci.mq.customer;

import javax.jms.TextMessage;

/**
 * Created by wgq on 16/1/7.
 */
public interface MessageHandler {

    public void setMessage(TextMessage message,String msg);
}
