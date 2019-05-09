package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 老师认证
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_TEACHER_AUTH")
public class TeacherAuth implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/**  */
	@Column(name = "org_id" )
	private Long orgId;
	
	/**  */
	@Column(name = "name" )
	private String name;
	
	/**  */
	@Column(name = "birthday" )
	private java.util.Date birthday;
	
	/** 1-全职   2-兼职 */
	@Column(name = "type" )
	private Integer type;
	
	/**  */
	@Column(name = "sex" )
	private String sex;
	
	/** 省份证 */
	@Column(name = "id_card" )
	private String idCard;
	
	/** 教师证 */
	@Column(name = "teacher_card" )
	private String teacherCard;
	
	/** 学历证 */
	@Column(name = "education_card" )
	private String educationCard;
	
	/** 资历证 */
	@Column(name = "ql_card" )
	private String qlCard;
	
	/**  */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/**  */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
	/** 对应老师id */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 1-通过  2-申请中  3-被拒绝 */
	@Column(name = "status" )
	private Integer status;
	
	/** 相关理由 */
	@Column(name = "reason" )
	private String reason;
	
	/** 1-已读   2-未读 */
	@Column(name = "is_read" )
	private Integer isRead;
	
	private String avatar;
	/** 相关理由 */
	@Column(name = "account" )
	private String account;
}
