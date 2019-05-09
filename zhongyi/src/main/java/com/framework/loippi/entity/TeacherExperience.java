package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 任教经历
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TEACHER_EXPERIENCE")
public class TeacherExperience implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 开始时间 */
	@Column(name = "start" )
	private java.util.Date start;
	
	/** 结束时间 */
	@Column(name = "end" )
	private java.util.Date end;
	
	/**  */
	@Column(name = "content" )
	private String content;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/**  */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	
}
