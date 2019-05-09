package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TeacherCommentRemarkDao;
import com.framework.loippi.entity.TeacherCommentRemark;
import com.framework.loippi.service.TeacherCommentRemarkService;
import java.util.List;
/**
 * SERVICE - TeacherCommentRemark(教师评论标签)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class TeacherCommentRemarkServiceImpl extends GenericServiceImpl<TeacherCommentRemark, Long> implements TeacherCommentRemarkService {
	
	@Autowired
	private TeacherCommentRemarkDao teacherCommentRemarkDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(teacherCommentRemarkDao);
	}

	public List<TeacherCommentRemark> findListByPage(Object parameter){
		return teacherCommentRemarkDao.findListByPage(parameter);
	}
}
