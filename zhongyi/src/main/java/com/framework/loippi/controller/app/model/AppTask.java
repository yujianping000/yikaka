package com.framework.loippi.controller.app.model;

public class AppTask {
      private long taskId;
	  private String content;
      private Integer growupvalue;
      private Integer status;
      
      public long getTaskId() {
  		return taskId;
  	}
  	public void setTaskId(long taskId) {
  		this.taskId = taskId;
  	}
  	public String getContent() {
  		return content;
  	}
  	public void setContent(String content) {
  		this.content = content;
  	}
  	public Integer getGrowupvalue() {
  		return growupvalue;
  	}
  	public void setGrowupvalue(Integer growupvalue) {
  		this.growupvalue = growupvalue;
  	}
  	public Integer getStatus() {
  		return status;
  	}
  	public void setStatus(Integer status) {
  		this.status = status;
  	}
}
