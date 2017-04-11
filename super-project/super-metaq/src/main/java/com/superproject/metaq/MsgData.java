package com.superproject.metaq;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MsgData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3374694238694852839L;

	private Long id;
	
	private Object data;
	
	private String desc;
	
	public MsgData() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
