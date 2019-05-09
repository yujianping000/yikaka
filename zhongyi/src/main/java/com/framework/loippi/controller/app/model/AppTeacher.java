package com.framework.loippi.controller.app.model;

import java.util.List;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 教师表
 * 
 * @author wgb
 * @version 2.0
 */
public class AppTeacher {


	/**  */
	private Long id;
	
	/** 昵称 */
	@Column(name = "nickname" )
	private String nickname;
	
	/** 头像 */
	private String avatar;
	
	/** 生日 */
	private String birthdayString;
	
	/** 标签  以，隔开 */
	private String mark;
	
	/** 简介 */
	private String description;
	
	/** 老师的star */
	private Integer favStar;
	
	/** 性别 */
	private String sex;
	
	/** 1-正常   2-锁定 */
	private Integer status;
	
	/** 教龄 */
	private Integer age;
	
	/** 机构ids */
	private String orgIds;
	/** 机构名 */
	private String orgNames;
	
	/**教师类型  1-全职  2-兼职*/
	private Integer teacherType;
	

	private List<AppTeacherExperience> appTeacherExperiences;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getBirthdayString() {
		return birthdayString;
	}

	public void setBirthdayString(String birthdayString) {
		this.birthdayString = birthdayString;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public List<AppTeacherExperience> getAppTeacherExperiences() {
		return appTeacherExperiences;
	}

	public void setAppTeacherExperiences(List<AppTeacherExperience> appTeacherExperiences) {
		this.appTeacherExperiences = appTeacherExperiences;
	}

	public Integer getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(Integer teacherType) {
		this.teacherType = teacherType;
	}

}
