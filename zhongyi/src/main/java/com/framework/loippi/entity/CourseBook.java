package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import com.framework.loippi.utils.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 课程预约试听
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_BOOK")
public class CourseBook implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 姓名 */
	@Column(name = "name" )
	private String name;
	
	/** 联系方式 */
	@Column(name = "phone" )
	private String phone;
	
	/** 孩子id */
	@Column(name = "child_id" )
	private Long childId;
	
	/** 孩子姓名 */
	@Column(name = "child_name" )
	private String childName;
	
	/** 报班目的 */
	@Column(name = "remark" )
	private String remark;
	
	/** 课程id */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 家长id */
	@Column(name = "parent_id" )
	private Long parentId;
	
	/** 课程名 */
	@Column(name = "course_name" )
	private String courseName;
	
	/** 家长账号名 */
	@Column(name = "parent_account" )
	private String parentAccount;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
	/** 孩子性别*/
	@Column(name = "sex" )
	private String sex;
	
	/** 孩子出生日期*/
	@Column(name = "birthday" )
	private java.util.Date birthday;
	
	private String createDateString;
	
	private String birthdayString;
	
	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}

	public String getCreateDateString() {
		if (createDate != null) {
			return StringUtil.date2String(createDate);
		}
		return createDateString;
	}
	
	public void setBirthdayString(String birthdayString) {
		this.birthdayString = birthdayString;
	}
	
	public String getBirthdayString() {
		if (birthday != null) {
			return StringUtil.date2String(birthday);
		}
		return birthdayString;
	}
	
	
}
