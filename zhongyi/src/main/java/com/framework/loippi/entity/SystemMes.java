package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 系统消息
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYSTEM_MES")
public class SystemMes implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 主键 */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 图片 */
	@Column(name = "image" )
	private String image;
	
	/** 标题 */
	@Column(name = "title" )
	private String title;
	
	/** 内容 */
	@Column(name = "content" )
	private String content;
	
	/** 用户id */
	@Column(name = "user_id" )
	private Long userId;
	
	/** 用户名 */
	@Column(name = "user_name" )
	private String userName;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 消息类型    1电子围栏、2SOS求救、3设备定位、4续费消息、5系统推送*/
	@Column(name = "type" )
	private Integer type;
	
	/** 已读状态 1未读 2已读  3未发送 */
	@Column(name = "status" )
	private Integer status;
	
	/** 消息id */
	@Column(name = "message_id" )
	private Long messageId;
	//接收发送人数
	@Column(name = "num" )
	private Integer num;
	
}
