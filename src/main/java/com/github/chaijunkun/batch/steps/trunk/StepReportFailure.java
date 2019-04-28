package com.github.chaijunkun.batch.steps.trunk;

import com.github.chaijunkun.batch.base.CommonStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepReportFailure extends CommonStep implements Tasklet {
	
	private static final Logger log = LoggerFactory.getLogger(StepReportFailure.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("任务报告-执行失败");
		return RepeatStatus.FINISHED;
	}
	
}
