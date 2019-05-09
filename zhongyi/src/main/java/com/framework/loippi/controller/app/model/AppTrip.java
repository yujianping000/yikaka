package com.framework.loippi.controller.app.model;

import java.util.Date;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppTrip {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTripTime() {
		return tripTime;
	}

	public void setTripTime(String tripTime) {
		this.tripTime = tripTime;
	}

	public String getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**  */
	private Long id;
	
	/** 行程时间 */
	private String tripTime;
	
	/** 提醒时间 */
	private String alarmTime;
	
	/** 创建时间 */
	private String createDate;
	
	/** 内容 */
	private String content;
	
	/** 图片 */
	private String images;
	
	/** 所属用户 */
	private Long uid;
}
