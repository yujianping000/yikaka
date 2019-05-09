package com.framework.loippi.dao;

import com.framework.loippi.entity.Course;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Course(课程表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseDao  extends GenericDao<Course, Long> {
	List<Course> findListByPage(Object parameter);
    Long  update2buyCourse(Long id);
	List<Course> findMyListByPage(Object parameter);
	List<Course> findAvg(Object parameter);
	
	List<Course> findCoourseTypeByOrgId(Object parameter);
	Course findOrgIdByCourseId(Object parameter);
}
