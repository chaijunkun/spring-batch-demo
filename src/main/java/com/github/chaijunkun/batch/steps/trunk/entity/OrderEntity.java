package com.github.chaijunkun.batch.steps.trunk.entity;

/**
 * 虚构的订单实体
 * @author chaijunkun
 */
public class OrderEntity {
	
	public static enum OrderType {
		
		TYPE_A(1, "type_a"),
		TYPE_B(2, "type_b");
		
		private int typeId;
		
		private String desc;
		
		private OrderType(int typeId, String desc){
			this.typeId = typeId;
			this.desc = desc;
		}
		
		public static OrderType getTypeById(int typeId) {
			OrderType[] values = OrderType.values();
			for (OrderType value : values) {
				if (value.getTypeId() == typeId){
					return value;
				}
			}
			return null;
		}

		public int getTypeId() {
			return typeId;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	/** 订单id */
	private Long orderId;
	
	/** 订单类型 */
	private Integer orderType;
	
	/** 经销商名称 */
	private String sellerName;
	
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
	 * 获取订单类型
	 * @return 订单类型
	 */
	public Integer getOrderType() {
		return orderType;
	}

	/**
	 * 设置订单类型
	 * @param orderType 订单类型
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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
