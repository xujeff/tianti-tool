package com.jeff.tianti.tool.kafka.dto;

import java.util.Date;

/**
 * 通知信息封装对象
 * @author Jeff Xu
 * @since 2018-07-18
 */
public class NoticeMessageDTO {
	
	//通知标题
	private String title;
	
	//通知内容
	private String content;
	
	//通知时间
	private Date noticeDate;
	
	//发布者
	private String publisher;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}
