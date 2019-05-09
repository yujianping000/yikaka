package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 横幅表
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_BANNER")
public class TBanner implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 图片链接 */
	@Column(name = "img_url" )
	private String imgUrl;
	
	/** 链接 */
	@Column(name = "link_url" )
	private String linkUrl;
	
	/** 排序 */
	@Column(name = "sort" )
	private Integer sort;
	
	/** 标题 */
	@Column(name = "title" )
	private String title;
	
}
