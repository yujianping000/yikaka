package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseChangeDao;
import com.framework.loippi.entity.CourseChange;
import com.framework.loippi.service.CourseChangeService;
import java.util.List;
/**
 * SERVICE - CourseChange(调班申请)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseChangeServiceImpl extends GenericServiceImpl<CourseChange, Long> implements CourseChangeService {
	
	@Autowired
	private CourseChangeDao courseChangeDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseChangeDao);
	}

	public List<CourseChange> findListByPage(Object parameter){
		return courseChangeDao.findListByPage(parameter);
	}
}
