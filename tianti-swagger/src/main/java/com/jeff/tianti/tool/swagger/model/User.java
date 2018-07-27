package com.jeff.tianti.tool.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息类
 * @author Jeff Xu
 * @since 2018-07-18
 */
@ApiModel(value="用户对象",description="用户（User）对象信息")
public class User {
	
	//账号
	@ApiModelProperty(value="账号",name="accout")
	private String accout;
	
	//密码
	@ApiModelProperty(value="密码",name="pwd")
	private String pwd;

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
