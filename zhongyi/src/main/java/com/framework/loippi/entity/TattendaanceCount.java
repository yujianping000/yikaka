package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 考勤管理
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TATTENDAANCE_COUNT")
public class TattendaanceCount implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "id" )
	private Long id;
	
	/**  */
	@Column(name = "attendance_count" )
	private String attendanceCount;
	
	/** 提前分钟 */
	@Column(name = "advance_minute" )
	private Integer advanceMinute;
	
	/** 迟到分钟 */
	@Column(name = "late_minute1" )
	private Integer lateMinute1;
	
	/** 迟到扣除1 */
	@Column(name = "deduct1" )
	private java.math.BigDecimal deduct1;
	
	/** 封顶 */
	@Column(name = "max_deduct1" )
	private java.math.BigDecimal maxDeduct1;
	
	/** 迟到分钟 */
	@Column(name = "late_minute2" )
	private Integer lateMinute2;
	
	/** 迟到扣除2 */
	@Column(name = "deduct2" )
	private java.math.BigDecimal deduct2;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 用户id */
	@Column(name = "org_id" )
	private Long orgId;
	
}
