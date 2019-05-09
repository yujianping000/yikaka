package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 任务表
 * 
 * @author wmj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TASK")
public class TTask implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 文字说明 */
	@Column(name = "content" )
	private String content;
	
	/** 成长值 */
	@Column(name = "value" )
	private Integer value;
	
	/**  */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
}
