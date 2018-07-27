package com.jeff.tianti.tool.redis.dto;

import java.util.Date;

/**
 * 用户信息封装对象
 * @author Jeff Xu
 * @since 2018-07-18
 */
public class UserInfoDTO {
	
	//id不可变
	private String id;
	
	//用户名
	private String userName;
	
	//性别
	private Integer sex;
	
	//生日
	private Date birthDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
