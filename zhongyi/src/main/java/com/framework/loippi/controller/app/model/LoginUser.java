package com.framework.loippi.controller.app.model;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.mybatis.ext.annotation.Column;

public class LoginUser {
	
	  public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
    public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	 public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}
     
		
		public Integer getFavStar() {
			return favStar;
		}

		public void setFavStar(Integer favStar) {
			this.favStar = favStar;
		}

	/**  */
    private Long id;

    /**  */
    private String phone;
   

	/**  */
    private String avatar;
    
    /**  */
    private String sex;

    /**
     * 0-被禁   1-正常   
     */
    private Integer status;

    private String userName;

    private String sessionId;
    
	private Long orgId;
    
	/* 星星*/
	private Integer favStar;
   
	/*用户类型*/
	private Integer userType;

	/** 登录类型 */
	private Integer type;
    

    

    public static LoginUser ofParent(TParent tParent,String sessionId) {
        LoginUser loginUser = new LoginUser();
        loginUser.setSex(tParent.getSex());
        loginUser.setAvatar(tParent.getAvatar());
        loginUser.setType(tParent.getType());
        loginUser.setId(tParent.getId());
        loginUser.setStatus(tParent.getStatus());
        loginUser.setPhone(tParent.getAccount());
        loginUser.setUserName(tParent.getNickName());
        loginUser.setSessionId(sessionId);
        loginUser.setUserType(1);
        return loginUser;
    }
    
    public static LoginUser ofTeacher(Teacher tParent,String sessionId) {
        LoginUser loginUser = new LoginUser();
        loginUser.setSex(tParent.getSex());
        loginUser.setAvatar(tParent.getAvatar());
        loginUser.setType(tParent.getType());
        loginUser.setId(tParent.getId());
        loginUser.setStatus(tParent.getStatus());
        loginUser.setPhone(tParent.getAccount());
        loginUser.setUserName(tParent.getNickname());
        loginUser.setFavStar(tParent.getFavStar());
        loginUser.setSessionId(sessionId);
        loginUser.setUserType(2);
        return loginUser;
    }


}
