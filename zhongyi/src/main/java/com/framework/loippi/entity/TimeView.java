package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 机构课时
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COURSE_TIME_VIEW")
public class TimeView implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "course_time_id" )
	private Long courseTimeId;
	
	/**  */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 名称 */
	@Column(name = "course_name" )
	private String courseName;
	
	/** 昵称 */
	@Column(name = "teacher_name" )
	private String teacherName;
	
	/** 代课老师姓名 */
	@Column(name = "substitute_name" )
	private String substituteName;
	
	/** 是否代课  1——不代课   2——代课 */
	@Column(name = "substitute_type" )
	private Integer substituteType;
	
	/** 课程上课时间（调课时为调课时间） */
	@Column(name = "course_time" )
	private java.util.Date courseTime;
	
	/** 上课时间1 */
	@Column(name = "course_time1" )
	private String courseTime1;
	
	/** 上课时间2 */
	@Column(name = "course_time2" )
	private String courseTime2;
	
	/** 是否调课   1——未调课    2——调课 */
	@Column(name = "changeTime_type" )
	private Integer changetimeType;
	
	/** 原课程上课时间（调课时存放原来上课时间） */
	@Column(name = "old_time" )
	private java.util.Date oldTime;
	
	/** 原课程上课开始时间 */
	@Column(name = "old_time1" )
	private String oldTime1;
	
	/** 原课程上课结束时间 */
	@Column(name = "old_time2" )
	private String oldTime2;
	
	/** 原上课当天日期 */
	@Column(name = "c_time" )
	private java.util.Date cTime;
	
	/**  */
	@Column(name = "org_id" )
	private Long orgId;
	
}
