package com.framework.loippi.dao;

import com.framework.loippi.controller.app.model.AppCourseTimeTch;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseTime(上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseTimeDao  extends GenericDao<CourseTime, Long> {
	List<CourseTime> findListByPage(Object parameter);
	List<AppCourseTimeTch> findCourseTimeById(Object parameter);
	List<String> findCourseTime1(Object parameter);
	List<AppCourseTimeTch> findCourseTimeById2(Object parameter);
	List<String> findCourseTime3(Object parameter);
	List<CourseTime> findByParams2(Object parameter);
	List<CourseTime> findByParams3(Object parameter);
}
