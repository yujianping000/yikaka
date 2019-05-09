package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 工资明细
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_WAGES_LIST")
public class WagesList implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 对应工资id */
	@Column(name = "wid" )
	private Long wid;
	
	/** 工资 */
	@Column(name = "number" )
	private Double number;
	
	/**  */
	@Column(name = "create_time" )
	private Long createTime;
	
	/** 对应用户 */
	@Column(name = "uid" )
	private Long uid;
	
	/** 工资名称 */
	@Column(name = "name" )
	private String name;
	
}
