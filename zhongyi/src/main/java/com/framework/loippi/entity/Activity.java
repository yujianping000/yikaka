package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 活动
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACTIVITY")
public class Activity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 名称 */
	@Column(name = "name" )
	private String name;
	
	/** 状态   1-报名中   2-进行中  3-未开始 4-一结束 */
	@Column(name = "status" )
	private Integer status;
	
	/**  */
	@Column(name = "image" )
	private String image;
	
	/** 费用 */
	@Column(name = "fee" )
	private java.math.BigDecimal fee;
	
	/** 报名开始时间 */
	@Column(name = "start_time" )
	private java.util.Date startTime;
	
	/** 报名结束时间 */
	@Column(name = "end_time" )
	private java.util.Date endTime;
	
	/** 创建用户id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 活动地点 */
	@Column(name = "location" )
	private String location;
	
	/** 坐标 */
	@Column(name = "loc_lat" )
	private java.math.BigDecimal locLat;
	
	/** 坐标 */
	@Column(name = "loc_lng" )
	private java.math.BigDecimal locLng;
	
	/** 坐标 */
	@Column(name = "loc_geo" )
	private String locGeo;
	
	/** 主办单位 */
	@Column(name = "company" )
	private String company;
	
	/** 年龄范围 */
	@Column(name = "age_rank" )
	private String ageRank;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 限额 */
	@Column(name = "total_sum" )
	private Integer totalSum;
	
	/** 空闲数量 */
	@Column(name = "total_feez" )
	private Integer totalFeez;
	
	/** 已加入 显示报名人数 */
	@Column(name = "already" )
	private Integer already;
	
	
	/** 已加入 实际报名人数 */
	@Column(name = "already2" )
	private Integer already2;
	
	/** 描述 */
	@Column(name = "description" )
	private String description;
	
	/** 机构 */
	@Column(name = "organization" )
	private String organization;
	
	/** id */
	@Column(name = "org_id" )
	private Long orgId;
	
	
	
}
