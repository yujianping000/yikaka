package com.framework.loippi.controller.app.model;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppUsrFriendRequest {
	/** 编号 */
	private Long id;
	/** 状态（0-未处理 1-已接受） */
	private Integer status;
	
	/** 目标用户名称 */
	private String destName;
	
	/** 目标用户头像 */
	private String destAvatar;
	
	private String content;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getDestAvatar() {
		return destAvatar;
	}

	public void setDestAvatar(String destAvatar) {
		this.destAvatar = destAvatar;
	}

}
