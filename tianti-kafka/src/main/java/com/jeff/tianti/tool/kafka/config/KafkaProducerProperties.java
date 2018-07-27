package com.jeff.tianti.tool.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Kafka生产者属性
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@ConfigurationProperties(prefix = KafkaProducerProperties.KAFKA_PRODUCER_PREFIX)
public class KafkaProducerProperties {

	public static final String KAFKA_PRODUCER_PREFIX = "spring.data.kafka.producer";

	// 用于建立与kafka集群连接的host/port组。数据将会在所有servers上均衡加载。列表格式:host1:port1,host2:port2,…
	private String servers;

	// 重连次数。设置大于0的值将使客户端重新发送任何数据，一旦这些数据发送失败。
	private int retries;

	// 每次批量发送消息的数量
	private int batchSize;

	private int linger;

	private int bufferMemory;

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public int getLinger() {
		return linger;
	}

	public void setLinger(int linger) {
		this.linger = linger;
	}

	public int getBufferMemory() {
		return bufferMemory;
	}

	public void setBufferMemory(int bufferMemory) {
		this.bufferMemory = bufferMemory;
	}

}
