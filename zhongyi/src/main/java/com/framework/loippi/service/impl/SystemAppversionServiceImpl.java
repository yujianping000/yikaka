package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.SystemAppversionDao;
import com.framework.loippi.entity.SystemAppversion;
import com.framework.loippi.service.SystemAppversionService;
import java.util.List;
/**
 * SERVICE - SystemAppversion(app版本更新信息)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class SystemAppversionServiceImpl extends GenericServiceImpl<SystemAppversion, Long> implements SystemAppversionService {
	
	@Autowired
	private SystemAppversionDao systemAppversionDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(systemAppversionDao);
	}

	public List<SystemAppversion> findListByPage(Object parameter){
		return systemAppversionDao.findListByPage(parameter);
	}
}
