package com.framework.loippi.controller.app.model;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 上课时间安排
 * 
 * @author wgb
 * @version 2.0
 */

public class AppCourseTime  {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseTimeString() {
		return courseTimeString;
	}

	public void setCourseTimeString(String courseTimeString) {
		this.courseTimeString = courseTimeString;
	}


	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	/**  */
	private Long id;
	
	/** 课程上课时间 */
	private String courseTimeString;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/** 课程上课时间 */
	private String time;
	
	
	/** 课程id */
	private Long courseId;
	
}
