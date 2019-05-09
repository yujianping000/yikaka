package com.framework.loippi.controller.app.model;

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

public class AppCourseComment  {


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getFavStar() {
		return favStar;
	}

	public void setFavStar(Integer favStar) {
		this.favStar = favStar;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	public String getCreateDateString() {
		return createDateString;
	}

	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**  */
	private Long id;
	
	/** 评论用户 */
	private Long uid;
	
	/** 星星 */
	private Integer favStar;
	
	/** 评论时间 */
	private String createDateString;


	/** 评论内容 */
	private String content;
	
	/** 评论标签 */
	private String mark;
	
	/**课程id  */
	private Long courseId;
	
	/**机构id  */
	private Long orgId;
	
	/** 教师id */
	private Long teacherId;
	
	/** 头像 */
	private String avatar;
	
	/** 评论人 */
	private String name;


	
}
