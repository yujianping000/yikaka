package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseSummary;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseSummary(教师课程总结)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseSummaryDao  extends GenericDao<CourseSummary, Long> {
	List<CourseSummary> findListByPage(Object parameter);

}
