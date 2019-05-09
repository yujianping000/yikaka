package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import com.framework.loippi.utils.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 教师表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TEACHER")
public class Teacher implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 账号 */
	@Column(name = "account" )
	private String account;
	
	/** 密码 */
	@Column(name = "password" )
	private String password;
	
	/** 第三分key */
	@Column(name = "social_code" )
	private String socialCode;
	
	/** 1-手机用户   2-微信  3-qq */
	@Column(name = "type" )
	private Integer type;
	
	/** 昵称 */
	@Column(name = "nickname" )
	private String nickname;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 生日 */
	@Column(name = "birthday" )
	private java.util.Date birthday;
	
	/** 标签  以，隔开 */
	@Column(name = "mark" )
	private String mark;
	
	/** 简介 */
	@Column(name = "description" )
	private String description;
	
	/** 老师的star */
	@Column(name = "fav_star" )
	private Integer favStar;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 性别 */
	@Column(name = "sex" )
	private String sex;
	
	/** 1-正常   2-锁定 */
	@Column(name = "status" )
	private Integer status;
	
	/** 教龄 */
	@Column(name = "age" )
	private Integer age;
	
	
	/** 教师类型   1—全职教师  2—兼职教师 */
	@Column(name = "teacher_type" )
	private Integer teacherType;
	
	/** 机构名称 */
	@Column(name = "org_name" )
	private String orgName;
	
	/** 加入机构时间 */
	@Column(name = "join_time" )
	private java.util.Date joinTime;
	
	
	private String teacherTypeString;
	
	private String joinTimeString;
	
	private String statusString;
	
	public void setTeacherTypeString(String teacherTypeString) {
		this.teacherTypeString = teacherTypeString;
	}
	
	public String getTeacherTypeString() {
		if(teacherType != null) {
			if(teacherType == 1) {
				return "全职教师";
			}
			if(teacherType == 2) {
				return "兼职教师";
			}
		}
		return teacherTypeString;
	}
	
	public void setJoinTimeString(String joinTimeString) {
		this.joinTimeString = joinTimeString;
	}
	
	public String getJoinTimeString() {
		if(joinTime != null) {
			return StringUtil.date2String4(joinTime);
		}
		return joinTimeString;
	}
	
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	
	public String getStatusString() {
		if(status != null) {
			if(status == 1) {
				return "启用";
			}
			if(status == 2) {
				return "禁用";
			}
		} 
		return statusString;
	}
	
	/** 标签一 */
	private String mark1;
	
	/** 标签二 */
	private String mark2;
	
	/** 标签三*/
	private String mark3;
	
	/** 邀请状态     1—未邀请    2—已邀请 ,默认为未邀请状态*/
	private Integer inviteType;
	
	/**用户评价分数*/
	private Integer userEvaluation;
	
	/**机构评价评价分数*/
	private Integer orgEvaluation;
	
	/**入职教师加入机构的时间*/
	private String joinTimeList;
	
	/**用户分数统计*/
	private Integer userRate;
}
