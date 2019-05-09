package com.framework.loippi.controller.app.model;
//课程评价
public class AppStudent4 {
	
	Long id;//学生id
    String name;//学生姓名
    String avatar;//学生头像
    String content;
	
	String star;
	String mark;
	String nomark;
	
	public String getNomark() {
		return nomark;
	}
	public void setNomark(String nomark) {
		this.nomark = nomark;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
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
