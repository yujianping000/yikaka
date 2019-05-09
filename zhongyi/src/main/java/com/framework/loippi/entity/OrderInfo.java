package com.framework.loippi.entity;

public class OrderInfo {
   	public java.math.BigDecimal getTotalFeeInfo() {
		return totalFeeInfo;
	}
	public void setTotalFeeInfo(java.math.BigDecimal totalFeeInfo) {
		this.totalFeeInfo = totalFeeInfo;
	}
	public java.math.BigDecimal getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(java.math.BigDecimal paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public java.math.BigDecimal getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(java.math.BigDecimal ordernum) {
		this.ordernum = ordernum;
	}
	public java.math.BigDecimal getProcedureCostInfo() {
		return procedureCostInfo;
	}
	public void setProcedureCostInfo(java.math.BigDecimal procedureCostInfo) {
		this.procedureCostInfo = procedureCostInfo;
	}
	private java.math.BigDecimal totalFeeInfo;
	private java.math.BigDecimal paymentInfo;
	private java.math.BigDecimal ordernum;
	private java.math.BigDecimal procedureCostInfo;
}
