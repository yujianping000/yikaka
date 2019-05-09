package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseTimeCommentDao;
import com.framework.loippi.entity.CourseTimeComment;
import com.framework.loippi.service.CourseTimeCommentService;
import java.util.List;
/**
 * SERVICE - CourseTimeComment(课程教学时间评论)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseTimeCommentServiceImpl extends GenericServiceImpl<CourseTimeComment, Long> implements CourseTimeCommentService {
	
	@Autowired
	private CourseTimeCommentDao courseTimeCommentDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseTimeCommentDao);
	}

	public List<CourseTimeComment> findListByPage(Object parameter){
		return courseTimeCommentDao.findListByPage(parameter);
	}
}
