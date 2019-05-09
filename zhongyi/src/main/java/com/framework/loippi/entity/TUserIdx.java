package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 用户索引表
 * 
 * @author 用户索引表
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_USER_IDX")
public class TUserIdx implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 昵称 */
	@Column(name = "name" )
	private String name;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 用户类型  1-老师 2-学生  3-机构  4-平台 */
	@Column(name = "type" )
	private Integer type;
	
	/** 登录账号 */
	@Column(name = "account" )
	private String account;
	
}
