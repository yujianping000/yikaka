package com.framework.loippi.controller.app.model;

public class CourseTime {
     private String name;
     public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public Long getStuId() {
		return stuId;
	}
	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String courseTime;
     private Long stuId;
     private String text;
} 
