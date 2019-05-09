package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 请假说明
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TLEAVE_EXPLAIN")
public class TleaveExplain implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 事假天数 */
	@Column(name = "day1" )
	private Integer day1;
	
	/** 事假扣除金额 */
	@Column(name = "deduct1" )
	private java.math.BigDecimal deduct1;
	
	/** 事假天数 */
	@Column(name = "day2" )
	private Integer day2;
	
	/** 事假扣除金额 */
	@Column(name = "deduct2" )
	private java.math.BigDecimal deduct2;
	
	/** 事假天数 */
	@Column(name = "day3" )
	private Integer day3;
	
	/** 事假扣除金额 */
	@Column(name = "deduct3" )
	private java.math.BigDecimal deduct3;
	
	/** 事假天数 */
	@Column(name = "day4" )
	private Integer day4;
	
	/** 事假扣除金额 */
	@Column(name = "deduct4" )
	private java.math.BigDecimal deduct4;
	
	/** 事假天数 */
	@Column(name = "day5" )
	private Integer day5;
	
	/** 事假扣除金额 */
	@Column(name = "deduct5" )
	private java.math.BigDecimal deduct5;
	
	/** 事假天数 */
	@Column(name = "day6" )
	private Integer day6;
	
	/** 事假扣除金额 */
	@Column(name = "deduct6" )
	private java.math.BigDecimal deduct6;
	
	/** 事假天数 */
	@Column(name = "day7" )
	private Integer day7;
	
	/** 事假扣除金额 */
	@Column(name = "deduct7" )
	private java.math.BigDecimal deduct7;
	
	/** 事假天数 */
	@Column(name = "day8" )
	private Integer day8;
	
	/** 事假扣除金额 */
	@Column(name = "deduct8" )
	private java.math.BigDecimal deduct8;
	
	/** 事假天数 */
	@Column(name = "day9" )
	private Integer day9;
	
	/** 事假扣除金额 */
	@Column(name = "deduct9" )
	private java.math.BigDecimal deduct9;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	/** 用户id */
	@Column(name = "org_id" )
	private Long orgId;
	
	
}
