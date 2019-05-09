package com.framework.loippi.entity;

import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 签到
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SIGN")
public class TSign implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 地址 */
	@Column(name = "location" )
	private String location;
	
	/** 签到时间 */
	@Column(name = "mark_time" )
	private Date markTime;
	
	/** 签到时间 */
	@Column(name = "mark_time1" )
	private Long markTime1;
	
	/** 组织id */
	@Column(name = "org_id" )
	private Long orgId;
	/** 组织id */
	@Column(name = "hour_id" )
	private Long hourId;
	
}
