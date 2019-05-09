package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 点名
 * 
 * @author wmj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_CALL_ROLL")
public class CallRoll implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 教师id */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 家长id */
	@Column(name = "parent_id" )
	private Long parentId;
	
	/** 课程id */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 学生id */
	@Column(name = "stu_id" )
	private Long stuId;
	
	/** 学生名称 */
	@Column(name = "stu_name" )
	private String stuName;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 点名状态 1已到 2待确认 3未到 */
	@Column(name = "status" )
	private Integer status;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 课程时间id */
	@Column(name = "course_time_id" )
	private Long courseTimeId;
	
}
