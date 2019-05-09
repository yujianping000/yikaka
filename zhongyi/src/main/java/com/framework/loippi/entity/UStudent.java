package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 学生表
 * 
 * @author longbh
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "U_STUDENT")
public class UStudent implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 家长ID */
	@Column(name = "uid" )
	private Long uid;
	
	/** 性别 */
	@Column(name = "sex" )
	private String sex;
	
	/** 生日 */
	@Column(name = "birtchday" )
	private java.util.Date birtchday;
	
	/** 爱好 */
	@Column(name = "hobby" )
	private String hobby;
	
	/** 昵称 */
	@Column(name = "nickname" )
	private String nickname;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/**  */
	@Column(name = "create_time" )
	private Long createTime;
	
}
