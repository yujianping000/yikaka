package com.framework.loippi.controller.app;


public class AppTeacherCenter {
	/** 昵称 */
	private String nickname;
	/** 头像 */
	private String avatar;
	
	/** 生日 */
	private String birthday;
	
	/** 标签  以，隔开 */
	private String mark;
	
	/** 老师的star */
	private Integer favStar;
	
	
	/** 性别 */
	private String sex;
	
	/** 简介 */
	private String description;
	
	/** 教师类型*/
	private Integer teacherType;
	


	public Integer getTeacherType() {
		return teacherType;
	}
	public void setTeacherType(Integer teacherType) {
		this.teacherType = teacherType;
	}
	/** 简介 */
	private String orgName;
	
	private Long orgId;
	
	
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Integer getFavStar() {
		return favStar;
	}
	public void setFavStar(Integer favStar) {
		this.favStar = favStar;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
