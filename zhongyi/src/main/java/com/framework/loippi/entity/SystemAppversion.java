package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - app版本更新信息
 * 
 * @author wgb
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYSTEM_APPVERSION")
public class SystemAppversion implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** app类型 */
	@Column(name = "type" )
	private String type;
	
	/** 版本号 */
	@Column(name = "version" )
	private String version;
	
	/** 下载地址 */
	@Column(name = "url" )
	private String url;
	
	/** 更新版本内容 */
	@Column(name = "content" )
	private String content;
	
	/** 强制更新状态 1 不强制  2 强制 */
	@Column(name = "status" )
	private String status;
	
}
