package com.github.chaijunkun.batch.test.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

class TryToGetSysClock implements RetryCallback<Long, Exception> {
	
	private Logger log;
	
	private int failureCounter = 0;
	
	private String msg = "获取系统时钟异常";
	
	//重试逻辑传递上下文
	public TryToGetSysClock(Logger log) {
		this.log = log;
	}

	@Override
	public Long doWithRetry(RetryContext context) throws Exception {
		while (failureCounter<6) {
			failureCounter++;
			log.error("msg:{}, failure counter:{}.", msg, failureCounter);
			throw new Exception(msg);
		}
		log.info("重试成功");
		return System.currentTimeMillis();
	}
	
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-retry.xml")
public class RetryTemplateTest {
	
	private static final Logger log = LoggerFactory.getLogger(RetryTemplateTest.class);
	
	@Autowired
	private RetryTemplate retryTemplate;
	
	@Test
	public void doTest(){
		try {
			Long sysClock = retryTemplate.execute(new TryToGetSysClock(log));
			log.info("获取到系统时钟:{}", sysClock);
		} catch (Exception e) {
			log.error("实在获取不到了", e);
		}
	}

}
