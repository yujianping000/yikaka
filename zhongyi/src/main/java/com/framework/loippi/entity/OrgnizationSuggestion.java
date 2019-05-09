package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 机构意见
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ORGNIZATION_SUGGESTION")
public class OrgnizationSuggestion implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 家长id */
	@Column(name = "parent_id" )
	private Long parentId;
	
	/** 姓名 */
	@Column(name = "name" )
	private String name;
	
	/** 手机号 */
	@Column(name = "phone" )
	private String phone;
	
	/** 意见 */
	@Column(name = "content" )
	private String content;
	
	/**  */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 家长昵称 */
	private String nickName;
	
}
