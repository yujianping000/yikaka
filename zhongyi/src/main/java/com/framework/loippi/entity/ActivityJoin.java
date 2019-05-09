package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 活动报名
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACTIVITY_JOIN")
public class ActivityJoin implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 家长id */
	@Column(name = "pid" )
	private Long pid;
	
	/** 孩子id */
	@Column(name = "cid" )
	private Long cid;
	
	/** 活动id */
	@Column(name = "aid" )
	private Long aid;
	
	/** 联系电话 */
	@Column(name = "phone" )
	private String phone;
	
	/** 备注 */
	@Column(name = "remark" )
	private String remark;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
}
