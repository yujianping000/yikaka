package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseChange;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseChange(调班申请)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseChangeDao  extends GenericDao<CourseChange, Long> {
	List<CourseChange> findListByPage(Object parameter);

}
