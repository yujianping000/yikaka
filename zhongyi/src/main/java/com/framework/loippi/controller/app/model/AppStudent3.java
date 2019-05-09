package com.framework.loippi.controller.app.model;
//请假
public class AppStudent3 {
	
	Long id;//学生id
    String name;//学生姓名
    String avatar;//学生头像
    String time;//学生请假时间
    String reason;//学生请假原因
    String status;//学生请假状态
       public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
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
