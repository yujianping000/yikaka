package com.framework.loippi.controller.app.model;
//续班
public class AppStudent2 {
	
	Long id;//学生id
    String name;//学生姓名
    String avatar;//学生头像
    String time;//学生续班时间
	
       public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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


}
