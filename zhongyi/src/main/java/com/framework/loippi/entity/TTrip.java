package com.framework.loippi.entity;

import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 行程
 * 
 * @author longbh
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TRIP")
public class TTrip implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 行程时间 */
	//@Column(name = "trip_time" )
	//private Date tripTime;
	
	/** 提醒时间 */
	//@Column(name = "alarm_time" )
	//private Date alarmTime;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private Date createDate;
	
	/** 内容 */
	@Column(name = "content" )
	private String content;
	
	/** 图片 */
	@Column(name = "images" )
	private String images;
	
	/** 所属用户 */
	@Column(name = "uid" )
	private Long uid;
	
	/** 所属用户 */
	@Column(name = "account" )
	private String account;
	/** 所属用户 */
	@Column(name = "name" )
	private String name;
	
	
}
