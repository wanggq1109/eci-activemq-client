package com.eci.mq.service;



import org.apache.activemq.Message;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.io.Serializable;

public interface MessageSender {

	/**
	 * 发送普通的纯文本信息
	 * @param destination 消息队列对象
	 * @param message	消息内容
	 */
	public String sendMessage(Destination destination, final String message);


}
