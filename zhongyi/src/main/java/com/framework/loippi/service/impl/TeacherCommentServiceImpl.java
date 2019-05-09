package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TeacherCommentDao;
import com.framework.loippi.entity.TeacherComment;
import com.framework.loippi.service.TeacherCommentService;
import java.util.List;
/**
 * SERVICE - TeacherComment(机构评价)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class TeacherCommentServiceImpl extends GenericServiceImpl<TeacherComment, Long> implements TeacherCommentService {
	
	@Autowired
	private TeacherCommentDao teacherCommentDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(teacherCommentDao);
	}

	public List<TeacherComment> findListByPage(Object parameter){
		return teacherCommentDao.findListByPage(parameter);
	}
}
