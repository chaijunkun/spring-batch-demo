package com.github.chaijunkun.batch.steps.trunk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.github.chaijunkun.batch.steps.trunk.entity.OrderEntity;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

public class DataReader extends AbstractItemCountingItemStreamItemReader<OrderEntity>{

	private static final Logger log = LoggerFactory.getLogger(DataReader.class);
	
	/** 数据资源 */
	private static final String RES_DATA = "/data/export.txt";
	
	/** 必须给reader起个名字 */
	private static final String READER_NAME = "dataReader";
	
	private BufferedReader fileReader;
	
	public DataReader() {
		this.setName(READER_NAME);
	}
	
	@Override
	protected void doClose() throws Exception {
		IOUtils.closeQuietly(fileReader);
	}

	@Override
	protected void doOpen() throws Exception {
		fileReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(RES_DATA)));
	}

	/**
	 * 读取行,转换为对象
	 * 返回为null时自动停止
	 */
	@Override
	protected OrderEntity doRead() throws Exception {
		String line = fileReader.readLine();
		if (StringUtils.isEmpty(line)){
			return null;
		}
		log.info("读取到的内容:{}", line);
		String[] fields = line.split("\\t");
		OrderEntity order = new OrderEntity();
		order.setOrderId(Long.parseLong(fields[0]));
		order.setOrderType(Integer.parseInt(fields[1]));
		order.setSellerName(fields[2]);
		return order;
	}

}
