package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 机构教师
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORG_TEACHERVIEW")
public class OrgTeacherview implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 账号 */
	@Column(name = "account" )
	private String account;
	
	/** 昵称 */
	@Column(name = "nickname" )
	private String nickname;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 性别 */
	@Column(name = "sex" )
	private String sex;
	
	/** 教师类型   1—全职教师  2—兼职教师 */
	@Column(name = "teacher_type" )
	private Integer teacherType;
	
	/** 加入机构时间 */
	@Column(name = "join_time" )
	private java.util.Date joinTime;
	
	/** 老师的star */
	@Column(name = "fav_star" )
	private Integer favStar;
	
	/** 1-正常   2-锁定 */
	@Column(name = "status" )
	private Integer status;
	
	/**  */
	@Column(name = "org_id" )
	private Long orgId;
	
	/**用户分数统计*/
	private Integer userRate;
	
}
