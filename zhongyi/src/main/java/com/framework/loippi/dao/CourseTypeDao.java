package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseType;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseType(课程类型)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseTypeDao  extends GenericDao<CourseType, Long> {
	List<CourseType> findListByPage(Object parameter);

}
