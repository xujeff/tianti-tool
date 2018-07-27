package com.jeff.tianti.tool.readwrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 读写分离启动入口
 * @author Jeff Xu
 * @since 2018-07-18
 */
@EnableAutoConfiguration
@EnableTransactionManagement(order = 10) // 开启事务，并设置order值，默认是Integer的最大值
@ComponentScan(basePackages = { "com.jeff.tianti.tool" })
@SpringBootApplication
public class ReadWriteApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ReadWriteApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ReadWriteApplication.class, args);
	}
	
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		
	}
	
}
