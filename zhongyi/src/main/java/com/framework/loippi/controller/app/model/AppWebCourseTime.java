package com.framework.loippi.controller.app.model;

public class AppWebCourseTime {
        public String getCoursedate() {
		return coursedate;
	}
	public void setCoursedate(String coursedate) {
		this.coursedate = coursedate;
	}
	public String getCoursetime() {
		return coursetime;
	}
	public void setCoursetime(String coursetime) {
		this.coursetime = coursetime;
	}
	public String getCourseweek() {
		return courseweek;
	}
	public void setCourseweek(String courseweek) {
		this.courseweek = courseweek;
	}
	Long id;
		public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		String coursedate;
        String coursetime;
        String courseweek;
}
