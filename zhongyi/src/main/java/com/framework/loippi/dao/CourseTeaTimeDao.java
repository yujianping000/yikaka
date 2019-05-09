package com.framework.loippi.dao;

import com.framework.loippi.controller.app.model.CourseTime;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseTeaTime(教师上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseTeaTimeDao  extends GenericDao<CourseTeaTime, Long> {
	List<CourseTeaTime> findListByPage(Object parameter);
	List<Long> findTimeIdLate(Object parameter);
	List<Long> findTimeIdonTime(Object parameter);
	Long deletewhere(Object parameter);
	CourseTeaTime findisEnable(Object parameter);
    void	updateClassStatus(Object parameter);
     List<CourseTime>   findListCoursetea();
   void  updateOrderTimes(Object parameter);
   
	List<CourseTeaTime> findByParams2(Object parameter);
}
