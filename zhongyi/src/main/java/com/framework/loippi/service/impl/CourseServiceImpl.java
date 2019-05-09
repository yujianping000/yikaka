package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseDao;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseBook;
import com.framework.loippi.service.CourseService;
import java.util.List;
/**
 * SERVICE - Course(课程表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseServiceImpl extends GenericServiceImpl<Course, Long> implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseDao);
	}

	public List<Course> findListByPage(Object parameter){
		return courseDao.findListByPage(parameter);
	}

	@Override
	public List<Course> findMyListByPage(Object parameter) {
		// TODO Auto-generated method stub
		return courseDao.findMyListByPage(parameter);
	}

	@Override
	public List<Course> findCoourseTypeByOrgId(Object parameter) {
		
		return courseDao.findCoourseTypeByOrgId(parameter);
	}

	@Override
	public Course findOrgIdByCourseId(Object parameter) {
		
		return courseDao.findOrgIdByCourseId(parameter);
	}
	
	
}
