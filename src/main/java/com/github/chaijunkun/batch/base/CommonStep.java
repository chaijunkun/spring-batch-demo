package com.github.chaijunkun.batch.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;

/** 
 * 抽象公共步骤定义,常用方法集中在此
 * @author chaijunkun
 * @since 2014年11月28日 
 */
public abstract class CommonStep {

	private static final Logger log = LoggerFactory.getLogger(CommonStep.class);

	/**
	 * 设置将对象存入全局上下文
	 * @param chunkContext
	 * @param key
	 * @param value
	 */
	protected void set(ChunkContext chunkContext, String key, Object value){
		log.debug("set share data to execution context from chunk context, key:{}, value:{}", key, value);
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put(key, value);
	}

	/**
	 * 设置将对象存入全局上下文
	 * @param stepExecution
	 * @param key
	 * @param value
	 */
	protected void set(StepExecution stepExecution, String key, Object value){
		log.debug("set share data to execution context from step execution, key:{}, value:{}", key, value);
		stepExecution.getJobExecution().getExecutionContext().put(key, value);
	}

	/**
	 * 从全局上下文中取得对象
	 * @param chunkContext
	 * @param key
	 * @param clazz
	 * @return
	 */
	protected <T> T get(ChunkContext chunkContext, String key, Class<T> clazz){
		log.debug("get share data from execution context by chunk context, key:{}, class:{}", key, clazz);
		Object retVal = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get(key);
		if (retVal == null){
			return null;
		}else{
			return clazz.cast(retVal);
		}
	}

	/**
	 * 从全局上下文中取得对象
	 * @param stepExecution
	 * @param key
	 * @param clazz
	 * @return
	 */
	protected <T> T get(StepExecution stepExecution, String key, Class<T> clazz){
		log.debug("get share data from execution context by step execution, key:{}, class:{}", key, clazz);
		Object retVal = stepExecution.getJobExecution().getExecutionContext().get(key);
		if (retVal == null){
			return null;
		}else{
			return clazz.cast(retVal);
		}
	}


	/**
	 * 从全局上下文中删除对象
	 * @param chunkContext
	 * @param key
	 */
	protected void remove(ChunkContext chunkContext, String key){
		log.debug("remove share data from execution context by chunk context, key:{}", key);
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().remove(key);
	}

	/**
	 * 从全局上下文中删除对象
	 * @param stepExecution
	 * @param key
	 */
	protected void remove(StepExecution stepExecution, String key){
		log.debug("remove share data from execution context by step execution, key:{}", key);
		stepExecution.getJobExecution().getExecutionContext().remove(key);
	}

	/**
	 * 设置步骤退出代码
	 * @param chunkContext
	 * @param exitStatus
	 */
	protected void setExitStatus(ChunkContext chunkContext, ExitStatus exitStatus){
		log.debug("set exit status from chunk context:{}", exitStatus);
		chunkContext.getStepContext().getStepExecution().setExitStatus(exitStatus);
	}

}
