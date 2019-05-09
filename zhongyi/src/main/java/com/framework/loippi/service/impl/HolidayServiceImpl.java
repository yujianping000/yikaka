package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.HolidayDao;
import com.framework.loippi.entity.Holiday;
import com.framework.loippi.service.HolidayService;
import java.util.List;
/**
 * SERVICE - Holiday(节假日)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class HolidayServiceImpl extends GenericServiceImpl<Holiday, Long> implements HolidayService {
	
	@Autowired
	private HolidayDao holidayDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(holidayDao);
	}

	public List<Holiday> findListByPage(Object parameter){
		return holidayDao.findListByPage(parameter);
	}
}
