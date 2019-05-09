package com.framework.loippi.entity;

import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 家长表
 * 
 * @author longbh
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_PARENT")
public class TParent implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 账号 */
	@Column(name = "account" )
	private String account;
	
	/** 密码 */
	@Column(name = "password" )
	private String password;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 第三方code */
	@Column(name = "social_code" )
	private String socialCode;
	
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
	private Date createDate;
	
	/** 1-正常   2-锁定 */
	@Column(name = "status" )
	private Integer status;
	
	/** QQ帐号 */
	@Column(name = "qq_account" )
	private String qqAccount;
	
	/** QQ名称 */
	@Column(name = "qq_name" )
	private String qqName;
	
	/** QQ头像 */
	@Column(name = "qq_avatar" )
	private String qqAvatar;
	
	/** 微信帐号 */
	@Column(name = "weixin_account" )
	private String weixinAccount;
	
	/** 微信名称 */
	@Column(name = "weixin_name" )
	private String weixinName;
	
	/** 微信头像 */
	@Column(name = "weixin_avatar" )
	private String weixinAvatar;
	
	/** 等级 */
	@Column(name = "level" )
	private Integer level;
	/** 等级图片 */
	@Column(name = "levelimage" )
	private String levelimage;
	
	/** 成长值 */
	@Column(name = "grow_up_value" )
	private Integer growupValue;
	
	/** 微信头像 */
	@Column(name = "starts" )
	private Double starts;
	
	/** 首字母 */
	@Column(name = "first_name" )
	private String firstName;
	
	/** uuid */
	@Column(name = "uuid" )
	private String uuid;
	
}
