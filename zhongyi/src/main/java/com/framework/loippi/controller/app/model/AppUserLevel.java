package com.framework.loippi.controller.app.model;

public class AppUserLevel {
	  private Double starts;
      private Integer growupValue;
      private Integer Level;
      private Long taskTotal;
      private Integer dynamicIsRead;
      private String levelimage;
   
	public String getLevelimage() {
		return levelimage;
	}
	public void setLevelimage(String levelimage) {
		this.levelimage = levelimage;
	}
	public Double getStarts() {
		return starts;
	}
	public void setStarts(Double starts) {
		this.starts = starts;
	}
	public Integer getGrowupValue() {
		return growupValue;
	}
	public void setGrowupValue(Integer growupValue) {
		this.growupValue = growupValue;
	}
	public Integer getLevel() {
		return Level;
	}
	public void setLevel(Integer level) {
		Level = level;
	}
	public Long getTaskTotal() {
		return taskTotal;
	}
	public void setTaskTotal(Long taskTotal) {
		this.taskTotal = taskTotal;
	}
	public Integer getDynamicIsRead() {
		return dynamicIsRead;
	}
	public void setDynamicIsRead(Integer dynamicIsRead) {
		this.dynamicIsRead = dynamicIsRead;
	}
}
