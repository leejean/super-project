package com.superproject.metaq;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:/applicationContext-metaq-customer.xml"})
public class TestMetaqCustomer{
	private Logger logger  =  Logger.getLogger(getClass());
	/**
	 * 
	 */
	@Test
	public void testSendMsg() {
		try{
//			MsgData msgData = new MsgData();
//			msgData.setId(System.currentTimeMillis());
//			msgData.setData(new Date().toLocaleString());
//			boolean producerRun = Producer.getInstance().producerRun(
//					Topic.SUPER_TOPIC,
//					"易理坚"+new Date().toLocaleString(),
//					"test"+new Date().toLocaleString());
//			logger.info("producerRun:"+producerRun);
			logger.info("==============running...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
