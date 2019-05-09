package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import com.framework.loippi.utils.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 工资记录表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SALARY_LOG")
public class TSalaryLog implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;
	
	private String createTimeString;
	
	private String payTimeString;
	
	private String statusString;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 底薪 */
	@Column(name = "basic_salary" )
	private java.math.BigDecimal basicSalary;
	
	/** 课时 */
	@Column(name = "course_salary" )
	private java.math.BigDecimal courseSalary;
	
	/** 绩效 */
	@Column(name = "performance_salary" )
	private java.math.BigDecimal performanceSalary;
	
	/** 全勤 */
	@Column(name = "all_tsutomu" )
	private java.math.BigDecimal allTsutomu;
	
	/** 奖金 */
	@Column(name = "bonus" )
	private java.math.BigDecimal bonus;
	
	/** 提成 */
	@Column(name = "push_money" )
	private java.math.BigDecimal pushMoney;
	
	/** 报销 */
	@Column(name = "reimburse" )
	private java.math.BigDecimal reimburse;
	
	/** 其他 */
	@Column(name = "other" )
	private java.math.BigDecimal other;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 发放时间 */
	@Column(name = "pay_time" )
	private java.util.Date payTime;
	
	/** 状态 1已发放 2没发放 */
	@Column(name = "status" )
	private Integer status;
	
	/** 帐号 */
	@Column(name = "account" )
	private String account;
	
	/** 姓名 */
	@Column(name = "user_name" )
	private String userName;
	
	/** 合计 */
	@Column(name = "total_salary" )
	private java.math.BigDecimal totalSalary;
	
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public String getStatusString() {
		if (status != null) {
			if (status == 1) {
				return "已发放";
			}
			if (status == 2) {
				return "没发放";
			}
		}
		return statusString;
	}

	public void setPayTimeString(String payTimeString) {
		this.payTimeString = payTimeString;
	}

	public String getPayTimeString() {
		if (payTime != null) {
			return StringUtil.date2String4(payTime);
		}
		return payTimeString;
	}
	
	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}
	
	public String getCreateTimeString() {
		if (createTime != null) {
			return StringUtil.date2String22(createTime);
		}
		return createTimeString;
	}
	
}
