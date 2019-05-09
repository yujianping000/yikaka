package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 机构邀请
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORG_TEACHER_TALENT_VIEW")
public class TeacherTalentView implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 账号 */
	@Column(name = "account" )
	private String account;
	
	/** 昵称 */
	@Column(name = "nickname" )
	private String nickname;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 性别 */
	@Column(name = "sex" )
	private String sex;
	
	/** 生日 */
	@Column(name = "birthday" )
	private java.util.Date birthday;
	
	/** 简介 */
	@Column(name = "description" )
	private String description;
	
	/** 邀请状态   1——未邀请    2——邀请 */
	@Column(name = "inviteType" )
	private Integer invitetype;
	
	/** 邀请教师类型  1—全职教师  2—兼职教师 */
	@Column(name = "type" )
	private Integer type;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/**是否已经加入该机构  已加入状态设为1*/
	private Integer joinType;
	
}
