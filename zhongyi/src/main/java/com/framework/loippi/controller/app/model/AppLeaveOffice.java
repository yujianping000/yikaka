package com.framework.loippi.controller.app.model;

import java.util.Date;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppLeaveOffice {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getClassCondition() {
		return classCondition;
	}

	public void setClassCondition(String classCondition) {
		this.classCondition = classCondition;
	}

	public String getApplyTimet() {
		return applyTimet;
	}

	public void setApplyTimet(String applyTimet) {
		this.applyTimet = applyTimet;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getLeaveTimet() {
		return leaveTimet;
	}

	public void setLeaveTimet(String leaveTimet) {
		this.leaveTimet = leaveTimet;
	}
	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	private String backTime;
   
	private String backReason;
	/**  */
	private Long id;
	
	/** 帐号 */
	private String account;
	
	/** 性别 */
	private String sex;
	
	/** 教师名称 */
	private String name;
	
	/** 头像 */
	private String avatar;
	
	/** 教师类型 */
	private Integer type;
	
	/** 离职原因 */
	private String reason;
	
	/** 班级情况 */
	private String classCondition;
	
	/** 申请时间 */
	private String applyTimet;
	
	/** 状态 1已同意 2已拒绝 3未审核 */
	private Integer status;
	
	/** 教师id */
	private Long teacherId;
	
	/** 教师id */
	private String leaveTimet;
}
