package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.controller.app.model.CourseTime;
import com.framework.loippi.dao.CourseStuTimeDao;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.service.CourseStuTimeService;
import java.util.List;
/**
 * SERVICE - CourseStuTime(学生上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseStuTimeServiceImpl extends GenericServiceImpl<CourseStuTime, Long> implements CourseStuTimeService {
	
	@Autowired
	private CourseStuTimeDao courseStuTimeDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseStuTimeDao);
	}

	public List<CourseStuTime> findListByPage(Object parameter){
		return courseStuTimeDao.findListByPage(parameter);
	}

	@Override
	public Long deletewhere(Object parameter) {
		// TODO Auto-generated method stub
		return courseStuTimeDao.deletewhere(parameter);
	}

	@Override
	public List<CourseTime> findListCourse() {
		// TODO Auto-generated method stub
		return courseStuTimeDao.findListCourse();
	}
}
