package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseMakeupDao;
import com.framework.loippi.entity.CourseMakeup;
import com.framework.loippi.service.CourseMakeupService;
import java.util.List;
/**
 * SERVICE - CourseMakeup(补课申请)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseMakeupServiceImpl extends GenericServiceImpl<CourseMakeup, Long> implements CourseMakeupService {
	
	@Autowired
	private CourseMakeupDao courseMakeupDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseMakeupDao);
	}

	public List<CourseMakeup> findListByPage(Object parameter){
		return courseMakeupDao.findListByPage(parameter);
	}
}
