package com.framework.loippi.service;

import com.framework.loippi.controller.app.model.AppCourseTimeTch;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.CourseTime;
import java.util.List;
/**
 * SERVICE - CourseTime(上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseTimeService  extends GenericService<CourseTime, Long> {
	public	List<CourseTime> findListByPage(Object parameter);
	public List<AppCourseTimeTch> findCourseTimeById(Object parameter);
	public List<String> findCourseTime1(Object parameter);
    public	List<AppCourseTimeTch> findCourseTimeById2(Object parameter);
	public List<String> findCourseTime3(Object parameter);
    public		List<CourseTime> findByParams2(Object parameter);
    public			List<CourseTime> findByParams3(Object parameter);
}
