package com.superproject.rocket.base.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

/** 
 * 消息监听抽象类
 */
public abstract class RocketMQListener  implements MessageListenerConcurrently {

	protected Logger logger = Logger.getLogger(getClass());

	/**
	 * 继承jar包中的实现，监听到有消息首先会经过这个方法处理
	 */
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		//String topic = consumeConcurrentlyContext.getMessageQueue().getTopic();
		List<String> messageContentList = new ArrayList<String>();
		String topic = null;
		String tags = null;
		String msgId = null;
		try {
			for (MessageExt curMessage : messageList) {
				topic = curMessage.getTopic();
				tags = curMessage.getTags();
				msgId = curMessage.getMsgId();

				String msgContent = new String(curMessage.getBody(), "UTF-8");
				messageContentList.add(msgContent);
				logger.info("注册端接收到消息: topic={"+topic+"},tags={"+tags+"},messageId={"+msgId+"},messageContentList={"+messageContentList+"},messageList={"+messageList+"}");
			}
		} catch (Exception e) {
			logger.error("注册端接收到消息但解析发送内容时异常：topic={"+topic+"},tags={"+tags+"},messageId={"+msgId+"}", e);
		}

		//区分主题做处理
		Boolean flag = consumeMessage(messageContentList.get(0), topic, tags);
		if (flag) {
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		} else {
			return ConsumeConcurrentlyStatus.RECONSUME_LATER;

		}

	}

	/**
	 * 具体对应的消息处理类实现这个方法对消息进行各自的处理
	 * @param messageList
	 * @return
	 */
	public abstract boolean consumeMessage(String msgContext, String topic, String tags);

}
