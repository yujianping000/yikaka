package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 银行
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_BANK")
public class TBank implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 银行名称 */
	@Column(name = "bank_name" )
	private String bankName;
	
	/** 银行卡号 */
	@Column(name = "bank_account" )
	private String bankAccount;
	
	/** 持卡人 */
	@Column(name = "bank_user_name" )
	private String bankUserName;
	
	@Column(name = "org_id" )
	private Long orgId;
	
	private String number;
	
}
