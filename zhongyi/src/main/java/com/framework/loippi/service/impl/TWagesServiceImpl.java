package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TWagesDao;
import com.framework.loippi.entity.TWages;
import com.framework.loippi.service.TWagesService;
import java.util.List;
/**
 * SERVICE - TWages(工资表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TWagesServiceImpl extends GenericServiceImpl<TWages, Long> implements TWagesService {
	
	@Autowired
	private TWagesDao tWagesDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tWagesDao);
	}

	public List<TWages> findListByPage(Object parameter){
		return tWagesDao.findListByPage(parameter);
	}
}
