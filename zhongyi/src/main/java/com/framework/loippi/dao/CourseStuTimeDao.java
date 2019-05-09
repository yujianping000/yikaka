package com.framework.loippi.dao;

import com.framework.loippi.controller.app.model.CourseTime;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseStuTime(学生上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseStuTimeDao  extends GenericDao<CourseStuTime, Long> {
	List<CourseStuTime> findListByPage(Object parameter);
	Long deletewhere(Object parameter);
	 List<CourseTime>   findListCourse();
}
