package com.github.chaijunkun.batch.steps.trunk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepReportSuccess implements Tasklet {
	
	private static final Logger log = LoggerFactory.getLogger(StepReportSuccess.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("任务报告-执行成功");
		return RepeatStatus.FINISHED;
	}
	
}
