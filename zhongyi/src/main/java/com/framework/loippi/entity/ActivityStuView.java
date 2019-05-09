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
@Table(name = "ACTIVITY_STU_VIEW")
public class ActivityStuView implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 账号 */
	@Column(name = "account" )
	private String account;
	
	/** 昵称 */
	@Column(name = "nick_name" )
	private String nickName;
	
	/** type=1课程id   type=2活动id */
	@Column(name = "target_id" )
	private Long targetId;
	
	/** 集体课，第几期 */
	@Column(name = "term" )
	private Integer term;
	
	/**  */
	@Column(name = "id" )
	private Long id;
	
	/** 学生id */
	@Column(name = "stu_id" )
	private Long stuId;
	
	/** 家长id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 联系电话 */
	@Column(name = "phone" )
	private String phone;
	
	/** 备注 */
	@Column(name = "remark" )
	private String remark;
	
	/**  */
	@Column(name = "stu_name" )
	private String stuName;
	
	/** 学生性别 */
	@Column(name = "stu_sex" )
	private String stuSex;
	
	/** 学生头像 */
	@Column(name = "stu_avatar" )
	private String stuAvatar;
	
	/** 学生生日 */
	@Column(name = "stu_birthday" )
	private java.util.Date stuBirthday;
	
	/** 创建时间 */
	@Column(name = "created_time" )
	private java.util.Date createdTime;
	
	/** 活动参赛号 */
	@Column(name = "activity_number" )
	private String activityNumber;
	
	/** 活动获奖情况 */
	@Column(name = "activity_prize" )
	private String activityPrize;
	
}
