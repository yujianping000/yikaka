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
@Table(name = "HOUR_VIEW")
public class HourView implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "hour_id" )
	private Long hourId;
	
	/**  */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/**  */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 名称 */
	@Column(name = "name" )
	private String name;
	
	/** 签到时间 */
	@Column(name = "mark_time" )
	private java.util.Date markTime;
	
	/** 上课时间 */
	@Column(name = "coursetime" )
	private String coursetime;
	
	/** 昵称 */
	@Column(name = "nickname" )
	private String nickname;
	
	/** 上课状态 1已完成 2旷工 3迟到 4请假 */
	@Column(name = "class_status" )
	private Integer classStatus;
	
}
