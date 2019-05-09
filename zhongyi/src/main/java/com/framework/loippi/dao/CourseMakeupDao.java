package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseMakeup;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseMakeup(补课申请)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseMakeupDao  extends GenericDao<CourseMakeup, Long> {
	List<CourseMakeup> findListByPage(Object parameter);

}
