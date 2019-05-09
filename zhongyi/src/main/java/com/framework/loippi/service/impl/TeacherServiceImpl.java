package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TeacherDao;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.service.TeacherService;
import java.util.List;
/**
 * SERVICE - Teacher(教师表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TeacherServiceImpl extends GenericServiceImpl<Teacher, Long> implements TeacherService {
	
	@Autowired
	private TeacherDao teacherDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(teacherDao);
	}

	public List<Teacher> findListByPage(Object parameter){
		return teacherDao.findListByPage(parameter);
	}

	@Override
	public List<Teacher> findTeacherListByOrgId(Object parameter) {
		return teacherDao.findTeacherListByOrgId(parameter);
	}
}
