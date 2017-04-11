package com.superproject.metaq;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * 
 * @author sd
 *
 */
public class Producer {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private static MessageSessionFactory messageSessionFactory;
	
	private static MessageProducer msgProducer;

	private Producer() {
	}

	private static Producer producer = null;

	public static Producer getInstance() {
		if (producer == null) {
			producer = new Producer();
			msgProducer = messageSessionFactory.createProducer();
		}
		return producer;
	}

	/**
	 * 
	 * @param topic
	 * @param line
	 * @param attribute
	 * @return
	 * @throws MetaClientException
	 * @throws InterruptedException
	 */
	public boolean producerRun(final String topic, String line, String attribute) throws MetaClientException,InterruptedException{
		msgProducer.publish(topic);
		
		Message message = null;
		
		if(StringUtils.isNotEmpty(attribute)){
			message = new Message(topic, line.getBytes(),attribute);
		}else{
			message = new Message(topic, line.getBytes());
		}
		
		SendResult sendResult = msgProducer.sendMessage(message);
		
		if (!sendResult.isSuccess()) {
			logger.error("Send message failed,error message:" + sendResult.getErrorMessage());
			return false;
		} else {
			logger.info("Send message successfully,sent to " + sendResult.getPartition());
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param topic
	 * @param obj
	 * @param attribute
	 * @return
	 * @throws MetaClientException
	 * @throws InterruptedException
	 */
	public boolean producerRun(final String topic,  Object obj,String attribute) throws MetaClientException,InterruptedException{
		msgProducer.publish(topic);
		
		Message message = null;
		if(StringUtils.isNotEmpty(attribute)){
			message = new Message(topic, SerializeUtil.serialize(obj),attribute);
		}else{
			message = new Message(topic, SerializeUtil.serialize(obj));
		}
		SendResult sendResult = msgProducer.sendMessage(message);
		if (!sendResult.isSuccess()) {
			logger.error("Send message failed,error message:" + sendResult.getErrorMessage());
			return false;
		} else {
			logger.info("Send message successfully,sent to " + sendResult.getPartition());
		}
		return true;
	}
	
	/**
	 * 
	 * @param topics
	 * @param obj
	 * @throws MetaClientException
	 */
	public void transactionProducer(String[] topics,Object[] obj) throws MetaClientException{
	 
		final MessageProducer producer = messageSessionFactory.createProducer();
		try { 
			producer.beginTransaction();
                
			for (int i = 0; i < topics.length; i++) {
				producer.publish(topics[i]);
                
				SendResult sendResult = producer.sendMessage(new Message(topics[i], SerializeUtil.serialize(obj[i])));
				if (!sendResult.isSuccess()) {
					producer.rollback();
					continue;
				}
			}
			producer.commit();

		}catch (final Exception e) {
			producer.rollback();
		}

	}

	public static MessageSessionFactory getMessageSessionFactory() {
		return messageSessionFactory;
	}

	public static void setMessageSessionFactory(MessageSessionFactory messageSessionFactory) {
		Producer.messageSessionFactory = messageSessionFactory;
	}
}
