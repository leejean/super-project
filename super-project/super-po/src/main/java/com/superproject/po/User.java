package com.superproject.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
/**
 * 会员PO
 * @author leejean <br>
 * @version 1.0.0 2016年2月23日下午5:27:03<br>
 * @see 
 * @since JDK 1.7.0
 */
@Entity
@Table(name="t_user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3783973040545401038L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name="id", insertable=false)
	private String id;
	
	/**
	 * 姓名.
	 */
	@Column(name="nickname")
	private String nickname;
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNickName() {
		return nickname;
	}



	public void setNickName(String nickname) {
		this.nickname = nickname;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
