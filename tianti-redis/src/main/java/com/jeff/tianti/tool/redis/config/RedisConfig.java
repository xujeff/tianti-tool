package com.jeff.tianti.tool.redis.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置信息类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Configuration
@EnableConfigurationProperties({ RedisProperties.class })
public class RedisConfig {
	
	@Autowired
    private RedisProperties redisProperties;

	/**
	 * 获取redis配置
	 * @return
	 */
    @Bean
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();

        if (redisProperties != null && redisProperties.getMaxTotal() != null) {
            config.setMaxTotal(redisProperties.getMaxTotal());
            config.setMaxIdle(redisProperties.getMaxIdle());
            config.setNumTestsPerEvictionRun(redisProperties.getNumTestsPerEvictionRun());
            config.setTimeBetweenEvictionRunsMillis(redisProperties.getTimeBetweenEvictionRunsMillis());
            config.setMinEvictableIdleTimeMillis(redisProperties.getMinEvictableIdleTimeMillis());
            config.setSoftMinEvictableIdleTimeMillis(redisProperties.getSoftMinEvictableIdleTimeMillis());
            config.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
            config.setTestOnBorrow(redisProperties.getTestOnBorrow());
            config.setTestWhileIdle(redisProperties.getTestWhileIdle());
            config.setBlockWhenExhausted(redisProperties.getBlockWhenExhausted());
        }


        return config;
    }

    /**
     * 获取redis集群配置
     * @return
     */
    @Bean
    public RedisClusterConfiguration getRedisClusterConfiguration() {

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

        if (redisProperties != null && redisProperties.getMaxRedirects() != null) {
            redisClusterConfiguration.setMaxRedirects(redisProperties.getMaxRedirects());

            String clusters = redisProperties.getClusters();
            if (StringUtils.isNotBlank(clusters)) {
                String[] strs = clusters.split(",");
                Set<RedisNode> nodes = new HashSet<RedisNode>(strs.length);
                for (String str : strs) {
                    String[] ss = str.split(":");
                    RedisNode redisNode = new RedisNode(ss[0], Integer.parseInt(ss[1]));
                    nodes.add(redisNode);
                }
                redisClusterConfiguration.setClusterNodes(nodes);
            }
        }

        return redisClusterConfiguration;
    }

    /**
     * 获取redis工厂连接
     * @return
     */
    @Bean(destroyMethod = "destroy")
    public JedisConnectionFactory getConnectionFactory() {

        RedisClusterConfiguration redisClusterConfiguration = getRedisClusterConfiguration();

        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration);

        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);

        return factory;
    }

    /**
     * 获取redis操作句柄
     * @return
     */
    @Bean
    public RedisTemplate<?, ?> getRedisTemplate() {
        RedisTemplate<?, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(getConnectionFactory());

        return template;
    }

}
