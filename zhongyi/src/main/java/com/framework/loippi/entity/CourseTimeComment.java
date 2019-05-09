package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 课程教学时间评论
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_TIME_COMMENT")
public class CourseTimeComment implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 评论用户 */
	@Column(name = "uid" )
	private Long uid;
	
	/** 星星 */
	@Column(name = "fav_star" )
	private Integer favStar;
	
	/** 评论时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 评价内容 */
	@Column(name = "content" )
	private String content;
	
	/** 标签 */
	@Column(name = "mark" )
	private String mark;
	
	/** 课程id */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/**  */
	@Column(name = "teacher_avatar" )
	private String teacherAvatar;
	
	/**  */
	@Column(name = "teacher_name" )
	private String teacherName;
	
	/** 学生id */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 头像 */
	@Column(name = "student_id" )
	private Long studentId;
	
	/** 评论人头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 评论人 */
	@Column(name = "name" )
	private String name;
	
	/** 课程教学时间id */
	@Column(name = "time_id" )
	private Long timeId;
	
	/** 课程上课时间 */
	@Column(name = "course_time" )
	private java.util.Date courseTime;
	
	/** 上课时间1 */
	@Column(name = "course_time1" )
	private String courseTime1;
	
	/** 上课时间2 */
	@Column(name = "course_time2" )
	private String courseTime2;
	
	/** 上课时间-用于排序 */
	@Column(name = "course_time3" )
	private Long courseTime3;
	
	/** 未评论标签*/
	@Column(name = "nomark" )
	private String nomark;
	/** 数据类型：  1 学生对教师的评价   2教师对学生的评价*/
	@Column(name = "type" )
	private Integer type;
	
	/**用户账号*/
	@Column(name = "account" )
	private String account;
	
	/**用户账号*/
	@Column(name = "status" )
	private Integer status;
}
