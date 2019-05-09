package com.framework.loippi.controller.app.model;


/**
 * Entity - 任教经历
 * 
 * @author wgb
 * @version 2.0
 */

public class AppTeacherExperience  {

	public String getStartString() {
		return startString;
	}

	public void setStartString(String startString) {
		this.startString = startString;
	}

	public String getEndString() {
		return endString;
	}

	public void setEndString(String endString) {
		this.endString = endString;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/** 开始时间 */
	private String startString;
	
	/** 结束时间 */
	private String endString;
	
	/**  */
	private String content;

	
}
