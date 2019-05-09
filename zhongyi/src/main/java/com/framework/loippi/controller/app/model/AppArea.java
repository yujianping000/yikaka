package com.framework.loippi.controller.app.model;


public class AppArea {
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

	/** ID */
	private Long id;
	
	/** 名称 */
	private String name;
}
