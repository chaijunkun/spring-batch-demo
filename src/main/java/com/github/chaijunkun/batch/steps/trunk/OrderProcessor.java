package com.github.chaijunkun.batch.steps.trunk;

import org.springframework.batch.item.ItemProcessor;

import com.github.chaijunkun.batch.steps.trunk.entity.OrderEntity;
import com.github.chaijunkun.batch.steps.trunk.entity.OrderReport;
import com.github.chaijunkun.batch.steps.trunk.entity.OrderEntity.OrderType;

/**
 * 冯诺依曼结构
 * @author chaijunkun
 *
 * @param <A>
 */
public class OrderProcessor implements ItemProcessor<OrderEntity, OrderReport>{

	@Override
	public OrderReport process(OrderEntity input) throws Exception {
		OrderReport output = new OrderReport();
		//不变的字段照抄
		output.setOrderId(input.getOrderId());
		output.setSellerName(input.getSellerName());
		//转换订单类型 id->desc
		OrderType orderType = null;
		{
			Integer orderTypeId = input.getOrderType();
			if (null != orderTypeId){
				orderType = OrderType.getTypeById(orderTypeId);
			}
		}
		if (null != orderType){
			output.setOrderTypeDesc(orderType.getDesc());
		}
		return output;
	}

}
