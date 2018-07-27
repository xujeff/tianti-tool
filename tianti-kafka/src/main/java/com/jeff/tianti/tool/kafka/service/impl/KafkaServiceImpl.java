package com.jeff.tianti.tool.kafka.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jeff.tianti.tool.kafka.service.KafkaService;

/**
 * kafka业务服务实现类
 * @author Jeff Xu
 * @since 2017-12-15
 */
@Service
public class KafkaServiceImpl<T> implements KafkaService<T> {
		
    @Autowired
    private KafkaTemplate<String, T> kafkaTemplate;
	
    /**
	 * kafka给某个topic发送消息
	 * @param topic 主题
	 * @param data 值
	 * @return 是否发送成功
	 */
	@Override
	public void send(String topic, T data){
		kafkaTemplate.send(topic, data);
	}
    
	/**
	 * kafka给某个topic发送消息
	 * @param topic 主题
	 * @param key   键
	 * @param data 值
	 * @return 是否发送成功
	 */
	@Override
	public void send(String topic, String key, T data){
		kafkaTemplate.send(topic, key, data);
	}
	
	/**
	 * kafka分区给某个topic发送消息
	 * @param topic 主题
	 * @param partition 分区
	 * @param data  值
	 */
	@Override
	public void send(String topic, int partition, T data) {
		kafkaTemplate.send(topic, partition, data);
	}
	
	/**
	 * kafka分区给某个topic发送消息
	 * @param topic 主题
	 * @param partition 分区
	 * @param key   键
	 * @param data 值
	 */
	@Override
	public void send(String topic, int partition, String key, T data){
		kafkaTemplate.send(topic, partition, key, data);
	}

}
