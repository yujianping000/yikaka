package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.controller.app.model.AppCourseTimeTch;
import com.framework.loippi.dao.CourseTimeDao;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.service.CourseTimeService;
import java.util.List;
/**
 * SERVICE - CourseTime(上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseTimeServiceImpl extends GenericServiceImpl<CourseTime, Long> implements CourseTimeService {
	
	@Autowired
	private CourseTimeDao courseTimeDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseTimeDao);
	}

	public List<CourseTime> findListByPage(Object parameter){
		return courseTimeDao.findListByPage(parameter);
	}

	@Override
	public List<AppCourseTimeTch> findCourseTimeById(Object parameter) {
		// TODO Auto-generated method stub
		return courseTimeDao.findCourseTimeById(parameter);
	}

	@Override
	public List<String> findCourseTime1(Object parameter) {
		// TODO Auto-generated method stub
		return courseTimeDao.findCourseTime1(parameter);
	}

	@Override
	public List<AppCourseTimeTch> findCourseTimeById2(Object parameter) {
		// TODO Auto-generated method stub
		return courseTimeDao.findCourseTimeById2(parameter);
	}

	@Override
	public List<String> findCourseTime3(Object parameter) {
		// TODO Auto-generated method stub
		return courseTimeDao.findCourseTime3(parameter);
	}

	@Override
	public List<CourseTime> findByParams2(Object parameter) {
		// TODO Auto-generated method stub
		return courseTimeDao.findByParams2(parameter);
	}
	@Override
	public List<CourseTime> findByParams3(Object parameter) {
		// TODO Auto-generated method stub
		return courseTimeDao.findByParams3(parameter);
	}

}
