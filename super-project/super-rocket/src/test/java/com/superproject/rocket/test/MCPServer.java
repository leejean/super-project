/*
 * Copyright 2015-2030 Dtds.Inc All Rights Reserved.
 */
package com.superproject.rocket.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * dubbo 服务启动类.<br>
 * @author caock <br>
 * @version 1.0.0 2016年6月4日 下午3:52:04<br>
 * @see 
 * @since 3.0
 */
public class MCPServer {

	/**
	 * .
	 */
	private static Log log = LogFactory.getLog(MCPServer.class);
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			//String[] config={"classpath:webconfig/spring-context.xml","classpath:webconfig/spring-mvc.xml","classpath:webconfig/spring-server-provider.xml"};
			String[] config={"classpath:applicationContext-mcp.xml"};
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
			context.start();
			log.info("== MCPServer started!");

			//System.in.read();//利用输入流阻塞
		} catch (Exception e) {
			log.error("== MCPServer context start error:",e);
		}

		synchronized (MCPServer.class) {
			while (true) {//也可利用利用输入流阻塞
				try {
					log.info("MCPServer keeping living");
					MCPServer.class.wait();
				} catch (InterruptedException e) {
					log.error("MCPServer keeping living error:",e);
				}
			}
		}
	}
}

