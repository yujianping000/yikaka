package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - user
 * 
 * @author huangqi
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_FRIENDS")
public class Friends implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/**  */
	@Column(name = "uid" )
	private Long uid;
	
	/** 好友id */
	@Column(name = "dest_uid" )
	private Long destUid;
	
	/** 状态，0，申请，1，好友 */
	@Column(name = "STATUS" )
	private Integer status;
	
	/** 是否主动申请 */
	@Column(name = "is_apply" )
	private Integer isApply;
	
	/**  */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 是否清空 */
	@Column(name = "is_clear" )
	private Integer isClear;
	
}
