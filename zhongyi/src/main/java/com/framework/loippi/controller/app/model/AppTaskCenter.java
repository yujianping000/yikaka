package com.framework.loippi.controller.app.model;

import java.util.List;

public class AppTaskCenter {
	  private  AppLevelInfo appLevelInfo;
	private  List<AppTask> appTasks;
	   public List<AppTask> getAppTasks() {
		return appTasks;
	}
	public void setAppTasks(List<AppTask> appTasks) {
		this.appTasks = appTasks;
	}
      public AppLevelInfo getAppLevelInfo() {
		return appLevelInfo;
	}
	public void setAppLevelInfo(AppLevelInfo appLevelInfo) {
		this.appLevelInfo = appLevelInfo;
	}

}
