package com.framework.loippi.entity;

import java.math.BigDecimal;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppOrgnization {
	/**  */
	private Long id;
	
	/** 店名 */
	private String name;
	
	/** 封面 */
	private String image;
	
	/** 距离 */
	private BigDecimal distance;
	
	private Long uid;


	/** 省 */
	private String province;
	
	/** 城市 */
	private String city;
	
	/** 区 */
	private String area;
	
	/** 地区 */
	private String location;
	
	/** 纬度 */
	private java.math.BigDecimal locLat;
	
	/** 经度 */
	private java.math.BigDecimal locLng;
	
	/** 星星 */
	private Integer favStar;
	
	/** 评价数 */
	private Integer replyCount;
	
	/** 机构电话 */
	private String telPhone;
	
	/** 联系人名称 */
	private String serviceName;
	
	
	/** 手续费 */
	private java.math.BigDecimal fee;
	
	/** 介绍 */
	private String description;
	
	/** 课程名，用于app端搜索 */
	private String courseContent;
	
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

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public java.math.BigDecimal getLocLat() {
		return locLat;
	}

	public void setLocLat(java.math.BigDecimal locLat) {
		this.locLat = locLat;
	}

	public java.math.BigDecimal getLocLng() {
		return locLng;
	}

	public void setLocLng(java.math.BigDecimal locLng) {
		this.locLng = locLng;
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public java.math.BigDecimal getFee() {
		return fee;
	}

	public void setFee(java.math.BigDecimal fee) {
		this.fee = fee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}
	

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
}
