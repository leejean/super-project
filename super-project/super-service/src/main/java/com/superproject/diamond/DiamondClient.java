package com.superproject.diamond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.superproject.utils.SuperPropertyPlaceholderConfigurer;

/**
 * 
 * @author leejean
 *
 */
public class DiamondClient extends SuperPropertyPlaceholderConfigurer{
	
	private static DiamondClient tshDiamondClient = new DiamondClient();
	
	public static DiamondClient getInstance() {
		return tshDiamondClient;
	}
	public void init(){	
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("group", "superproject");
		map1.put("dataId", "jdbc");
		list.add(map1);
		this.loadMultConfigFromDiamond(list);
	}
//	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//				new String[] { "applicationContext.xml" });
//		Bean bean = (Bean) context.getBean("bean");
//		System.out.println("url:"+bean.getMsg());
//		context.start();
//		
//	}
}
