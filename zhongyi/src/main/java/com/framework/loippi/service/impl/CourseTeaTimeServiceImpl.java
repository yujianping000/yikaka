package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.controller.app.model.CourseTime;
import com.framework.loippi.dao.CourseTeaTimeDao;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.service.CourseTeaTimeService;
import java.util.List;
/**
 * SERVICE - CourseTeaTime(教师上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseTeaTimeServiceImpl extends GenericServiceImpl<CourseTeaTime, Long> implements CourseTeaTimeService {
	
	@Autowired
	private CourseTeaTimeDao courseTeaTimeDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseTeaTimeDao);
	}

	public List<CourseTeaTime> findListByPage(Object parameter){
		return courseTeaTimeDao.findListByPage(parameter);
	}

	@Override
	public List<Long> findTimeIdLate(Object parameter) {
		// TODO Auto-generated method stub
		return courseTeaTimeDao.findTimeIdLate(parameter);
	}

	@Override
	public List<Long> findTimeIdonTime(Object parameter) {
		// TODO Auto-generated method stub
		return courseTeaTimeDao.findTimeIdonTime(parameter);
	}
	

	@Override
	public Long deletewhere(Object parameter) {
		// TODO Auto-generated method stub
		return  courseTeaTimeDao.deletewhere(parameter);
	}

	@Override
	public CourseTeaTime findisEnable(Object parameter) {
		// TODO Auto-generated method stub
		return courseTeaTimeDao.findisEnable(parameter);
	}

	@Override
	public void updateClassStatus(Object parameter) {
		 courseTeaTimeDao.updateClassStatus(parameter);
		
	}

	@Override
	public List<CourseTime> findListCoursetea() {
		// TODO Auto-generated method stub
		return courseTeaTimeDao.findListCoursetea();
	}

	@Override
	public void updateOrderTimes(Object parameter) {
		 courseTeaTimeDao.updateOrderTimes(parameter);
		
	}

	@Override
	public List<CourseTeaTime> findByParams2(Object parameter) {
		// TODO Auto-generated method stub
		return courseTeaTimeDao.findByParams2( parameter);
	}
}
