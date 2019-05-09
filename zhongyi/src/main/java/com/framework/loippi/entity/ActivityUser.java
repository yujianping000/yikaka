package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 用户活动列表
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACTIVITY_USER")
public class ActivityUser implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 活动id */
	@Column(name = "aid" )
	private Long aid;
	
	/** 参加用户 */
	@Column(name = "uid" )
	private Long uid;
	
	/** 参加时间 */
	@Column(name = "create_date" )
	private String createDate;
	
	/** 联系电话 */
	@Column(name = "tel_phone" )
	private String telPhone;
	
	/** 备注 */
	@Column(name = "remark" )
	private String remark;
	
	/** 参加活动编号 */
	@Column(name = "ua_no" )
	private String uaNo;
	
	/** 对应小孩的id */
	@Column(name = "stu_id" )
	private Long stuId;
	
	/** 参加状态 */
	@Column(name = "status" )
	private Integer status;
	
}
