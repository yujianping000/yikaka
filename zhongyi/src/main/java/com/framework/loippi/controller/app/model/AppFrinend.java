package com.framework.loippi.controller.app.model;

import java.util.List;

public class AppFrinend {
    private Integer isRead;
 	private List<AppFriends> appFriends;
     public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public List<AppFriends> getAppFriends() {
		return appFriends;
	}
	public void setAppFriends(List<AppFriends> appFriends) {
		this.appFriends = appFriends;
	}

}
