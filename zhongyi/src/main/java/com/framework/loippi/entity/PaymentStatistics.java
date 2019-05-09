package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 支出统计
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_PAYMENT_STATISTICS")
public class PaymentStatistics implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 系统工资 */
	@Column(name = "online_salary" )
	private java.math.BigDecimal onlineSalary;
	
	/** 线上提现 */
	@Column(name = "online_withdraw" )
	private java.math.BigDecimal onlineWithdraw;
	
	/** 手动记账 */
	@Column(name = "offline_account" )
	private java.math.BigDecimal offlineAccount;
	
	/**  */
	@Column(name = "org_id" )
	private Long orgId;
	
	/**  */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
}
