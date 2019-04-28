package com.github.chaijunkun.batch.test.job;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskletJobTest extends AbstractFullLoadTest {
	
	@Autowired
	private JobLauncher launcher;
	
	@Resource(name = "taskletJobDemo")
	private Job job;
	
	@Test
	public void doTest() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		//自定义任务启动参数
		Map<String, JobParameter> params = new HashMap<String, JobParameter>();
		params.put("job_start_time", new JobParameter(System.currentTimeMillis()));
		launcher.run(job, new JobParameters(params));
	}

}
