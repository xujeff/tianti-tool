package com.jeff.tianti.tool.swagger;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger启动类
 * 访问路径：http://localhost:8002/swagger-ui.html
 * @author Jeff Xu
 * @since 2018-07-18
 */
@SpringBootApplication
@EnableSwagger2
public class SwaggerApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SwaggerApplication.class).web(true).run(args);
	}

}
