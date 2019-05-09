package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 任务用户关联
 * 
 * @author wmj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TASK_USER")
public class TaskUser implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 用户id */
	@Column(name = "user_id" )
	private Long userId;
	
	/** 任务id */
	@Column(name = "task_id" )
	private Long taskId;
	
	/** 状态 1领取 0未领取 */
	@Column(name = "status" )
	private Integer status;
	
	/**  */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 成长值 */
	@Column(name = "value" )
	private Integer value;
	
}
