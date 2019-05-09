package com.framework.loippi.controller.app.model;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppOrgDynamic {
	
	/**  */
	private Long id;
	
	/** 图片路径 */
	private String imgUrl;
	
	/** 文字内容 */
	private String textContent;
	
	/** 提示 */
	private String prompt;
	
	/** 星星数 */
	private Integer starts;
	
	/** 标签 */
	private String mark;
	
	/** 点名状态 1确定 2未确定 */
	private Integer callrollStatus;
	
	/** 点名id*/
	private Integer callrollId;
	
	/** 教师id */
	private Long teacherId;
	
	/** 课程id */
	private Long courseId;
	
	/** 课程名称 */
	private String courseName;
	
	/** 教师名称 */
	private String teacherName;
	
	/** 教师头像 */
	private String avatar;
	
	/** 创建时间 */
	private String createDate1;
	
	/** 邀请状态 */
	private String inviteStatus;
	
	/** 邀请id */
	private String inviteId;
	
	public String getOldCourseName() {
		return oldCourseName;
	}

	public void setOldCourseName(String oldCourseName) {
		this.oldCourseName = oldCourseName;
	}

	public String getNewCourseName() {
		return newCourseName;
	}

	public void setNewCourseName(String newCourseName) {
		this.newCourseName = newCourseName;
	}

	public Integer getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(Integer remainTime) {
		this.remainTime = remainTime;
	}

	private String oldCourseName;
	

	private String newCourseName;
	
	private Integer remainTime;
	
	/**邀请原因*/
	private String inviteReason;
		
	/** 动态类型 1课程总结 2老师对学生评价 3点名 4迟到提醒 */
	private Integer type;
	
	private Long  orgId;
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Integer getStarts() {
		return starts;
	}

	public void setStarts(Integer starts) {
		this.starts = starts;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Integer getCallrollStatus() {
		return callrollStatus;
	}

	public void setCallrollStatus(Integer callrollStatus) {
		this.callrollStatus = callrollStatus;
	}

	public Integer getCallrollId() {
		return callrollId;
	}

	public void setCallrollId(Integer callrollId) {
		this.callrollId = callrollId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCreateDate1() {
		return createDate1;
	}

	public void setCreateDate1(String createDate1) {
		this.createDate1 = createDate1;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public String getInviteStatus() {
		return inviteStatus;
	}

	public void setInviteStatus(String inviteStatus) {
		this.inviteStatus = inviteStatus;
	}

	public String getInviteId() {
		return inviteId;
	}

	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}
	
	public String getInviteReason() {
		return inviteReason;
	}

	public void setInviteReason(String inviteReason) {
		this.inviteReason = inviteReason;
	}

}
