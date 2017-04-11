package com.superproject.diamond;

import com.superproject.utils.SuperPropertyPlaceholderConfigurer;

public class CommonConfig {
	public static void init(SuperPropertyPlaceholderConfigurer tsh){
		System.out.println("=============:init2");
		String db_url = tsh.getConfig("db.url");
		System.out.println("db.url="+db_url);
		String abc = tsh.getConfig("abc");
		System.out.println("abc="+abc);
		
	}
}
