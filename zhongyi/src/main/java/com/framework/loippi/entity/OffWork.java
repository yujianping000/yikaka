package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 请假
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_OFF_WORK")
public class OffWork implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 老师id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 请假类型   1-事假   2-病假   3-年假  4-调休  5-婚假  6-产假  7-陪产假  8-丧假  9-其他 */
	@Column(name = "type" )
	private String type;
	
	/**  */
	@Column(name = "image" )
	private String image;
	
	/** 描述 */
	@Column(name = "description" )
	private String description;
	
	/** 课程id */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 课时 */
	@Column(name = "hour" )
	private String hour;
	
	/** 开始时间 */
	@Column(name = "start" )
	private java.util.Date start;
	
	/** 结束时间 */
	@Column(name = "end" )
	private java.util.Date end;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 请假原因 */
	@Column(name = "reason" )
	private String reason;
	
	/** 状态 1同意 0拒绝 */
	@Column(name = "status" )
	private Integer status;
	
	/** 请假天数 */
	@Column(name = "day_num" )
	private Integer dayNum;
	
	/** 组织id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 用户类型 */
	@Column(name = "user_type" )
	private Integer userType;
	
	/** 用户类型 */
	@Column(name = "account" )
	private String account;
	/** 用户类型 */
	@Column(name = "nick_name" )
	private String nickName;
	
	
	/** 学生id */
	@Column(name = "stuid" )
	private Long stuid;
	
	/** 请假时间 */
	@Column(name = "hourtime" )
	private String hourtime;
	/** 学生姓名 */
	@Column(name = "stuname" )
	private String stuname;
	
	/** 学生头像 */
	@Column(name = "stuavatar" )
	private String stuavatar;
	
}
