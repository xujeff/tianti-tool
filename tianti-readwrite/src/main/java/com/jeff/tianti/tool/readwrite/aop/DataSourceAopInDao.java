package com.jeff.tianti.tool.readwrite.aop;

import org.aspectj.lang.annotation.Before;

import com.jeff.tianti.tool.readwrite.config.dbconfig.DataSourceContextHolder;

/**
 * 在DAO层决定使用哪个数据源（使用AbstractRoutingDataSource+aop+annotation）
 * 我们不采用这种方式
 * 缺点：使用该方式不支持事务。因为事务在service层开启时，就必须拿到数据源了。
 * @author Jeff Xu
 * @since 2018-07-18
 */
public class DataSourceAopInDao {

	@Before("execution(* com.jeff.tianti.tool.readwrite.dao..*.find*(..)) "
			+ " or execution(* com.jeff.tianti.tool.readwrite.dao..*.get*(..)) "
			+ " or execution(* com.jeff.tianti.tool.readwrite.dao..*.query*(..))")
	public void setReadDataSourceType() {
		DataSourceContextHolder.setRead();
	}

	@Before("execution(* com.jeff.tianti.tool.readwrite.dao..*.insert*(..)) "
			+ " or execution(* com.jeff.tianti.tool.readwrite.dao..*.update*(..))"
			+ " or execution(* com.jeff.tianti.tool.readwrite.dao..*.add*(..))")
	public void setWriteDataSourceType() {
		DataSourceContextHolder.setWrite();
	}

}
