package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import com.framework.loippi.utils.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - tWithdraw
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_WITHDRAW")
public class TWithdraw implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	private String statusString;

	private String withdrawTimeString;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;

	/** 提现时间 */
	@Column(name = "withdraw_time")
	private java.util.Date withdrawTime;

	/** 提现现金 */
	@Column(name = "withdraw_total")
	private java.math.BigDecimal withdrawTotal;

	/** 手续费 */
	@Column(name = "procedure_total")
	private java.math.BigDecimal procedureTotal;

	/** 金额 */
	@Column(name = "to_account_total")
	private java.math.BigDecimal toAccountTotal;

	/** 银行名称 */
	@Column(name = "bank_name")
	private String bankName;

	/** 银行卡号 */
	@Column(name = "bank_num")
	private String bankNum;

	/** 持卡人 */
	@Column(name = "bank_user_name")
	private String bankUserName;

	/** 备注 */
	@Column(name = "note")
	private String note;

	/** 状态 1已完成 2待审批 3已拒绝 */
	@Column(name = "status")
	private Integer status;

	/** 状态 1已完成 2待审批 3已拒绝 */
	@Column(name = "org_id")
	private Long orgId;

	/** 状态 1已完成 2待审批 3已拒绝 */
	@Column(name = "user_accnout")
	private String userAccnout;

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public String getStatusString() {
		if (status != null) {
			if (status == 1) {
				return "已完成";
			}
			if (status == 2) {
				return "待审批";
			}
			if (status == 3) {
				return "已拒绝";
			}
		}
		return statusString;
	}

	public void setWithdrawTimeString(String withdrawTimeString) {
		this.withdrawTimeString = withdrawTimeString;
	}

	public String getWithdrawTimeString() {
		if (withdrawTime != null) {
			return StringUtil.date2String4(withdrawTime);
		}
		return withdrawTimeString;
	}

}
