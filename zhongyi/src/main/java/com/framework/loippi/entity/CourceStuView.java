package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 课程查询学员
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COURSE_STU_VIEW")
public class CourceStuView implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 账号 */
	@Column(name = "account" )
	private String account;
	
	/** 昵称 */
	@Column(name = "nick_name" )
	private String nickName;
	
	/** 课程id */
	@Column(name = "target_id" )
	private Long targetId;
	
	/** 第几期 */
	@Column(name = "term" )
	private Integer term;
	
	/** 订单id */
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
	
	/** 孩子姓名 */
	@Column(name = "stu_name" )
	private String stuName;
	
	/** 性别 */
	@Column(name = "stu_sex" )
	private String stuSex;
	
	/** 孩子头像 */
	@Column(name = "stu_avatar" )
	private String stuAvatar;
	
	/** 出生日期 */
	@Column(name = "stu_birthday" )
	private java.util.Date stuBirthday;
	
	/** 报名时间 */
	@Column(name = "created_time" )
	private java.util.Date createdTime;
	
	/** 剩余课时 */
	@Column(name = "classtimes" )
	private Integer classtimes;
	
	@Column(name = "openclass" )
	private Integer openclass;
	
}
