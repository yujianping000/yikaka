package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 机构用户
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ORG_USER")
public class OrgUser implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 用户类型   1—家长   2—教师 */
	@Column(name = "type" )
	private Integer type;
	
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
}
