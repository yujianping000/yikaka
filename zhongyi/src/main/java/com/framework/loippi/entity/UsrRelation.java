package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 用户关系：关注、好友、黑名单
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_USR_RELATION")
public class UsrRelation implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 编号 */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 用户编号 */
	@Column(name = "uid" )
	private Long uid;
	
	/** 目标用户编号 */
	@Column(name = "dest_uid" )
	private Long destUid;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 目标用户名称 */
	@Column(name = "dest_name" )
	private String destName;
	
	/** 目标用户头像 */
	@Column(name = "dest_avatar" )
	private String destAvatar;
	/** 目标用户头像 */
	@Column(name = "dest_first_name" )
	private String destFirstName;
	
}
