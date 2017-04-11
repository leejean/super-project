package com.superproject.rocket.base.producer;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * RocketMQ生产者
 * @author leejean
 *
 */
public class RocketMQProducer {

	protected Logger logger = Logger.getLogger(getClass());

	protected DefaultMQProducer producer = null;

	protected String rocketMQUrl;// 注册机服务名

	protected String producerGroupName;// 主题组

	/**
	 * 初始化
	 */
	public void init() throws Exception {
		producer = new DefaultMQProducer(producerGroupName);
		producer.setNamesrvAddr(rocketMQUrl);
		producer.start();
		logger.info("===========================MQ连接[已创建]===========================");
	}

	/**
	 * 销毁
	 */
	public void destroy() {
		producer.shutdown();
		logger.info("===========================MQ连接[已销毁]===========================");
	}

	/**
	 * 发送消息
	 * @param message
	 * @return
	 */
	public Map<String, Object> sendMessage(Message message) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object resultMsg;
		Boolean sendSuccess = Boolean.FALSE;
		try {
			SendResult result = producer.send(message);
			resultMsg = packageResult(message, result, null, 1);
			sendSuccess = Boolean.TRUE;
		} catch (Exception e) {
			resultMsg = packageResult(message, null, e.getMessage(), 2);
			logger.error("发送消息异常:exceptionMsg={}", e);
		}
		map.put("resultMsg", resultMsg);
		map.put("sendSuccess", sendSuccess);
		return map;
	}

	/**
	 * 组装发送的消息
	 * @param Topic 消息主题
	 * @param tag 主题分支
	 * @param key 消息的key
	 * @param messageContent 消息的内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Message packageMessage(String Topic, String tag, String key, String messageContent) {
		Message msg = null;
		if (StringUtils.isNotEmpty(Topic) && StringUtils.isNotEmpty(tag) && StringUtils.isNotEmpty(key)
				&& StringUtils.isNotEmpty(messageContent)) {
			try {
				msg = new Message(Topic.trim(),// topic
						tag.trim(),// tag
						key.trim(),// key
						messageContent.trim().getBytes("UTF-8"));// messageContent.getBytes()
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	/**
	 * 组装发送结果
	 * @param msg
	 * @param result
	 * @param excetionMsg
	 * @param msgType
	 * @return
	 */
	public SendMQResult packageResult(Message msg, SendResult result, String excetionMsg, int msgType) {
		SendMQResult msge = new SendMQResult();
		try {
			msge.setNamesrvAddr(this.rocketMQUrl);
			msge.setProducerGroupName(this.producerGroupName);
			msge.setTopic(msg.getTopic());
			msge.setTag(msg.getTags());
			msge.setKey(msg.getKeys());
			msge.setMessageContent(new String(msg.getBody(), "UTF-8"));
			msge.setCreateDate(new Date());
		} catch (Exception e) {
		}

		if (1 == msgType) {
			msge.setMsgId(result.getMsgId());
			msge.setQueueOffset(result.getQueueOffset());
		} else {
			msge.setExceptionMsg(excetionMsg);
		}
		return msge;
	}

	public String getRocketMQUrl() {
		return rocketMQUrl;
	}

	public void setRocketMQUrl(String rocketMQUrl) {
		this.rocketMQUrl = rocketMQUrl;
	}

	public String getProducerGroupName() {
		return producerGroupName;
	}

	public void setProducerGroupName(String producerGroupName) {
		this.producerGroupName = producerGroupName;
	}

}
