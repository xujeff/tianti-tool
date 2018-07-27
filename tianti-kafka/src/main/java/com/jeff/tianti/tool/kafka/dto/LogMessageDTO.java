package com.jeff.tianti.tool.kafka.dto;

import java.util.Date;

/**
 * 日志信息封装对象
 * @author Jeff Xu
 * @since 2018-07-18
 */
public class LogMessageDTO {
	
	//访问url
	private String url;
	
	//访问时间
	private Date createDate;
	
	//访问ip
	private String ip;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
