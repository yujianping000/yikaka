package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseBook;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseBook(课程预约试听)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseBookDao  extends GenericDao<CourseBook, Long> {
	List<CourseBook> findListByPage(Object parameter);

}
