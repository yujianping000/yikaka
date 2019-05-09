package com.framework.loippi.controller.app.model;

public class AppActivitySignup {
	
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuAvatar() {
		return stuAvatar;
	}
	public void setStuAvatar(String stuAvatar) {
		this.stuAvatar = stuAvatar;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public Integer getStuAge() {
		return stuAge;
	}
	public void setStuAge(Integer stuAge) {
		this.stuAge = stuAge;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSignupTime() {
		return signupTime;
	}
	public void setSignupTime(String signupTime) {
		this.signupTime = signupTime;
	}
	/** 学生姓名 */
	private String stuName;
	/** 学生头像 */
	private String stuAvatar;
	
	/** 学生性别 */
	private String stuSex;
	
	/** 学生年龄 */
	private Integer stuAge;
	
	/** 联系电话 */
	private String phone;
	/** 备注 */ 
	private String remark;
	/** 报名时间 */
	private String signupTime;
}
