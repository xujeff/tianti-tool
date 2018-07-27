package com.jeff.tianti.tool.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger的配置类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Configuration
public class SwaggerConfig {

	//开启状态
	@Value("${swagger.enable}")
	private boolean enable;
	
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jeff.tianti.tool.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("tianti-swagger的使用DEMO")
                .description("swagger使用demo")
                .termsOfServiceUrl("http://localhost:8002")
                .contact("Jeff Xu")
                .version("1.0")
                .build();
    }

}
