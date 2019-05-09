package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseTypeDao;
import com.framework.loippi.entity.CourseType;
import com.framework.loippi.service.CourseTypeService;
import java.util.List;
/**
 * SERVICE - CourseType(课程类型)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseTypeServiceImpl extends GenericServiceImpl<CourseType, Long> implements CourseTypeService {
	
	@Autowired
	private CourseTypeDao courseTypeDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseTypeDao);
	}

	public List<CourseType> findListByPage(Object parameter){
		return courseTypeDao.findListByPage(parameter);
	}
}
