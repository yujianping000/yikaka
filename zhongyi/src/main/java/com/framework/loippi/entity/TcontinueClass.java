package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 续班表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TCONTINUE_CLASS")
public class TcontinueClass implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 最小值 */
	@Column(name = "mix" )
	private java.math.BigDecimal mix;
	
	/** 最大值 */
	@Column(name = "max" )
	private java.math.BigDecimal max;
	
	/** 金额 */
	@Column(name = "account" )
	private java.math.BigDecimal account;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 用户id */
	@Column(name = "org_id" )
	private Long orgId;
	
	
}
