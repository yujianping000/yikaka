package com.framework.loippi.controller.app.model;

import java.math.BigDecimal;
import java.util.Date;

public class AppActivity {
	
	private Long id;
	
	private String name;
	
	private Integer status;
	
	private String image;


	private BigDecimal free;
	
	private String startTime;
	
	private String endTime;
	
	private Long uid;
	
	private String location;
	
	private BigDecimal locLat;
	
	private BigDecimal LocLng;
	
	private String locGeo;
	
	private String company;
	
	private String ageRank;
	
	private String createDate;
	
	private Integer totalSum;

	private Integer totalFeez;
	
	private Integer already;
	
	private String description;
	
	private String organization;
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	private Long orgId;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getFree() {
		return free;
	}

	public void setFree(BigDecimal free) {
		this.free = free;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getLocLat() {
		return locLat;
	}

	public void setLocLat(BigDecimal locLat) {
		this.locLat = locLat;
	}

	public BigDecimal getLocLng() {
		return LocLng;
	}

	public void setLocLng(BigDecimal locLng) {
		LocLng = locLng;
	}

	public String getLocGeo() {
		return locGeo;
	}

	public void setLocGeo(String locGeo) {
		this.locGeo = locGeo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAgeRank() {
		return ageRank;
	}

	public void setAgeRank(String ageRank) {
		this.ageRank = ageRank;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Integer totalSum) {
		this.totalSum = totalSum;
	}


	public Integer getTotalFeez() {
		return totalFeez;
	}

	public void setTotalFeez(Integer totalFeez) {
		this.totalFeez = totalFeez;
	}

	public Integer getAlready() {
		return already;
	}

	public void setAlready(Integer already) {
		this.already = already;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	
}
