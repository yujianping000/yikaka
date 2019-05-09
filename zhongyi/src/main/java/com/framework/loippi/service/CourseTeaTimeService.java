package com.framework.loippi.service;

import com.framework.loippi.controller.app.model.CourseTime;
import com.framework.loippi.entity.CourseTeaTime;
import java.util.List;
/**
 * SERVICE - CourseTeaTime(教师上课时间安排)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseTeaTimeService  extends GenericService<CourseTeaTime, Long> {
	public	List<CourseTeaTime> findListByPage(Object parameter);
	public List<Long> findTimeIdLate(Object parameter);
	public	List<Long> findTimeIdonTime(Object parameter);
	Long deletewhere(Object parameter);
	CourseTeaTime findisEnable(Object parameter);
    public void updateClassStatus(Object parameter);
    List<CourseTime>   findListCoursetea();
    void  updateOrderTimes(Object parameter);
    
	List<CourseTeaTime> findByParams2(Object parameter);
}
