package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.WagesListDao;
import com.framework.loippi.entity.WagesList;
import com.framework.loippi.service.WagesListService;
import java.util.List;
/**
 * SERVICE - WagesList(工资明细)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class WagesListServiceImpl extends GenericServiceImpl<WagesList, Long> implements WagesListService {
	
	@Autowired
	private WagesListDao wagesListDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(wagesListDao);
	}

	public List<WagesList> findListByPage(Object parameter){
		return wagesListDao.findListByPage(parameter);
	}
}
