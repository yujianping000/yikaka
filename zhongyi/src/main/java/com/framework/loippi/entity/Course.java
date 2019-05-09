package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 课程表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE")
public class Course implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;
	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 名称 */
	@Column(name = "name" )
	private String name;
	
	/** 课程图片 */
	@Column(name = "image" )
	private String image;
	
	/** 描述 */
	@Column(name = "description" )
	private String description;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 机构名称 */
	@Column(name = "orgname" )
	private String orgname;
	
	/** 用户 */
	@Column(name = "uid" )
	private Long uid;
	
	/** 星星 */
	@Column(name = "fav_count" )
	private Integer favCount;
	
	/** 回复数 */
	@Column(name = "reply_count" )
	private Integer replyCount;
	
	/**  */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/**  */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 代课老师 */
	@Column(name = "substitute" )
	private Long substitute;
	
	/** 助教 */
	@Column(name = "assistant" )
	private Long assistant;
	
	/** 1、一对一   2、集体课 */
	@Column(name = "type" )
	private Integer type;
	
	/** 价格 */
	@Column(name = "price" )
	private java.math.BigDecimal price;
	
	/** 购买人数 */
	@Column(name = "buy_sum" )
	private Integer buySum;
	
	/** 空闲数量 */
	@Column(name = "freez" )
	private Integer freez;
	
	/** 总数 */
	@Column(name = "total_sum" )
	private Integer totalSum;
	
	/** 1-可预约   2-关闭 */
	@Column(name = "praper" )
	private Integer praper;
	
	/** 1-正常   2-下架 */
	@Column(name = "status" )
	private Integer status;
	
	/** 教室id */
	@Column(name = "class_id" )
	private Long classId;
	
	/** 课时介绍 */
	@Column(name = "periods_desc" )
	private String periodsDesc;
	
	/** 订单id */
	@Column(name = "order_id" )
	private Long orderId;
	
	/** 集体课，第几期 */
	@Column(name = "term" )
	private Integer term;
	
	/** 课时*/
	@Column(name = "periods" )
	private Integer periods;
	
	/** 代课老师姓名*/
	@Column(name = "substitute_name" )
	private String substituteName;
	
	/** 是否代课   1——不代课    2——代课*/
	@Column(name = "substitute_type" )
	private Integer substituteType;
	
	/**课程类型*/
	@Column(name = "course_type" )
	private String courseType;
	
	/**评价个数*/
	private Integer favStar;
}
