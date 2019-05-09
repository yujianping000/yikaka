package com.framework.loippi.controller.app.model;


import com.framework.loippi.mybatis.ext.annotation.Column;


/**
 * Entity - 课程教学时间评论
 * 
 * @author wgb
 * @version 2.0
 */

public class AppCourseTimeComment {


	/**  */
	private Long id;

	/** 星星 */
	@Column(name = "fav_star" )
	private Integer favStar;
	
	/** 评价内容 */
	@Column(name = "content" )
	private String content;
	/** 标签 */
	@Column(name = "mark" )
	private String mark;
	

	/**  */
	@Column(name = "teacher_avatar" )
	private String teacherAvatar;
	
	/**  */
	@Column(name = "teacher_name" )
	private String teacherName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFavStar() {
		return favStar;
	}

	public void setFavStar(Integer favStar) {
		this.favStar = favStar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getTeacherAvatar() {
		return teacherAvatar;
	}

	public void setTeacherAvatar(String teacherAvatar) {
		this.teacherAvatar = teacherAvatar;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	
}
