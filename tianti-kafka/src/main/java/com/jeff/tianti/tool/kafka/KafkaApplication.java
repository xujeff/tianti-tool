package com.jeff.tianti.tool.kafka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * kafka启动类
 * （使用该功能需要在本地安装好Zookeeper和kafka）
 * @author Jeff Xu
 * @since 2018-07-18
 */
@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(KafkaApplication.class).web(true).run(args);
	}

}
