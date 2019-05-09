package com.framework.loippi.service;

import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseBook;

import java.util.List;
/**
 * SERVICE - Course(课程表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseService  extends GenericService<Course, Long> {
	public	List<Course> findListByPage(Object parameter);
	public	List<Course> findMyListByPage(Object parameter);
	
	public List<Course> findCoourseTypeByOrgId(Object parameter);
	public Course findOrgIdByCourseId(Object parameter);
}
