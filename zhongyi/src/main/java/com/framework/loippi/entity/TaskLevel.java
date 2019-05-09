package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 任务等级
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TASK_LEVEL")
public class TaskLevel implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 等级 */
	@Column(name = "level" )
	private Integer level;
	
	/** 等级积分值 */
	@Column(name = "level_value" )
	private Integer levelValue;
	
	/** 图片1 */
	@Column(name = "level_image1" )
	private String levelImage1;
	
	/** 图片2 */
	@Column(name = "level_image2" )
	private String levelImage2;
	
	/** 图片3 */
	@Column(name = "level_image3" )
	private String levelImage3;
	
}
