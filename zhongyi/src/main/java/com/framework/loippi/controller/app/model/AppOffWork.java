package com.framework.loippi.controller.app.model;

import java.util.List;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppOffWork {
   
	public List<String> getCourseTime() {
		return courseTime;
	}

	public void setCourseTime( List<String>courseTime) {
		this.courseTime = courseTime;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDayNum() {
		return dayNum;
	}

	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	private String avatar;
	
	private String userName;
	
	/** 请假类型   1-事假   2-病假   3-年假  4-调休  5-婚假  6-产假  7-陪产假  8-丧假  9-其他 */
	private String type;
	
	/**  */
	private String image;
	
	/** 描述 */
	private String description;
	
	/** 课程id */
	private Long courseId;
	
	/** 课时 */
	private  List<String>courseTime;
	


	private String createDate;


	/** 开始时间 */
	private String start;
	
	/** 结束时间 */
	private String end;
	
	
	/** 请假原因 */
	private String reason;
	
	/** 状态 1同意 0拒绝 */
	private Integer status;
	
	/** 请假天数 */
	private Integer dayNum;
	
	private String courseName;


	
}
