package com.framework.loippi.controller.app.model;

import com.framework.loippi.mybatis.ext.annotation.Column;

public class AppUsrRelation {
	//目标用户id
	private Long destUid;
	/** 目标用户名称 */
	private String destName;
	
	/** 目标用户头像 */
	private String destAvatar;
	
	/** 用户首字母 */
	private String destFirstName;
	public Long getDestUid() {
		return destUid;
	}

	public void setDestUid(Long destUid) {
		this.destUid = destUid;
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

	public String getDestFirstName() {
		return destFirstName;
	}

	public void setDestFirstName(String destFirstName) {
		this.destFirstName = destFirstName;
	}


}
