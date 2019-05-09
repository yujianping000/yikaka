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
@Table(name = "T_ORG_INVITE")
public class OrgInvite implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 教师id */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 邀请教师类型  1—全职教师  2—兼职教师 */
	@Column(name = "type" )
	private Integer type;
	
	/** 邀请原因 */
	@Column(name = "reason" )
	private String reason;
	
	/** 邀请状态   1——未邀请    2——邀请 */
	@Column(name = "inviteType" )
	private Integer invitetype;
	
}
