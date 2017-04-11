package com.superproject.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 会员VO
 * @author leejean <br>
 * @version 1.0.0 2016年2月23日下午5:27:03<br>
 * @see 
 * @since JDK 1.7.0
 */
public class UserVo {
	/**
	 * 
	 */

	/**
	 * 主键
	 */
	private String id;
	
	private String nickname;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
