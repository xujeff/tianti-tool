package com.jeff.tianti.tool.redis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * redis启动类
 * （使用该功能需要在本地安装好redis）
 * @author Jeff Xu
 * @since 2018-07-18
 */
@SpringBootApplication
public class RedisApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(RedisApplication.class).web(true).run(args);
	}

}
