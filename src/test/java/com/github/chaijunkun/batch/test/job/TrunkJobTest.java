package com.github.chaijunkun.batch.test.job;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

public class TrunkJobTest extends AbstractFullLoadTest {
	
	@Autowired
	private JobLauncher launcher;
	
	/**注入指定任务*/
	@Resource(name = "trunkJobDemo")
	private Job job;
	
	@Test
	public void doTest() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		//自定义任务启动参数
		//区分不同任务的标志是任务名+参数哈希
		Map<String, JobParameter> params = new HashMap<String, JobParameter>();
		params.put("application", new JobParameter("report_center"));
		params.put("v", new JobParameter("1"));
		JobExecution jobExecution = launcher.run(job, new JobParameters(params));
		ExitStatus exitStatus = jobExecution.getExitStatus();
		logger.info("任务退出状态:{}", exitStatus.getExitCode());
	}

}
