package com.jeff.tianti.tool.kafka.service;

/**
 * kafka服务接口
 * @author Jeff Xu
 * @since 2018-07-18
 */
public interface KafkaService<T> {
	
	/**
	 * kafka给某个topic发送消息
	 * @param topic 主题
	 * @param data 值
	 * @return 是否发送成功
	 */
	void send(String topic, T data);
	
	/**
	 * kafka给某个topic发送消息
	 * @param topic 主题
	 * @param key   键
	 * @param data 值
	 * @return 是否发送成功
	 */
	void send(String topic, String key, T data);
	

	/**
	 * kafka分区给某个topic发送消息
	 * @param topic 主题
	 * @param partition 分区
	 * @param data  值
	 */
	void send(String topic, int partition, T data);
	
	/**
	 * kafka分区给某个topic发送消息
	 * @param topic 主题
	 * @param partition 分区
	 * @param key   键
	 * @param data 值
	 */
	void send(String topic, int partition, String key, T data);
	
}
