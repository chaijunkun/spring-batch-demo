package com.github.chaijunkun.batch.test.job;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public abstract class AbstractFullLoadTest {
	
	public static final Logger logger = LoggerFactory.getLogger(AbstractFullLoadTest.class);

	private static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private long startTime;

	@Before
	public void doBefore(){
		logger.info("单元测试开始");
		startTime = System.currentTimeMillis();
	}

	@After
	public void doAfter(){
		long endTime = System.currentTimeMillis();
		logger.info("单元测试结束,耗时:{}毫秒", endTime - startTime);
		logger.info("=================");
	}

	/**
	 * 反射对象属性及其值并日志输出
	 * @param logger 日志记录器
	 * @param bean 要解析的对象
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	protected void logBean(Logger logger, Object bean) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		logger.info("=================显示bean内容=================");
		BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String key = propertyDescriptor.getName();
			// 过滤class属性  
			if (!key.equals("class")) {  
				// 得到property对应的getter方法  
				Method getter = propertyDescriptor.getReadMethod();  
				Object value = getter.invoke(bean);  
				if (value instanceof Date){
					logger.info("property:{}, value:{}", key, dateformat.format((Date)value));
				}else{
					logger.info("property:{}, value:{}", key, value);
				}
			}  
		}
	}


}
