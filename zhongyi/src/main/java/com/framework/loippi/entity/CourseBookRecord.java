package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 试课跟进
 * 
 * @author wyr
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE_BOOK_RECORD")
public class CourseBookRecord implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 跟进日期 */
	@Column(name = "record_time" )
	private java.util.Date recordTime;
	
	/** 跟进情况记录 */
	@Column(name = "record" )
	private String record;
	
	/** 跟进人姓名 */
	@Column(name = "name" )
	private String name;
	
	/** 备注 */
	@Column(name = "remark" )
	private String remark;
	
	/** 创建日期 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 试课id */
	@Column(name = "book_id" )
	private Long bookId;
	
}
