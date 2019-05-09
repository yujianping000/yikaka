package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.HourViewDao;
import com.framework.loippi.entity.HourView;
import com.framework.loippi.service.HourViewService;
import java.util.List;
/**
 * SERVICE - HourView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class HourViewServiceImpl extends GenericServiceImpl<HourView, Long> implements HourViewService {
	
	@Autowired
	private HourViewDao hourViewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(hourViewDao);
	}

	public List<HourView> findListByPage(Object parameter){
		return hourViewDao.findListByPage(parameter);
	}
}
