package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 补课申请
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_MAKEUP")
public class CourseMakeup implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 学生id */
	@Column(name = "student_id" )
	private Long studentId;
	
	/** 家长id */
	@Column(name = "parent_id" )
	private Long parentId;
	
	/** 课程id */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 联系电话 */
	@Column(name = "phone" )
	private String phone;
	
	/** 姓名 */
	@Column(name = "name" )
	private String name;
	
	/** 原因 */
	@Column(name = "content" )
	private String content;
	
	/**  */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 账号 */
	@Column(name = "account" )
	private String account;
	
	/** 学生姓名 */
	@Column(name = "student_name" )
	private String studentName;
	
	/** 学生性别 */
	@Column(name = "sex" )
	private String sex;
	
	/** 学生生日 */
	@Column(name = "birtchday" )
	private java.util.Date birtchday;
	
	/** 课程名称 */
	@Column(name = "course_name" )
	private String courseName;
	
	/** 备注 */
	@Column(name = "remark" )
	private String remark;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
}
