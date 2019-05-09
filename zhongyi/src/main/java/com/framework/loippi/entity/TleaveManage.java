package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 请假管理
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TLEAVE_MANAGE")
public class TleaveManage implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 请假类型   1-事假   2-病假   3-年假  4-调休  5-婚假  6-产假  7-陪产假  8-丧假  9-其他 */
	@Column(name = "type" )
	private Integer type;
	
	/** 限制天数 */
	@Column(name = "limit_day" )
	private Integer limitDay;
	
	/** 超过天数扣除金额 */
	@Column(name = "deduct_account" )
	private java.math.BigDecimal deductAccount;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	
}
