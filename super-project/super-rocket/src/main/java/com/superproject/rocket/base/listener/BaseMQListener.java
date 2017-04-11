package com.superproject.rocket.base.listener;

import javax.annotation.Resource;

import com.superproject.rocket.gms.GmsMessageListener;

/**
 * 消息监听处理分发
 * TODO 在此写上类的相关说明.<br>
 * @author leejean <br>
 * @version 1.0.0 2016年10月25日 下午3:49:34<br>
 * @see 
 * @since JDK 1.7.0
 */
public class BaseMQListener extends RocketMQListener {

	@Resource
	private GmsMessageListener gmsMessageListener;
//	@Resource
//	private GoodsStatusUpdateMQListener goodsStatusUpdateMQListener;

	/**
	 * 处理成功返回true,处理失败返回false
	 */
	@Override
	public boolean consumeMessage(String msgContext, String topic, String tags) {
		Boolean flag = Boolean.FALSE;
		//不同主题分流处理
		if (true) {
			flag = gmsMessageListener.consumeMessage(msgContext, topic, tags);
		}else{
			logger.error("未知的主题:"+topic);
		}
		return flag;
	}
}
