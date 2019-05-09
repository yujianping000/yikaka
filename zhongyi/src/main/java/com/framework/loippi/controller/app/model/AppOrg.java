package com.framework.loippi.controller.app.model;

import java.math.BigDecimal;

public class AppOrg {
	/**  */
	private Long id;
	
	/** 店名 */
	private String name;
	
	/** 封面 */
	private String image;

	/** 星星 */
	private Integer favStar;
	
	/** 评价数 */
	private Integer replyCount;
	
	/** 机构电话 */
	private String telPhone;
	
	/** 介绍 */
	private String description;
	
	/** 是否选中 */
	private Integer isCheck;
	
	/** 地区 */
	private String location;
	
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getFavStar() {
		return favStar;
	}

	public void setFavStar(Integer favStar) {
		this.favStar = favStar;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
