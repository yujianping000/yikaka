package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 教师上课时间安排
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_TEA_TIME")
public class CourseTeaTime implements GenericEntity {

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
	
	/** 课程时间id */
	@Column(name = "course_time_id" )
	private Long courseTimeId;
	
	/** 1 表示未上课  2 表示已上了课 */
	@Column(name = "status" )
	private Integer status;
	
	/** 教师id */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 集体课，第几期 */
	@Column(name = "term" )
	private Integer term;
	
	/** 是否调课   1——未调课    2——调课 */
	@Column(name = "type" )
	private Integer type;
	
	/** 调课原因 */
	@Column(name = "reason" )
	private String reason;
	
	/** 原课程上课时间 */
	@Column(name = "old_time" )
	private java.util.Date oldTime;
	
	/** 原课程上课开始时间 */
	@Column(name = "old_time1" )
	private String oldTime1;
	
	/** 原课程上课结束时间 */
	@Column(name = "old_time2" )
	private String oldTime2;
	
	/** 上课状态*/
	@Column(name = "class_status" )
	private Integer classStatus;
	
	/** 开课时间前分钟数*/
	@Column(name = "course_time_less" )
	private Long courseTimeLess;
	
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 原始上课日期*/
	@Column(name = "c_time" )
	private java.util.Date cTime;
	
	/** 1 已经课程总结*/
	@Column(name = "summary" )
	private Integer summary;
	
	/** 1 已经完成评价*/
	@Column(name = "comment" )
	private Integer comment;
	
	/** 1 表示教师已按点名按钮*/
	@Column(name = "callroll" )
	private Integer callroll;
	

	
}
