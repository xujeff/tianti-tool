package com.jeff.tianti.tool.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * kafka生产者配置信息类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Configuration
@EnableConfigurationProperties({ KafkaProducerProperties.class })
public class KafkaProducerConfig {

	@Autowired
	private KafkaProducerProperties kafkaProducerProperties;

	// producer配置信息初始化
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		if (kafkaProducerProperties != null) {
			props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getServers());
			props.put(ProducerConfig.RETRIES_CONFIG, kafkaProducerProperties.getRetries());
			props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProducerProperties.getBatchSize());
			props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProducerProperties.getLinger());
			props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProducerProperties.getBufferMemory());
			props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		}
		return props;
	}

	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	/**
	 * kafka操作句柄
	 * 
	 * @return
	 */
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<String, String>(producerFactory());
	}

}
