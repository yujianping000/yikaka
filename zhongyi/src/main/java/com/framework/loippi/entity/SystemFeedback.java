package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 意见反馈
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYSTEM_FEEDBACK")
public class SystemFeedback implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 编号 */
	@Column(id = true, name = "ID", updatable = false)
	private Long id;
	
	/** 创建时间 */
	@Column(name = "CREATE_DATE" )
	private java.util.Date createDate;
	
	/** 手机 */
	@Column(name = "MOBILE" )
	private String mobile;
	
	/** 内容 */
	@Column(name = "CONTENT" )
	private String content;
	
	/** IP */
	@Column(name = "IP" )
	private String ip;
	
	/** 1.家长 2. 教师 */
	@Column(name = "TYPE" )
	private Integer type;
	
	/** 用户id */
	@Column(name = "UID" )
	private Long uid;
	
	/** 昵称 */
	@Column(name = "NICKNAME" )
	private String nickname;
	
}
