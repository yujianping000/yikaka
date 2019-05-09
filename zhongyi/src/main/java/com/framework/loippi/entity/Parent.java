package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import com.framework.loippi.utils.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 家长
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_PARENT")
public class Parent implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 账号 */
	@Column(name = "account" )
	private String account;
	
	/**  */
	@Column(name = "password" )
	private String password;
	
	/**  */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 第三方code */
	@Column(name = "qq_account" )
	private String qqAccount;
	
	/** 1-手机用户   2-微信  3-qq */
	@Column(name = "type" )
	private Integer type;
	
	/** 昵称 */
	@Column(name = "nick_name" )
	private String nickName;
	
	/** 真实姓名 */
	@Column(name = "rel_name" )
	private String relName;
	
	/** 性别 */
	@Column(name = "sex" )
	private String sex;
	
	/**  */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 1-正常   2-锁定 */
	@Column(name = "status" )
	private Integer status;
	
	/** qq名称 */
	@Column(name = "qq_name" )
	private String qqName;
	
	/** 微信帐号 */
	@Column(name = "weixin_account" )
	private String weixinAccount;
	
	/** 微信名称 */
	@Column(name = "weixin_name" )
	private String weixinName;
	
	/** 微信头像 */
	@Column(name = "weixin_avatar" )
	private String weixinAvatar;
	
	/** QQ头像 */
	@Column(name = "qq_avatar" )
	private String qqAvatar;
	
	/** 等级 */
	@Column(name = "level" )
	private Integer level;
	
	/** 成长值 */
	@Column(name = "grow_up_value" )
	private Integer growUpValue;
	
	/** 教师对学生的评价星星数量 */
	@Column(name = "starts" )
	private java.math.BigDecimal starts;
	
	/** 首字母 */
	@Column(name = "first_name" )
	private String firstName;
	
	/** 用户uuid */
	@Column(name = "uuid" )
	private String uuid;
	
	private String createDateString;
	
	private String statusString;
	
	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}
	
	public String getCreateDateString() {
		if(createDate != null){
			return StringUtil.date2StringTime(createDate);
		}
		return createDateString;
	}
	
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	
	public String getStatusString() {
		if(status != null) {
			if(status == 1) {
				return "启用";
			}
			if(status == 2) {
				return "禁用";
			}
		}
		return statusString;
	}
}
