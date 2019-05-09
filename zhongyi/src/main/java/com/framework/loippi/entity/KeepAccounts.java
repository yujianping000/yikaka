package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 手机记账表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_KEEP_ACCOUNTS")
public class KeepAccounts implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 记账时间 */
	@Column(name = "tally_time" )
	private java.util.Date tallyTime;
	
	/** 数量 */
	@Column(name = "number" )
	private Integer number;
	
	/** 支付时间 */
	@Column(name = "pay_time" )
	private java.util.Date payTime;
	
	/** 单价 */
	@Column(name = "unit-price" )
	private java.math.BigDecimal unitPrice;
	
	/** 部门 */
	@Column(name = "department" )
	private String department;
	
	/** 金额 */
	@Column(name = "amount_money" )
	private java.math.BigDecimal amountMoney;
	
	/** 申请人 */
	@Column(name = "apply_user" )
	private String applyUser;
	
	/** 交易方式 */
	@Column(name = "deal_way" )
	private String dealWay;
	
	/** 项目 */
	@Column(name = "project" )
	private String project;
	
	/** 明细 */
	@Column(name = "detail" )
	private String detail;
	
	/** 备注 */
	@Column(name = "note" )
	private String note;
	
	/** 创建时间 */
	@Column(name = "crate_time" )
	private java.util.Date crateTime;
	
}
