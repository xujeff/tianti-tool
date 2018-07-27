package com.jeff.tianti.tool.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * kafka消费者配置信息类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@ConfigurationProperties(prefix = KafkaConsumerProperties.KAFKA_CONSUMER_PREFIX)
public class KafkaConsumerProperties {

	public static final String KAFKA_CONSUMER_PREFIX = "spring.data.kafka.consumer";

	// 指定kafka 代理地址，可以多个(直接用逗号分隔)
	private String servers;

	private boolean enableAutoCommit;

	private String sessionTimeout;

	private String autoCommitInterval;

	// 指定默认group id
	private String groupId;

	private String autoOffsetReset;

	// 指定listener 容器中的线程数，用于提高并发量
	private int concurrency;

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public boolean getEnableAutoCommit() {
		return enableAutoCommit;
	}

	public void setEnableAutoCommit(boolean enableAutoCommit) {
		this.enableAutoCommit = enableAutoCommit;
	}

	public String getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(String sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public String getAutoCommitInterval() {
		return autoCommitInterval;
	}

	public void setAutoCommitInterval(String autoCommitInterval) {
		this.autoCommitInterval = autoCommitInterval;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAutoOffsetReset() {
		return autoOffsetReset;
	}

	public void setAutoOffsetReset(String autoOffsetReset) {
		this.autoOffsetReset = autoOffsetReset;
	}

	public int getConcurrency() {
		return concurrency;
	}

	public void setConcurrency(int concurrency) {
		this.concurrency = concurrency;
	}

}
