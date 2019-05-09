package com.framework.loippi.service;

import com.framework.loippi.controller.app.model.CourseTime;
import com.framework.loippi.entity.CourseStuTime;
import java.util.List;
/**
 * SERVICE - CourseStuTime(学生上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseStuTimeService  extends GenericService<CourseStuTime, Long> {
	public	List<CourseStuTime> findListByPage(Object parameter);
	public		Long deletewhere(Object parameter);
	 List<CourseTime>   findListCourse();
}
