package com.github.chaijunkun.batch.steps.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepBranch1 implements Tasklet {
	
	private static final Logger log = LoggerFactory.getLogger(StepBranch1.class);

	public StepBranch1() {
		log.info("实例化类:{}", getClass().getSimpleName());
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("运行了branch 1");
		//重复状态若写成CONTINUABLE,则会重复执行本方法(execute)
		//这种用法可用于接MQ:消息一个一个地接,将消息放入上下文,积攒到一定数量之后继续走后续流程
		//return RepeatStatus.CONTINUABLE;
		//重复状态若写成FINISHED,则跳出该步骤
		return RepeatStatus.FINISHED;
	}

}
