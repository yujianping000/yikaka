package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.UStudentDao;
import com.framework.loippi.entity.UStudent;
import com.framework.loippi.service.UStudentService;

/**
 * SERVICE - UStudent(学生表)
 * 
 * @author longbh
 * @version 2.0
 */
@Service
public class UStudentServiceImpl extends GenericServiceImpl<UStudent, Long> implements UStudentService {
	
	@Autowired
	private UStudentDao uStudentDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(uStudentDao);
	}
}
