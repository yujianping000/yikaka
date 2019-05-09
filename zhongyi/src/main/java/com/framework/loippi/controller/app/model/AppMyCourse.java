package com.framework.loippi.controller.app.model;

import java.util.List;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppMyCourse {
	/** 课程id */
	private Long id;
	
	/** 课程名称 */
	private String name;
	
	/** 课程图片 */
	private String image;

	/** 用户 */
	private Long studentId;

	/** 1、一对一   2、集体课 */
	private Integer type;
	
	
	
	/** 机构id */
	private Long orgId;
	/** 机构名称 */
	private String orgname;
	/**  */
	private Long teacherId;
	/** 教师姓名 */
	private String teacherName ;
	
	/** 教师头像 */
	private String teacherAvatar ;
	
	/** 剩余课时 */
	private Integer classtimes;
	
	private Long orderId;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

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

	public Integer getClasstimes() {
		return classtimes;
	}

	public void setClasstimes(Integer classtimes) {
		this.classtimes = classtimes;
	}





}
