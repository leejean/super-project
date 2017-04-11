package com.superproject.rocket.gms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * TODO 商品消息处理消息处理.<br>
 * @author leejean <br>
 * @version 1.0.0 2016年10月25日 上午11:49:50<br>
 * @see 
 * @since JDK 1.7.0
 */
public class GmsMessageListener {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 解析，调用消费者服务dubbo
	 * @author leejean <br>
	 * @Date 2016年10月26日 下午2:05:30<br>
	 * @param msgContext 消息内容
	 * @param topic 主题名称 根据主题名称实现不同转发
	 * @param tag tag
	 * @return
	 */
	public Boolean consumeMessage(String msgContext,String topic,String tag) {
		JSONObject msgJSONObject = (JSONObject) JSON.parse(msgContext);
		String bis_type = msgJSONObject.getString("bis_type");
		String goods_id = msgJSONObject.getString("goods_id");
		String data = msgJSONObject.getString("data");
		System.out.println(bis_type);
		System.out.println(goods_id);
		System.out.println(data);
		return true;
	}
}

