package com.framework.loippi.entity;

public class WithdrawInfo {

	public java.math.BigDecimal getWithdrawTotalInfo() {
		return withdrawTotalInfo;
	}
	public void setWithdrawTotalInfo(java.math.BigDecimal withdrawTotalInfo) {
		this.withdrawTotalInfo = withdrawTotalInfo;
	}
	public java.math.BigDecimal getToAccountTotal() {
		return toAccountTotal;
	}
	public void setToAccountTotal(java.math.BigDecimal toAccountTotal) {
		this.toAccountTotal = toAccountTotal;
	}
	public java.math.BigDecimal getProcedureTotalInfo() {
		return procedureTotalInfo;
	}
	public void setProcedureTotalInfo(java.math.BigDecimal procedureTotalInfo) {
		this.procedureTotalInfo = procedureTotalInfo;
	}
	private java.math.BigDecimal toAccountTotal;
	private java.math.BigDecimal procedureTotalInfo;
	private java.math.BigDecimal withdrawTotalInfo;
}
