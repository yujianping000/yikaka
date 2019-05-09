package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - VIEW
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WITHDRAW_VIEW")
public class WithdrawView implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 机构账号 */
	@Column(name = "account" )
	private String account;
	
	/**  */
	@Column(name = "name" )
	private String name;
	
	/** 联系人名称 */
	@Column(name = "service_name" )
	private String serviceName;
	
	/** 联系人电话 */
	@Column(name = "service_phone" )
	private String servicePhone;
	
	/** 手续费 */
	@Column(name = "fee" )
	private java.math.BigDecimal fee;
	
	/**  */
	@Column(name = "withdraw_total" )
	private String withdrawTotal;
	/**  */
	@Column(name = "totalday" )
	private  String totalday;
	
	
}
