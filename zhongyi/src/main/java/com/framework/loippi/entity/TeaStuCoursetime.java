package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - VIEW
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VIEW_TEA_STU_COURSETIME")
public class TeaStuCoursetime implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 姓名 */
	@Column(name = "nickname" )
	private String nickname;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 课程id */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 上课时间-用于排序 */
	@Column(name = "course_time3" )
	private Long courseTime3;
	
	/** 1 教师已评价 */
	@Column(name = "comment" )
	private Integer comment;
	
	@Column(name = "student_id" )
	private Long studentId;
	
	@Column(name = "status" )
	private Integer status;
	/** 家长id */
	@Column(name = "uid" )
	private Long uid;
}
