package com.github.chaijunkun.batch.steps.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

public class StepMain implements Tasklet {

	private static final Logger log = LoggerFactory.getLogger(StepMain.class);
	
	private RetryTemplate retryTemplate;
	
	public RetryTemplate getRetryTemplate() {
		return retryTemplate;
	}

	public void setRetryTemplate(RetryTemplate retryTemplate) {
		this.retryTemplate = retryTemplate;
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("主要步骤执行");
		retryTemplate.execute(new RetryCallback<Object, Exception>() {
			private int failureCounter = 0;
			private String msg = "故意的异常";
			@Override
			public Object doWithRetry(RetryContext context) throws Exception {
				//此处如果设置成3则会执行branch1
				//此处如果设置成6则会执行branch2
				//详见spring-batch-tasklet关于stepMain的返回值定义
				while (failureCounter<6) {
					failureCounter++;
					log.error("msg:{}, failure counter:{}.", msg, failureCounter);
					throw new Exception(msg);
				}
				log.info("重试成功");
				return null;
			}
		});
		return RepeatStatus.FINISHED;
	}

}
