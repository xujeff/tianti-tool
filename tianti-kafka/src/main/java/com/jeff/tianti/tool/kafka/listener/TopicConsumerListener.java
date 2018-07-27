package com.jeff.tianti.tool.kafka.listener;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * kafka消费者监听类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Component
public class TopicConsumerListener {

	private final static Logger logger = LoggerFactory.getLogger(TopicConsumerListener.class);

	// 监听LOG_TOPIC信息
	@KafkaListener(topics = { "LOG_TOPIC" })
	public void logTopicLister(ConsumerRecord<String, String> consumerRecord)throws JsonParseException, JsonMappingException, IOException {
		String data = consumerRecord.value();
		logger.info("接收到LOG_TOPIC消息：" + data);
	}

	// 监听NOTICE_TOPIC信息
	@KafkaListener(topics = { "NOTICE_TOPIC" })
	public void noticeTopicLister(ConsumerRecord<String, String> consumerRecord)throws JsonParseException, JsonMappingException, IOException {
		String data = consumerRecord.value();
		logger.info("接收到NOTICE_TOPIC消息：" + data);
	}

}
