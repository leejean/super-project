package com.superproject.rocket.base.consumer;

import java.util.Arrays;
import java.util.List;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.superproject.rocket.base.listener.RocketMQListener;

/**
 * RocketMQ消费者注册
 * @author leejean
 *
 */
public class RocketMQConsumer extends BaseMQConsumer {

	private String consumerType;//接收消息方式: BROADCASTING广播形式  CLUSTERING集群消费

	private String Topic;

	private String tags;

	private RocketMQListener rocketMQListener;

	/**
	 * BROADCAST   广播消费模式
	 * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
	 * 但是实际PushConsumer内部是使用长轮询Pull方式从Broker拉消息，然后再回调用户Listener方法<br>
	 * namesrvAddr  172.16.1.193:9876 为示例
	 * consumerGroupName  一个消费主题组
	 * Topic一个主题 可以参数形式：TopicA  多个主题 ：TopicA,TopicB,TopicC,TopicD  注意：一个consumer对象可以订阅多个Topic
	 * tags: 格式"TagA || TagC || TagD"
	 */
	public void init() throws InterruptedException, MQClientException {
		//1.连接
		if (null == consumer) {
			initConnetion();
		}

		/**
		 * 订阅指定topic下tags分别等于TagA或TagC或TagD
		 */
		//consumer.subscribe(Topic, tags);
		List<String> topList = Arrays.asList(Topic.split(","));
		for (String curTopic : topList) {
			consumer.subscribe(curTopic, "*");
		}

		/**
		 * 订阅指定topic下所有消息<br>
		 * 注意：一个consumer对象可以订阅多个topic
		 */
		//        consumer.subscribe("TopicTest22", "*");
		//        consumer.subscribe("TopicTest33", "*");
		if (consumerType.equals("BROADCASTING")) {
			consumer.setMessageModel(MessageModel.BROADCASTING);
		} else if (consumerType.equals("CLUSTERING")) {
			consumer.setMessageModel(MessageModel.CLUSTERING);
		}
		/**
		 * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
		 * 如果非第一次启动，那么按照上次消费的位置继续消费
		 */
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

		consumer.registerMessageListener(rocketMQListener);

		//		Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues("PushTopic");
		//		for (MessageQueue messageQueue : messageQueues) {
		//			System.out.println(messageQueue.getTopic());
		//		}

		/**
		 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 */
		consumer.start();

		logger.info("=====================>消息监听启动成功：messageConsumerListener={"+rocketMQListener.getClass().getName()+"},Topic={"+Topic+"}");
	}

	public String getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(String consumerType) {
		this.consumerType = consumerType;
	}

	public String getTopic() {
		return Topic;
	}

	public void setTopic(String topic) {
		Topic = topic;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public RocketMQListener getRocketMQListener() {
		return rocketMQListener;
	}

	public void setRocketMQListener(RocketMQListener rocketMQListener) {
		this.rocketMQListener = rocketMQListener;
	}

}
