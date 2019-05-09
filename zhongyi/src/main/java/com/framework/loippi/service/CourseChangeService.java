package com.framework.loippi.service;

import com.framework.loippi.entity.CourseChange;
import java.util.List;
/**
 * SERVICE - CourseChange(调班申请)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseChangeService  extends GenericService<CourseChange, Long> {
	public	List<CourseChange> findListByPage(Object parameter);
}
