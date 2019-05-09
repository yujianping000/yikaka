package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.StudentDao;
import com.framework.loippi.entity.Student;
import com.framework.loippi.mybatis.paginator.domain.PageList;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;

import java.util.List;
/**
 * SERVICE - Student(学生表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class StudentServiceImpl extends GenericServiceImpl<Student, Long> implements StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(studentDao);
	}

	public List<Student> findListByPage(Object parameter){
		return studentDao.findListByPage(parameter);
	}

	@Override
	public List<Student> findStudentListByOrgId(Object parameter) {
		return studentDao.findStudentListByOrgId(parameter);
	}

	@Override
	public Long getById(String account) {
		
		return studentDao.getById(account);
	}
}
