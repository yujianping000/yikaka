package com.framework.loippi.service;

import com.framework.loippi.entity.CourseBook;
import java.util.List;
/**
 * SERVICE - CourseBook(课程预约试听)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseBookService  extends GenericService<CourseBook, Long> {
	public	List<CourseBook> findListByPage(Object parameter);
}
