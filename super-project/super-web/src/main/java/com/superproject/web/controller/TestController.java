package com.superproject.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.superproject.service.myservice.SuperService;

@Controller
@RequestMapping(value = "/test/")
public class TestController extends SuperController{
	@Autowired
	SuperService superService;
	@RequestMapping("test.do")
	@ResponseBody
	public String test(){
		logger.info("----");
		return new Date().toString();
	}
	@RequestMapping
	@ResponseBody
	public ReturnDTO test2(){
		return ReturnDTO.OK(new Date().toString());
	}
	/**
	 * http://localhost:9999/test/get.do?id=1
	 * @param id
	 * @return
	 */
	@RequestMapping("get.do")
	@ResponseBody
	public ReturnDTO get(Long id){
		try {
			return ReturnDTO.OK(superService.get(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
