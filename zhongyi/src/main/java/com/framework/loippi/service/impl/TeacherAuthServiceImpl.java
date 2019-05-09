package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TeacherAuthDao;
import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.service.TeacherAuthService;
import java.util.List;
/**
 * SERVICE - TeacherAuth(老师认证)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TeacherAuthServiceImpl extends GenericServiceImpl<TeacherAuth, Long> implements TeacherAuthService {
	
	@Autowired
	private TeacherAuthDao teacherAuthDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(teacherAuthDao);
	}

	public List<TeacherAuth> findListByPage(Object parameter){
		return teacherAuthDao.findListByPage(parameter);
	}
}
