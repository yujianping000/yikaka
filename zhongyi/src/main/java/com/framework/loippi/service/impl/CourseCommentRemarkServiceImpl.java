package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseCommentRemarkDao;
import com.framework.loippi.entity.CourseCommentRemark;
import com.framework.loippi.service.CourseCommentRemarkService;
import java.util.List;
/**
 * SERVICE - CourseCommentRemark(评论标签)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseCommentRemarkServiceImpl extends GenericServiceImpl<CourseCommentRemark, Long> implements CourseCommentRemarkService {
	
	@Autowired
	private CourseCommentRemarkDao courseCommentRemarkDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseCommentRemarkDao);
	}

	public List<CourseCommentRemark> findListByPage(Object parameter){
		return courseCommentRemarkDao.findListByPage(parameter);
	}
}
