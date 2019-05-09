package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import com.framework.loippi.utils.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 订单表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ORDER")
public class Order implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;

	/** 订单编号 */
	@Column(name = "bn")
	private String bn;

	/** type=1课程id type=2活动id */
	@Column(name = "target_id")
	private Long targetId;

	/** 订单类型 1-课程 2-活动报名 */
	@Column(name = "type")
	private Integer type;

	/**  */
	@Column(name = "unickname")
	private String unickname;

	/** 家长id */
	@Column(name = "uid")
	private Long uid;

	/** 学生id */
	@Column(name = "stu_id")
	private Long stuId;

	/**  */
	@Column(name = "stu_name")
	private String stuName;

	/** 订单状态，1 待付款 2已付款 3 待评价 4已取消 5客户端删除 8订单完成 */
	@Column(name = "status")
	private Integer status;

	/** 订单取消原因 */
	@Column(name = "cancel_reason")
	private String cancelReason;

	/** 支付方式 1-支付宝 2-微信 3-银联 */
	@Column(name = "pay_type")
	private Integer payType;

	/** 支付金额 */
	@Column(name = "payment")
	private java.math.BigDecimal payment;

	/** 订单总额，不包含其他优惠 */
	@Column(name = "total_fee")
	private java.math.BigDecimal totalFee;

	/** 已支付的金额 */
	@Column(name = "payed_fee")
	private java.math.BigDecimal payedFee;

	/** 卖家是否评价 1-已评价 2-未评价 */
	@Column(name = "seller_rate")
	private Integer sellerRate;

	/** 创建时间 */
	@Column(name = "created_time")
	private java.util.Date createdTime;

	/** 支付时间 */
	@Column(name = "pay_time")
	private java.util.Date payTime;

	/** 申请time */
	@Column(name = "consign_time")
	private java.util.Date consignTime;

	/** 结束时间 */
	@Column(name = "end_time")
	private java.util.Date endTime;

	/** 更新时间 */
	@Column(name = "update_time")
	private java.util.Date updateTime;

	/** 超时时间 */
	@Column(name = "time_out_time")
	private java.util.Date timeOutTime;

	/** 取消时间 */
	@Column(name = "cancel_time")
	private java.util.Date cancelTime;

	/** 买家是否评价 */
	@Column(name = "buyer_rate")
	private Integer buyerRate;

	/** 评论状态 1-正常 2-已关闭 */
	@Column(name = "rate_status")
	private Integer rateStatus;

	/** 1-正常 2-关闭 */
	@Column(name = "disabled")
	private Integer disabled;

	/** 取消状态 */
	@Column(name = "cancel_status")
	private Integer cancelStatus;

	/** uuid */
	@Column(name = "uuid")
	private String uuid;

	/** 课程id */
	@Column(name = "course_id")
	private Long courseId;

	/** 课程名称 */
	@Column(name = "course_name")
	private String courseName;

	/** 机构id */
	@Column(name = "org_id")
	private Long orgId;

	/** 机构名称 */
	@Column(name = "org_name")
	private String orgName;

	/** 学生头像 */
	@Column(name = "stu_avatar")
	private String stuAvatar;

	/** 学生性别 */
	@Column(name = "stu_sex")
	private String stuSex;

	/** 学生年龄 */
	@Column(name = "stu_age")
	private Integer stuAge;

	/** 联系电话 */
	@Column(name = "phone")
	private String phone;

	/**  */
	@Column(name = "image")
	private String image;

	/** 备注 */
	@Column(name = "remark")
	private String remark;

	/**  */
	@Column(name = "target_name")
	private String targetName;

	/**  */
	@Column(name = "teacher_id")
	private Long teacherId;

	/** 剩余课时 */
	@Column(name = "classtimes")
	private Integer classtimes;

	/** 活动参赛号 */
	@Column(name = "activity_number")
	private String activityNumber;

	/** 活动获奖情况 */
	@Column(name = "activity_prize")
	private String activityPrize;

	/** 集体课，第几期 */
	@Column(name = "term")
	private Integer term;

	/** 交易流水号 */
	@Column(name = "serial_number")
	private String serialNumber;

	/** 学生生日 */
	@Column(name = "stu_birthday")
	private java.util.Date stuBirthday;

	/** 学生爱好 */
	@Column(name = "stu_hobby")
	private String stuHobby;

	/** 评价内容 */
	@Column(name = "rate_content")
	private String rateContent;

	/** 评价分数 */
	@Column(name = "rate_score")
	private java.math.BigDecimal rateScore;

	/** 开课状态 空已报名 2上课中 3已完成上课 */
	@Column(name = "openclass")
	private Integer openclass;

	/** 记账类型 */
	@Column(name = "tally_type")
	private Integer tallyType;

	/** 记账类型 */
	@Column(name = "order_type")
	private Integer orderType;

	/** 记账类型 */
	@Column(name = "procedure_rate")
	private java.math.BigDecimal procedureRate;

	/** 记账类型 */
	@Column(name = "procedure_cost")
	private java.math.BigDecimal procedureCost;

	/** 评价时间 */
	@Column(name = "rate_time")
	private java.util.Date rateTime;

	private String createdTimeString;

	private String payTimeString;

	private String payTimeString2;

	private String statusString;

	private String payTypeString;

	/** 记账类型 */
	@Column(name = "continueclass")
	private Integer continueclass;

	public void setCreatedTimeString(String createdTimeString) {
		this.createdTimeString = createdTimeString;
	}

	public String getCreatedTimeString() {
		if (createdTime != null) {
			return StringUtil.date2StringTime(createdTime);
		}
		return createdTimeString;
	}

	public void setPayTimeString(String payTimeString) {
		this.payTimeString = payTimeString;
	}

	public String getPayTimeString() {
		if (payTime != null) {
			return StringUtil.date2StringTime(payTime);
		}
		return createdTimeString;
	}

	public void setPayTimeString2(String payTimeString2) {
		this.payTimeString2 = payTimeString2;
	}

	public String getPayTimeString2() {
		if (payTime != null) {
			return StringUtil.date2String4(payTime);
		}
		return createdTimeString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public String getStatusString() {
		if (status != null) {
			if (status == 1) {
				return "待付款";
			}
			if (status == 2) {
				return "已付款";
			}
			if (status == 3) {
				return "待评价";
			}
			if (status == 4) {
				return "已取消";
			}
			if (status == 5) {
				return "客户端删除";
			}
			if (status == 8) {
				return "已完成";
			}
		}
		return statusString;
	}

	public void setPayTypeString(String payTypeString) {
		this.payTypeString = payTypeString;
	}

	public String getPayTypeString() {
		if (payType != null) {
			if (payType == 1) {
				return "支付宝";
			}
			if (payType == 2) {
				return "微信";
			}
			if (payType == 3) {
				return "银联";
			}
			if (payType == 4) {
				return "现金";
			}
		}
		return payTypeString;
	}

	/** 记录中间报名少上的课时数 */
	@Column(name = "restclass")
	private Integer restclass;

	/** 是否中间报名 1表示中间报名 */
	@Column(name = "rest_type")
	private Integer restType;
}
