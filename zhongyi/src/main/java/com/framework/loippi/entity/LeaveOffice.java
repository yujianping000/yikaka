package com.framework.loippi.entity;

import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 离职审核表
 * 
 * @author wmj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_LEAVE_OFFICE")
public class LeaveOffice implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 帐号 */
	@Column(name = "account" )
	private String account;
	
	/** 性别 */
	@Column(name = "sex" )
	private String sex;
	
	/** 教师名称 */
	@Column(name = "name" )
	private String name;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 教师类型 */
	@Column(name = "type" )
	private Integer type;
	
	/** 离职原因 */
	@Column(name = "reason" )
	private String reason;
	
	/** 班级情况 */
	@Column(name = "class_condition" )
	private String classCondition;
	
	/** 申请时间 */
	@Column(name = "apply_time" )
	private java.util.Date applyTime;
	
	/** 状态 1已同意 2已拒绝 3未审核 */
	@Column(name = "status" )
	private Integer status;
	
	/** 教师id */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 教师id */
	@Column(name = "leave_time" )
	private Date leaveTime;
	
	/** 教师id */
	@Column(name = "back_reason" )
	private String backReason;
	
	/** 教师id */
	@Column(name = "back_time" )
	private Date backTime;
	
	/** 教师id */
	@Column(name = "org_id" )
	private Long orgId;
	
}
