package com.superproject.rocket.base.producer;

import java.util.Date;

public class SendMQResult {

	private Long id;

	private String namesrvAddr;//注册机服务名

	private String producerGroupName;//主题组

	private String Topic;// 消息主题

	private String tag;//主题分支

	private String key;//key

	private String messageContent;//消息内容

	private Date createDate;//创建时间

	private String msgId;//消息ID

	private Long queueOffset;//消息数目

	private String exceptionMsg;//消息发送失败的原因

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Long getQueueOffset() {
		return queueOffset;
	}

	public void setQueueOffset(Long queueOffset) {
		this.queueOffset = queueOffset;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamesrvAddr() {
		return namesrvAddr;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}

	public String getProducerGroupName() {
		return producerGroupName;
	}

	public void setProducerGroupName(String producerGroupName) {
		this.producerGroupName = producerGroupName;
	}

	public String getTopic() {
		return Topic;
	}

	public void setTopic(String topic) {
		Topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

}
