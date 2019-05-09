package com.framework.loippi.controller.app.model;
//全部学生，旷课
public class AppStudent1 {
	
	Long id;//学生id
    String name;//学生姓名
    String avatar;//学生头像
    Integer period;//学生课时
	
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
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}

}
