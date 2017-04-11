package com.superproject.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.alibaba.fastjson.JSON;
import com.superproject.service.myservice.SuperService;
import com.superproject.vo.UserVo;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml"})

@TestExecutionListeners(listeners={
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class
})
public class Test {
	@Autowired
	SuperService superService;
	@org.junit.Test
	public void test(){
		try {
			UserVo userVo = superService.get(1L);
			System.out.println(JSON.toJSONString(userVo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
