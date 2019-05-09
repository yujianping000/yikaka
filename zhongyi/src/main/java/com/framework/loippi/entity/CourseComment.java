package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 课程评论
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_COMMENT")
public class CourseComment implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 评论用户 */
	@Column(name = "uid" )
	private Long uid;
	
	/** 星星 */
	@Column(name = "fav_star" )
	private Integer favStar;
	
	/** 评论时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/**  */
	@Column(name = "content" )
	private String content;
	
	/**  */
	@Column(name = "mark" )
	private String mark;
	
	/**  */
	@Column(name = "course_id" )
	private Long courseId;
	
	/**  */
	@Column(name = "org_id" )
	private Long orgId;
	
	/**  */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 评论人 */
	@Column(name = "name" )
	private String name;
	
	/** 订单id */
	@Column(name = "order_id" )
	private Long orderId;
	
	/** 状态    1——启用   2——禁用  */
	@Column(name = "status" )
	private Integer status;
	
	/** 评价账号 */
	@Column(name = "account" )
	private String account;
	
}
