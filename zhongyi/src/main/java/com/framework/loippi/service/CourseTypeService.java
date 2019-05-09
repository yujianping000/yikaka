package com.framework.loippi.service;

import com.framework.loippi.entity.CourseType;
import java.util.List;
/**
 * SERVICE - CourseType(课程类型)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseTypeService  extends GenericService<CourseType, Long> {
	public	List<CourseType> findListByPage(Object parameter);
}
