package com.github.chaijunkun.batch.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CommonStepListener implements StepExecutionListener {
	
	private static final Logger log = LoggerFactory.getLogger(CommonStepListener.class);

	private long startTime;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		log.info("========[{}]-start========", stepExecution.getStepName());
		startTime = System.currentTimeMillis();
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		long endTime = System.currentTimeMillis();
		log.info("========[{}]-end-execute-time-[{}]ms========", stepExecution.getStepName(), endTime - startTime);
		//这里可以获得执行上下文
		//stepExecution.getExecutionContext();
		//一般直接返回任务执行的状态,不做修改
		return stepExecution.getExitStatus();
	}

}
