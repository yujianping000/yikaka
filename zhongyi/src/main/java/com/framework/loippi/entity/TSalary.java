package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 工资表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SALARY")
public class TSalary implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 用户id */
	@Column(name = "uid" )
	private Long uid;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 底薪 */
	@Column(name = "basic_salary" )
	private java.math.BigDecimal basicSalary;
	
	/** 课时 */
	@Column(name = "course_salary" )
	private java.math.BigDecimal courseSalary;
	
	/** 绩效 */
	@Column(name = "performance_salary" )
	private java.math.BigDecimal performanceSalary;
	
	/** 全勤 */
	@Column(name = "all_tsutomu" )
	private java.math.BigDecimal allTsutomu;
	
	/** 奖金 */
	@Column(name = "bonus" )
	private java.math.BigDecimal bonus;
	
	/** 提成 */
	@Column(name = "push_money" )
	private java.math.BigDecimal pushMoney;
	
	/** 报销 */
	@Column(name = "reimburse" )
	private java.math.BigDecimal reimburse;
	
	/** 自定义1 */
	@Column(name = "edit1" )
	private java.math.BigDecimal edit1;
	
	/** 自定义2 */
	@Column(name = "edit2" )
	private java.math.BigDecimal edit2;
	
	/** 自定义3 */
	@Column(name = "edit3" )
	private java.math.BigDecimal edit3;
	
}
