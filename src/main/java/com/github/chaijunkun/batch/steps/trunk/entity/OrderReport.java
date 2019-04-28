package com.github.chaijunkun.batch.steps.trunk.entity;

/**
 * 虚构的订单实体
 * @author chaijunkun
 */
public class OrderReport {
	
	/** 订单id */
	private Long orderId;
	
	/** 订单类型描述 */
	private String orderTypeDesc;
	
	/** 经销商名称 */
	private String sellerName;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderReport [orderId=" + orderId + ", orderTypeDesc=" + orderTypeDesc + ", sellerName=" + sellerName
				+ "]";
	}

	/**
	 * 获取订单id
	 * @return 订单id
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单id
	 * @param orderId 订单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	

	/**
	 * 获取订单类型描述
	 * @return 订单类型描述
	 */
	public String getOrderTypeDesc() {
		return orderTypeDesc;
	}

	/**
	 * 设置订单类型描述
	 * @param orderTypeDesc 订单类型描述
	 */
	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}

	/**
	 * 获取经销商名称
	 * @return 经销商名称
	 */
	public String getSellerName() {
		return sellerName;
	}

	/**
	 * 设置经销商名称
	 * @param sellerName 经销商名称
	 */
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

}
