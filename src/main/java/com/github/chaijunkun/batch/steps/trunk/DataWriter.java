package com.github.chaijunkun.batch.steps.trunk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

import com.github.chaijunkun.batch.steps.trunk.entity.OrderReport;

public class DataWriter implements ItemStreamWriter<OrderReport> {
	
	private static final Logger log = LoggerFactory.getLogger(DataWriter.class);
	
	private static final String res_odd_order_out = "odd_order.txt";
	
	private static final String res_even_order_out = "even_order.txt";
	
	private BufferedWriter oddOrderWriter;
	
	private BufferedWriter evenOrderWriter;

	@Override
	public void write(List<? extends OrderReport> reports) throws Exception {
		try{
			for (OrderReport report : reports) {
				if ((report.getOrderId() % 2)!=0){
					oddOrderWriter.write(report.toString());
					oddOrderWriter.newLine();
				}else{
					evenOrderWriter.write(report.toString());
					evenOrderWriter.newLine();
				}
			}
		}finally{
			oddOrderWriter.flush();
			evenOrderWriter.flush();
		}
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		try {
			oddOrderWriter = new BufferedWriter(new FileWriter(new File(res_odd_order_out), true));
			evenOrderWriter = new BufferedWriter(new FileWriter(new File(res_even_order_out), true));
		} catch (IOException e) {
			log.error("打开输出流出错", e);
			throw new ItemStreamException(e);
		}
	}

	/**
	 * 该方法在writer执行前调用一次
	 * 每达到一次commit-interval后调用一次
	 * 所有数据都执行完再调一次
	 * 可以debug一下executionContext中的变量
	 */
	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		log.info("调用了writer的update方法");
	}

	@Override
	public void close() throws ItemStreamException {
		IOUtils.closeQuietly(evenOrderWriter);
		IOUtils.closeQuietly(oddOrderWriter);
	}

}
