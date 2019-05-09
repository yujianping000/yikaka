package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 工资表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_WAGES")
public class TWages implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 月份 */
	@Column(name = "month" )
	private String month;
	
	/** 工资 */
	@Column(name = "number" )
	private Double number;
	
	/**  */
	@Column(name = "create_date" )
	private Long createDate;
	
	/** 对应用户 */
	@Column(name = "uid" )
	private Long uid;
	
	/** 组织id */
	@Column(name = "org_id" )
	private Integer orgId;
	
}
