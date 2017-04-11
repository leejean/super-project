package com.superproject.web.controller;

import java.io.Serializable;

/**
 * 
 * @author sd
 *
 */
public class ReturnDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int status;

	/**
	 * 
	 */
	protected String msg = null;
	protected Object data;
	public ReturnDTO() {
	}
	public ReturnDTO(int status) {
		this.status = status;
	}
	

	public ReturnDTO(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public static ReturnDTO OK() {
		ReturnDTO dto = new ReturnDTO(ReturnStatusEnum.OK.getValue());
		return dto;
	}
	public static ReturnDTO OK(Object data) {
		ReturnDTO dto = new ReturnDTO(ReturnStatusEnum.OK.getValue());
		dto.setData(data);
		return dto;
	}
	public static ReturnDTO OK(Object data, String msg) {
		ReturnDTO dto = new ReturnDTO(ReturnStatusEnum.OK.getValue());
		dto.setMsg(msg);
		dto.setData(data);
		return dto;
	}
	public static ReturnDTO NO(int status, String msg) {
		ReturnDTO dto = new ReturnDTO(status);
		dto.setMsg(msg);
		return dto;
	}

	public void success(Object data) {
		this.status = ReturnStatusEnum.OK.getValue();
		this.data = data;
	}

	public void success(String msg, Object data) {
		this.status = ReturnStatusEnum.OK.getValue();
		this.msg = msg;
		this.data = data;
	}

	public void error(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}
}