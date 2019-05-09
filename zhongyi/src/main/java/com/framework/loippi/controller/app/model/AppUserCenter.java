package com.framework.loippi.controller.app.model;

public class AppUserCenter {
	 private String avatar;
     private String sex;
     private String nickname;
     private Integer mesIsRead;
 	private String realName;
    private Integer isEditPwd;
     public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getIsEditPwd() {
		return isEditPwd;
	}
	public void setIsEditPwd(Integer isEditPwd) {
		this.isEditPwd = isEditPwd;
	}

	
     public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getMesIsRead() {
		return mesIsRead;
	}
	public void setMesIsRead(Integer mesIsRead) {
		this.mesIsRead = mesIsRead;
	}
}
