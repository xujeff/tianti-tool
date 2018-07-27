package com.jeff.tianti.tool.readwrite.config.dbconfig;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据库源配置
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Configuration
public class DataSourceConfiguration {

	private static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);
	
	//数据源类型
	@Value("${mysql.datasource.type}")
	private Class<? extends DataSource> dataSourceType;
    
	/**
	 * 写数据源配置
	 * @return
	 */
	@Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "mysql.datasource.write")
    public DataSource writeDataSource() {
        log.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
	
	/**
     * 读数据源1配置
     * @return
     */
    @Bean(name = "readDataSource01")
    @ConfigurationProperties(prefix = "mysql.datasource.read01")
    public DataSource readDataSourceOne() {
        log.info("-------------------- read DataSource01 init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 读数据源2配置
     * @return
     */
    @Bean(name = "readDataSource02")
    @ConfigurationProperties(prefix = "mysql.datasource.read02")
    public DataSource readDataSourceTwo() {
        log.info("-------------------- read DataSource02 init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    
}
