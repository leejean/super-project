package com.superproject.rocket.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.Message;
import com.superproject.rocket.base.producer.RocketMQProducer;
import com.superproject.rocket.base.producer.SendMQResult;
import javax.annotation.Resource;
/** 
 * RocketMQ测试类 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-mcp.xml" })
public class TestRocketMQ {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private RocketMQProducer rocketMQProducer;

//	@Resource
//	private MessageServiceImpl messageServiceImpl;

	/**
	 * 发送消息主方法
	 */
	@Test
	public void sendRocketMQ() {
		try {
			GoodsVo goodsVo = new GoodsVo();
			goodsVo.setGoods_id(20161001L);
			goodsVo.setGoods_name("格力空调");

			//0.发送第一个topic
			Message sendMessage = rocketMQProducer.packageMessage("gms_goods_update", "tag-test", "goodsId", JSON.toJSONString(goodsVo));
			Map<String, Object> resultMap = rocketMQProducer.sendMessage(sendMessage);
			SendMQResult resultMsg = (SendMQResult) resultMap.get("resultMsg");
			if ((Boolean) resultMap.get("sendSuccess")) {
				logger.info("gms_goods_update发送消息成功：resultMsg={},resultMap={}", resultMsg, JSON.toJSON(resultMap));
			} else {
				logger.info("gms_goods_update发送消息失败：resultMsg={},resultMap={}", resultMsg, JSON.toJSON(resultMap));
			}

//			//1.发送第一个topic
//			sendMessage = rocketMQProducer.packageMessage("gms_goods_info_update", "tag-test", "goodsId", JSON.toJSONString(goodsVo));
//			resultMap = rocketMQProducer.sendMessage(sendMessage);
//			resultMsg = (SendMQResult) resultMap.get("resultMsg");
//			if ((boolean) resultMap.get("sendSuccess")) {
//				logger.info("gms_goods_info_update发送消息成功：resultMsg={},resultMap={}", resultMsg, JSON.toJSON(resultMap));
//			} else {
//				logger.info("gms_goods_info_update发送消息失败：resultMsg={},resultMap={}", resultMsg, JSON.toJSON(resultMap));
//			}
//
//			//2.发送第二个topic
//			sendMessage = rocketMQProducer.packageMessage("gms_goods_status_update", "tag-test", "goodsId", JSON.toJSONString(goodsVo));
//			resultMap = rocketMQProducer.sendMessage(sendMessage);
//			resultMsg = (SendMQResult) resultMap.get("resultMsg");
//			if ((boolean) resultMap.get("sendSuccess")) {
//				logger.info("gms_goods_status_update发送消息成功：resultMsg={},resultMap={}", resultMsg, JSON.toJSON(resultMap));
//			} else {
//				logger.info("gms_goods_status_update发送消息失败：resultMsg={},resultMap={}", resultMsg, JSON.toJSON(resultMap));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	@Test
//	public void sendGoodsPublisherMessage() {
//		Boolean flag = messageServiceImpl.sendGoodsPublisherMessage("12150", GoodsPublisherType.ADD);
//		System.out.println(flag);
//	}
}
