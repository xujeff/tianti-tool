package com.jeff.tianti.tool.readwrite.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.jeff.tianti.tool.readwrite.config.dbconfig.DataSourceContextHolder;
import com.jeff.tianti.tool.readwrite.config.dbconfig.DataSourceType;

/**
 * 在service层决定使用哪个数据源（AbstractRoutingDataSource+aop+annotation）
 * 我们采用这种方式，重点理解下该方法实现方式。
 * 优点：可以支持事务。
 * 缺点：类内部方法通过this.xx()方式相互调用时，aop不会进行拦截，需进行特殊处理。
 * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行 如果一旦开始切换到写库，则之后的读都会走写库
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Component
public class DataSourceAopInService implements PriorityOrdered {

	@Before("execution(* com.jeff.tianti.tool.readwrite.service..*.*(..)) "
			+ " and @annotation(com.jeff.tianti.tool.readwrite.annotation.ReadDataSource) ")
	public void setReadDataSourceType() {
		// 如果已经开启写事务了，那之后的所有读都从写库读
		if (!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())) {
			DataSourceContextHolder.setRead();
		}
	}

	@Before("execution(* com.jeff.tianti.tool.readwrite.service..*.*(..)) "
			+ " and @annotation(com.jeff.tianti.tool.readwrite.annotation.ReadDataSource) ")
	public void setWriteDataSourceType() {
		DataSourceContextHolder.setWrite();
	}

	@Override
	public int getOrder() {
		/**
		 * 值越小，越优先执行 要优于事务的执行 在启动类中加上了@EnableTransactionManagement(order = 10)
		 */
		return 1;
	}

}
