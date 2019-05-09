package com.framework.loippi.controller.app.model;

import java.util.List;

public class AppCourseTch {
	/**  */
	private Long Courseid;
	/** 名称 */
	private String name;
	
	/** 课程图片 */
	private String image;
	
	/** 描述 */
	private String description;
	
	/** 机构id */
	private Long orgId;
	
	private String orgname;
	
	/** 1、一对一   2、集体课 */
	private Integer type;
	
	/** 价格 */
	private java.math.BigDecimal price;
	
	

	public Long getCourseid() {
		return Courseid;
	}

	public void setCourseid(Long Courseid) {
		this.Courseid = Courseid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
}
