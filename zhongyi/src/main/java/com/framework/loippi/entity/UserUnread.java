package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 用户未读
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_USER_UNREAD")
public class UserUnread implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 未完成任务数 */
	@Column(name = "task_total" )
	private Integer taskTotal;
	
	/** 机构动态状态 1已读 0未读 */
	@Column(name = "dynamic_status" )
	private Integer dynamicStatus;
	
	/** 系统消息未读状态 1已读 0未读 */
	@Column(name = "system_mes_status" )
	private Integer systemMesStatus;
	
	/** 课程消息未读状态 1已读 0未读 */
	@Column(name = "courser_mes_status" )
	private Integer courserMesStatus;
	
	/** 上课提醒状态 1已读 0未读 */
	@Column(name = "class_remind_status" )
	private Integer classRemindStatus;
	
	/**  */
	@Column(name = "user_id" )
	private Long userId;
	
	
	/**  */
	@Column(name = "friend_status" )
	private Integer friendStatus;
	
	
	/** 用户类型 1家长 2老师 */
	@Column(name = "user_type" )
	private Integer userType;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
}
