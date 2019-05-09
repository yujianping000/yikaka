package com.framework.loippi.service;

import com.framework.loippi.entity.SystemAppversion;
import java.util.List;
/**
 * SERVICE - SystemAppversion(app版本更新信息)
 * 
 * @author wgb
 * @version 2.0
 */
public interface SystemAppversionService  extends GenericService<SystemAppversion, Long> {
	public	List<SystemAppversion> findListByPage(Object parameter);
}
