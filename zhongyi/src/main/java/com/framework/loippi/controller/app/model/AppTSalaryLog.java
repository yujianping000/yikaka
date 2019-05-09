package com.framework.loippi.controller.app.model;



public class AppTSalaryLog {
	public java.math.BigDecimal getCourseSalary() {
		return courseSalary;
	}

	public void setCourseSalary(java.math.BigDecimal courseSalary) {
		this.courseSalary = courseSalary;
	}

	public java.math.BigDecimal getPerformanceSalary() {
		return performanceSalary;
	}

	public void setPerformanceSalary(java.math.BigDecimal performanceSalary) {
		this.performanceSalary = performanceSalary;
	}

	public java.math.BigDecimal getAllTsutomu() {
		return allTsutomu;
	}

	public void setAllTsutomu(java.math.BigDecimal allTsutomu) {
		this.allTsutomu = allTsutomu;
	}

	public java.math.BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(java.math.BigDecimal bonus) {
		this.bonus = bonus;
	}

	public java.math.BigDecimal getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(java.math.BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
	}

	public java.math.BigDecimal getReimburse() {
		return reimburse;
	}

	public void setReimburse(java.math.BigDecimal reimburse) {
		this.reimburse = reimburse;
	}

	public java.math.BigDecimal getOther() {
		return other;
	}

	public void setOther(java.math.BigDecimal other) {
		this.other = other;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public java.math.BigDecimal getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(java.math.BigDecimal totalSalary) {
		this.totalSalary = totalSalary;
	}

	/** 课时 */
	private java.math.BigDecimal courseSalary;
	
	/** 绩效 */
	private java.math.BigDecimal performanceSalary;
	
	/** 全勤 */
	private java.math.BigDecimal allTsutomu;
	
	/** 奖金 */
	private java.math.BigDecimal bonus;
	
	/** 提成 */
	private java.math.BigDecimal pushMoney;
	
	/** 报销 */
	private java.math.BigDecimal reimburse;
	
	/** 其他 */
	private java.math.BigDecimal other;
	
	private String issueTime; 
	
	private java.math.BigDecimal totalSalary;
	
}
