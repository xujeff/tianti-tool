package com.jeff.tianti.tool.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

/**
 * kafka消费者配置信息类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Configuration
@EnableConfigurationProperties({ KafkaConsumerProperties.class })
public class KafkaConsumerConfig {

	@Autowired
	private KafkaConsumerProperties kafkaConsumerProperties;

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		if (kafkaConsumerProperties != null) {
			factory.setConsumerFactory(consumerFactory());
			factory.setConcurrency(kafkaConsumerProperties.getConcurrency());
			factory.getContainerProperties().setPollTimeout(1500);
		}
		return factory;
	}

	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	// consumer配置信息初始化
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> propsMap = new HashMap<>();
		if (kafkaConsumerProperties != null) {
			propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.getServers());
			propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaConsumerProperties.getEnableAutoCommit());
			propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,
					kafkaConsumerProperties.getAutoCommitInterval());
			propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaConsumerProperties.getSessionTimeout());
			propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerProperties.getGroupId());
			propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerProperties.getAutoOffsetReset());
		}
		return propsMap;
	}

}
