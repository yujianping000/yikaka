package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 上课时间安排
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_TIME")
public class CourseTime implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 课程上课时间 */
	@Column(name = "course_time" )
	private java.util.Date courseTime;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 课程id */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 上课时间1 */
	@Column(name = "course_time1" )
	private String courseTime1;
	
	/** 上课时间2 */
	@Column(name = "course_time2" )
	private String courseTime2;
	
	/** 上课时间-用于排序 */
	@Column(name = "course_time3" )
	private Long courseTime3;
	/** 下课时间 */
	@Column(name = "course_time4" )
	private Long courseTime4;
	/** 学生id ，用于一对一 */
	@Column(name = "student_id" )
	private Long studentId;
	
	
	/** 集体课，第几期 */
	@Column(name = "term" )
	private Integer term;
	
}
