package com.superproject.web.controller;

import java.io.Serializable;

public enum ReturnStatusEnum implements Serializable{
	OK(200, ""),
	Error(500, "");
	ReturnStatusEnum(int status, String info) {
		this.setValue(status);
		this.setInfo(info);
	}

	private int value;

	private String info;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}

	