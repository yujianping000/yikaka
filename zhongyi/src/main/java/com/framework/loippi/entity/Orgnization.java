package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 机构
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ORGNIZATION")
public class Orgnization implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/**  */
	@Column(name = "name" )
	private String name;
	
	/** 封面 */
	@Column(name = "image" )
	private String image;
	
	/** 省 */
	@Column(name = "province" )
	private String province;
	
	/**  */
	@Column(name = "city" )
	private String city;
	
	/** 区 */
	@Column(name = "area" )
	private String area;
	
	/** 地区 */
	@Column(name = "location" )
	private String location;
	
	/** 纬度 */
	@Column(name = "loc_lat" )
	private java.math.BigDecimal locLat;
	
	/** 经度 */
	@Column(name = "loc_lng" )
	private java.math.BigDecimal locLng;
	
	/** 星星 */
	@Column(name = "fav_star" )
	private Integer favStar;
	
	/** 评价数 */
	@Column(name = "reply_count" )
	private Integer replyCount;
	
	/**  */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/**  */
	@Column(name = "uid" )
	private Long uid;
	
	/** 机构电话 */
	@Column(name = "tel_phone" )
	private String telPhone;
	
	/** 联系人名称 */
	@Column(name = "service_name" )
	private String serviceName;
	
	/** 1-整除  2-禁用 */
	@Column(name = "status" )
	private Integer status;
	
	/** 手续费 */
	@Column(name = "fee" )
	private java.math.BigDecimal fee;
	
	/**  */
	@Column(name = "description" )
	private String description;
	
	/** 课程名，用于app端搜索 */
	@Column(name = "course_content" )
	private String courseContent;
	
	/** 拥有的课程类型 */
	@Column(name = "type" )
	private String type;
	
	/** 人气 */
	@Column(name = "hit" )
	private Integer hit;
	
	/** 距离 */
	@Column(name = "distance" )
	private java.math.BigDecimal distance;
	
	/** 用户评价 */
	@Column(name = "evaluation" )
	private java.math.BigDecimal evaluation;
	
	/** 教师数量 */
	@Column(name = "teacher_count" )
	private Integer teacherCount;
	
	/** 学院数量 */
	@Column(name = "student_count" )
	private Integer studentCount;
	
	/** */
	@Column(name = "uuid" )
	private String uuid;
	
	/**联系人电话 */
	@Column(name = "service_phone" )
	private String servicePhone;
	
	/**机构账号 */
	@Column(name = "account" )
	private String account;
	
	/**机构密码 */
	@Column(name = "password" )
	private String password;
	
	/**后台名称 */
	@Column(name = "background_name" )
	private String backgroundName;
	
	/**后台logo */
	@Column(name = "background_logo" )
	private String backgroundLogo;
	
	/**机构二维码图片 */
	@Column(name = "org_image" )
	private String orgImage;
	
	/** 省份id */
	@Column(name = "province_id" )
	private Long provinceId;
	
	/** 城市id */
	@Column(name = "city_id" )
	private Long cityId;
	
	/** 区域id */
	@Column(name = "area_id" )
	private Long areaId;
	
	/**用户分数统计*/
	private Integer userRate;
	
}
