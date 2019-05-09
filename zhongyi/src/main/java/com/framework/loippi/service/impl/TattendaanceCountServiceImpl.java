package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TattendaanceCountDao;
import com.framework.loippi.entity.TattendaanceCount;
import com.framework.loippi.service.TattendaanceCountService;
import java.util.List;
/**
 * SERVICE - TattendaanceCount(考勤管理)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TattendaanceCountServiceImpl extends GenericServiceImpl<TattendaanceCount, Long> implements TattendaanceCountService {
	
	@Autowired
	private TattendaanceCountDao tattendaanceCountDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tattendaanceCountDao);
	}

	public List<TattendaanceCount> findListByPage(Object parameter){
		return tattendaanceCountDao.findListByPage(parameter);
	}
}
