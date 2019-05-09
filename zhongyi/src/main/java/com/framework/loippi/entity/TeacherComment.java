package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 机构评价
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TEACHER_COMMENT")
public class TeacherComment implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 星星 */
	@Column(name = "fav_star" )
	private Integer favStar;
	
	/** 评论时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 评论内容 */
	@Column(name = "content" )
	private String content;
	
	/** 标签 */
	@Column(name = "mark" )
	private String mark;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 教师id */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 教师名称 */
	@Column(name = "teacher_name" )
	private String teacherName;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 机构名称 */
	@Column(name = "org_name" )
	private String orgName;
	
	/** 状态    1——启用   2——禁用 */
	@Column(name = "status" )
	private Integer status;
	
	/** 评价账号 */
	@Column(name = "account" )
	private String account;
	/** 评价账号 */
	@Column(name = "comment_type" )
	private Integer commentType;
	
	
}
