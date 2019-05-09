package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 课时费用
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_THOUR_EXPENSE")
public class ThourExpense implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 课时 */
	@Column(name = "hour" )
	private Integer hour;
	
	/** 奖励 */
	@Column(name = "award" )
	private java.math.BigDecimal award;
	
	/** 扣除 */
	@Column(name = "deduct" )
	private java.math.BigDecimal deduct;
	
	/** 上一节课 */
	@Column(name = "one_class" )
	private java.math.BigDecimal oneClass;
	
	/** 金额比率 */
	@Column(name = "money_rate" )
	private java.math.BigDecimal moneyRate;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 用户id */
	@Column(name = "org_id" )
	private Long orgId;
	
	
}
