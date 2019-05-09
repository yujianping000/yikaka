package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 扫码机构列表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ORGNIZATION_SCANLOG")
public class OrgnizationScanlog implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 用户id */
	@Column(name = "user_id" )
	private Long userId;
	
	/**  */
	@Column(name = "update_date" )
	private java.util.Date updateDate;
	
	/**  */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
}
