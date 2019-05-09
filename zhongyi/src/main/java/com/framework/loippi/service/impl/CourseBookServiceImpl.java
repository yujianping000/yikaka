package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseBookDao;
import com.framework.loippi.entity.CourseBook;
import com.framework.loippi.service.CourseBookService;
import java.util.List;
/**
 * SERVICE - CourseBook(课程预约试听)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseBookServiceImpl extends GenericServiceImpl<CourseBook, Long> implements CourseBookService {
	
	@Autowired
	private CourseBookDao courseBookDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseBookDao);
	}

	public List<CourseBook> findListByPage(Object parameter){
		return courseBookDao.findListByPage(parameter);
	}


}
