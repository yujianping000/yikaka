package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseSummaryDao;
import com.framework.loippi.entity.CourseSummary;
import com.framework.loippi.service.CourseSummaryService;
import java.util.List;
/**
 * SERVICE - CourseSummary(教师课程总结)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseSummaryServiceImpl extends GenericServiceImpl<CourseSummary, Long> implements CourseSummaryService {
	
	@Autowired
	private CourseSummaryDao courseSummaryDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseSummaryDao);
	}

	public List<CourseSummary> findListByPage(Object parameter){
		return courseSummaryDao.findListByPage(parameter);
	}
}
