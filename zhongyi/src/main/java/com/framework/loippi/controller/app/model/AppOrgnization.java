package com.framework.loippi.controller.app.model;


/**
 * 机构
 */
public class AppOrgnization {
	
	//机构id
	private Long id;
	
	//机构名
	private String name;
	
	//机构图片
	private String image;
	
	//星星
	private Integer favStar;
	
	//机构位置
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
