package com.framework.loippi.controller.app.model;

import com.framework.loippi.mybatis.ext.annotation.Column;

/**
 * Entity - 订单表
 * 
 * @author wgb
 * @version 2.0
 */

public class AppOrders2 {

	private Long id;
	/** 订单编号 */
	private String bn;
	/** type=1课程id   type=2活动id */
	private Long targetId;
	/** 订单类型  1-课程   2-活动报名 */
	private Integer type;
	/** 1 待付款 2已付款 3 待评价 4已取消 5客户端删除 */
	private Integer status;
	private String targetName;
	/** 机构id */
	private Long orgId;
	/** 机构名称 */
	private String orgName;
	/**  */
	private String image;

	private java.math.BigDecimal payment;
	private Long stuId;
	
	public Long getStuId() {
		return stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	private String stuName;
	/** 学生头像 */
	private String stuAvatar;
	
	/** 学生性别 */
	private String stuSex;
	
	/** 学生年龄 */
	private Integer stuAge;
	
	/** 联系电话 */
	private String phone;
	
	/**活动——主办单位*/
	private String company;
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	private String remark;
	private String createdTimeString;
	
	
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuAvatar() {
		return stuAvatar;
	}

	public void setStuAvatar(String stuAvatar) {
		this.stuAvatar = stuAvatar;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public Integer getStuAge() {
		return stuAge;
	}

	public void setStuAge(Integer stuAge) {
		this.stuAge = stuAge;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedTimeString() {
		return createdTimeString;
	}

	public void setCreatedTimeString(String createdTimeString) {
		this.createdTimeString = createdTimeString;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public java.math.BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(java.math.BigDecimal payment) {
		this.payment = payment;
	}

	
	
}
