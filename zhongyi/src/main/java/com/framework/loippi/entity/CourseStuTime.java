package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 学生上课时间安排
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_STU_TIME")
public class CourseStuTime implements GenericEntity {

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
	
	/** 学生id */
	@Column(name = "student_id" )
	private Long studentId;
	
	/** 课程时间id */
	@Column(name = "course_time_id" )
	private Long courseTimeId;
	
	/** 订单id */
	@Column(name = "order_id" )
	private Long orderId;
	
	/** 1 表示未上课  2 表示已上了课 */
	@Column(name = "status" )
	private Integer status;
	
	/** 集体课，第几期 */
	@Column(name = "term" )
	private Integer term;
	@Column(name = "comment" )
	private Integer comment;
	@Column(name = "stucomment" )
	private Integer stucomment;
	
}
