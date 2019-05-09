package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TimeViewDao;
import com.framework.loippi.entity.TimeView;
import com.framework.loippi.service.TimeViewService;
import java.util.List;
/**
 * SERVICE - TimeView(机构课时)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class TimeViewServiceImpl extends GenericServiceImpl<TimeView, Long> implements TimeViewService {
	
	@Autowired
	private TimeViewDao timeViewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(timeViewDao);
	}

	public List<TimeView> findListByPage(Object parameter){
		return timeViewDao.findListByPage(parameter);
	}
}
