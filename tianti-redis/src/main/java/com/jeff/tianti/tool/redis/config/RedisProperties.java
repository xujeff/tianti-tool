package com.jeff.tianti.tool.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis属性类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@ConfigurationProperties(prefix = RedisProperties.REDIS_PREFIX)
public class RedisProperties {

	public static final String REDIS_PREFIX = "spring.data.redis";

	// 最大连接数
	private Integer maxTotal;

	// 最大空闲时间
	private Integer maxIdle;

	// 每次最大连接数
	private Integer numTestsPerEvictionRun;

	// 释放扫描的扫描间隔
	private Integer timeBetweenEvictionRunsMillis;

	// 连接的最小空闲时间
	private Integer minEvictableIdleTimeMillis;

	// 连接控歘按时间多久后释放，当空闲时间>该值且空闲连接>最大空闲连接数时直接释放
	private Integer softMinEvictableIdleTimeMillis;

	// 获得链接时的最大等待毫秒数，小于0：阻塞不确定时间，默认-1
	private Integer maxWaitMillis;

	// 在获得链接的时候检查有效性，默认false
	private Boolean testOnBorrow;

	// 在空闲时检查有效性，默认false
	private Boolean testWhileIdle;

	// 连接耗尽时是否阻塞，false报异常，true阻塞超时,默认true
	private Boolean blockWhenExhausted;

	private Integer maxRedirects;
	// 节点
	private String clusters;

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public Integer getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public Integer getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public Integer getSoftMinEvictableIdleTimeMillis() {
		return softMinEvictableIdleTimeMillis;
	}

	public void setSoftMinEvictableIdleTimeMillis(Integer softMinEvictableIdleTimeMillis) {
		this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
	}

	public Integer getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(Integer maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public Boolean getBlockWhenExhausted() {
		return blockWhenExhausted;
	}

	public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
		this.blockWhenExhausted = blockWhenExhausted;
	}

	public Integer getMaxRedirects() {
		return maxRedirects;
	}

	public void setMaxRedirects(Integer maxRedirects) {
		this.maxRedirects = maxRedirects;
	}

	public String getClusters() {
		return clusters;
	}

	public void setClusters(String clusters) {
		this.clusters = clusters;
	}

}
