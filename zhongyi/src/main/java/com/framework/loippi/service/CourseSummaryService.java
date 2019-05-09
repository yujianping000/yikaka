package com.framework.loippi.service;

import com.framework.loippi.entity.CourseSummary;
import java.util.List;
/**
 * SERVICE - CourseSummary(教师课程总结)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseSummaryService  extends GenericService<CourseSummary, Long> {
	public	List<CourseSummary> findListByPage(Object parameter);
}
