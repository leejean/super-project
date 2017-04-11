package com.superproject.rocket.base.consumer;


import org.apache.log4j.Logger;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * BaseRocketMQConsumer基类 
 * @author leejean
 *
 */
public abstract class BaseMQConsumer {

	protected Logger logger = Logger.getLogger(getClass());

	protected DefaultMQPushConsumer consumer;

	protected String rocketMQUrl;// 注册机服务名

	protected String consumerGroupName;// 主题组

	protected void initConnetion() {
		/**
		 * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ConsumerGroupName需要由应用来保证唯一
		 */
		consumer = new DefaultMQPushConsumer(consumerGroupName);
		consumer.setNamesrvAddr(rocketMQUrl);
	}

	public String getRocketMQUrl() {
		return rocketMQUrl;
	}

	public void setRocketMQUrl(String rocketMQUrl) {
		this.rocketMQUrl = rocketMQUrl;
	}

	public String getConsumerGroupName() {
		return consumerGroupName;
	}

	public void setConsumerGroupName(String consumerGroupName) {
		this.consumerGroupName = consumerGroupName;
	}

}
