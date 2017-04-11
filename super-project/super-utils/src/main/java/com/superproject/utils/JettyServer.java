package com.superproject.utils;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
/**
 * 
 * @author leejean
 *
 */
public class JettyServer {
	private static Logger logger  =  Logger.getLogger(JettyServer.class);
	/**
	 * 默认端口
	 */
	private static final int port = 8080;
	/**
	 * 资源根目录
	 */
	private static String resourceBase = "src/main/webapp/";
	/**
	 * web.xml 位置
	 */
	private static String webPath = "/src/main/webapp/WEB-INF/web.xml";
	/**
	 * 项目发布根路径
	 */
	private static final String basePath = "/";
	/**
	 * 项目根路径
	 */
	private static String path = System.getProperty("user.dir").replace("\\", "/");
	
	public static void run(int port, String basePath) {
		try {
			logger.info("=====项目启动信息:http://localhost:"+port+basePath);
			//端口
			Server server = new Server(port);
			WebAppContext context = new WebAppContext();
			//web配置路径
			context.setDescriptor(path+webPath);
			//资源根目录
			context.setResourceBase(resourceBase);
			//定义项目名称"/project/"
			context.setContextPath(basePath);
			
			context.setParentLoaderPriority(true);
			
			server.setHandler(context);
			
			server.start();
			
			server.join();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param port 启动端口
	 */
	public static void run(int port) {
		JettyServer.run(port, basePath);
	}
	public static String getResourceBase() {
		return resourceBase;
	}
	public static void setResourceBase(String resourceBase) {
		JettyServer.resourceBase = resourceBase;
	}
	public static String getWebPath() {
		return webPath;
	}
	public static void setWebPath(String webPath) {
		JettyServer.webPath = webPath;
	}
	public static String getPath() {
		return path;
	}
	public static void setPath(String path) {
		JettyServer.path = path;
	}
	public static int getPort() {
		return port;
	}
	public static String getBasepath() {
		return basePath;
	}
	
	
	
}
