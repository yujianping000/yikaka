package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 教室
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_ROOM")
public class CourseRoom implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 教师名称 */
	@Column(name = "name" )
	private String name;
	
	/** 类型 1、一对一   2、集体课 */
	@Column(name = "type" )
	private Integer type;
	
	/** 容量人数 */
	@Column(name = "peoples" )
	private Integer peoples;
	
	/** 面积 */
	@Column(name = "area" )
	private Double area;
	
	/** 备注 */
	@Column(name = "remark" )
	private String remark;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
}
