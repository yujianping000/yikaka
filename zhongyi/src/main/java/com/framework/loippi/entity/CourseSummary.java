package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 教师课程总结
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_SUMMARY")
public class CourseSummary implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** ID */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 教师上课时间id */
	@Column(name = "course_tea_time_id" )
	private Long courseTeaTimeId;
	
	/** 总结图片 */
	@Column(name = "image" )
	private String image;
	
	/** 教师id */
	@Column(name = "tea_id" )
	private Long teaId;
	
	/** 总结内容 */
	@Column(name = "content" )
	private String content;
	
	/** 教师姓名 */
	@Column(name = "tea_name" )
	private String teaName;
	@Column(name = "avatar" )
	private String avatar;
}
