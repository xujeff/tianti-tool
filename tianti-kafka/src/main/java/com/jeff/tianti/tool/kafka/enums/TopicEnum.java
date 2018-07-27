package com.jeff.tianti.tool.kafka.enums;
/**
 * 主题（Topic）枚举类
 * @author Jeff Xu
 * @since 2018-07-18
 */
public enum TopicEnum {
	
	LOG_TOPIC("LOG_TOPIC","日志Topic"),
	NOTICE_TOPIC("NOTICE_TOPIC","通知Topic");
	
	//编码
	private String code;
	
	//名称
	private String name;
	
	private TopicEnum(String code,String name){
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
