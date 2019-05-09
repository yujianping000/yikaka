package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.StudentCommentRemarkDao;
import com.framework.loippi.entity.StudentCommentRemark;
import com.framework.loippi.service.StudentCommentRemarkService;
import java.util.List;
/**
 * SERVICE - StudentCommentRemark(学生评价)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class StudentCommentRemarkServiceImpl extends GenericServiceImpl<StudentCommentRemark, Long> implements StudentCommentRemarkService {
	
	@Autowired
	private StudentCommentRemarkDao studentCommentRemarkDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(studentCommentRemarkDao);
	}

	public List<StudentCommentRemark> findListByPage(Object parameter){
		return studentCommentRemarkDao.findListByPage(parameter);
	}
}
