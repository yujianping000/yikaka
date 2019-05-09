package com.framework.loippi.controller.app.model;

import java.util.List;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppMyCourseDetail {
	/**  */
	private Long id;
	
	/** 名称 */
	private String name;
	
	/** 课程图片 */
	private String image;
	
	/** 描述 */
	private String description;
	
	/** 机构id */
	private Long orgId;
	
	private Long studentId;
	
	
	private Long orderId;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	private String orgname;

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/** 用户 */
	private Long uid;
	
	/** 星星 */
	private Integer favCount;

	/** 回复数 */
	private Integer replyCount;
	
	/**  */
	private String createDateString;
	
	/**  */
	private Long teacherId;
	
	/** 代课老师 */
	private Long substitute;
	
	/** 助教 */
	private Long assistant;
	
	/** 1、一对一   2、集体课 */
	private Integer type;
	
	/** 价格 */
	private java.math.BigDecimal price;
	
	/** 购买人数 */
	private Integer buySum;
	
	/** 空闲数量 */
	private Integer freez;
	
	/** 总数 */
	private Integer totalSum;
	
	/** 1-可预约   2-关闭 */
	private Integer praper;
	
	/** 1-正常   2-下架 */
	private Integer status;
	
	/** 教室id */
	private Long classId;
	

	/** 课时介绍 */
	private String periodsDesc;
	
	
	/** 教师姓名 */
	private String teacherName ;
	
	/** 教师头像 */
	private String teacherAvatar ;
	
	/** 教师头像 */
	private Integer teacherAage ;
	
	/** 教师标签 */
	private String teacherMark;
	
	/** 剩余课时 */
	private Integer classtimes;
	
	public Integer getClasstimes() {
		return classtimes;
	}

	public void setClasstimes(Integer classtimes) {
		this.classtimes = classtimes;
	}

	public String getTeacherMark() {
		return teacherMark;
	}

	public void setTeacherMark(String teacherMark) {
		this.teacherMark = teacherMark;
	}

	private List<AppCourseTime> appCourseTimes;
	
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherAvatar() {
		return teacherAvatar;
	}

	public void setTeacherAvatar(String teacherAvatar) {
		this.teacherAvatar = teacherAvatar;
	}

	public Integer getTeacherAage() {
		return teacherAage;
	}

	public void setTeacherAage(Integer teacherAage) {
		this.teacherAage = teacherAage;
	}

	
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

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getFavCount() {
		return favCount;
	}

	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}


	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getSubstitute() {
		return substitute;
	}

	public void setSubstitute(Long substitute) {
		this.substitute = substitute;
	}

	public Long getAssistant() {
		return assistant;
	}

	public void setAssistant(Long assistant) {
		this.assistant = assistant;
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

	public Integer getBuySum() {
		return buySum;
	}

	public void setBuySum(Integer buySum) {
		this.buySum = buySum;
	}

	public Integer getFreez() {
		return freez;
	}

	public void setFreez(Integer freez) {
		this.freez = freez;
	}

	public Integer getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Integer totalSum) {
		this.totalSum = totalSum;
	}

	public Integer getPraper() {
		return praper;
	}

	public void setPraper(Integer praper) {
		this.praper = praper;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getPeriodsDesc() {
		return periodsDesc;
	}

	public void setPeriodsDesc(String periodsDesc) {
		this.periodsDesc = periodsDesc;
	}

	public List<AppCourseTime> getAppCourseTimes() {
		return appCourseTimes;
	}

	public void setAppCourseTimes(List<AppCourseTime> appCourseTimes) {
		this.appCourseTimes = appCourseTimes;
	}
	
	
	public String getCreateDateString() {
		return createDateString;
	}

	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}


}
